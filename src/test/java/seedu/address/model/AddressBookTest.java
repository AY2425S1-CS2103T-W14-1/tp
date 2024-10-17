package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRODUCT_RICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeliveries.APPLE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.delivery.Delivery;
import seedu.address.model.delivery.Status;
import seedu.address.model.delivery.exceptions.DuplicateDeliveryException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.DeliveryBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withCompany(VALID_COMPANY_BOB).withTags(VALID_TAG_HUSBAND)
                .withProducts(VALID_PRODUCT_RICE).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = AddressBookStub.withPersons(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateDelivery_throwsDuplicateDeliveryException() {
        // Two deliveries with the same identity fields
        Delivery editedApple = new DeliveryBuilder(APPLE).build();
        List<Delivery> newDeliveries = Arrays.asList(editedApple, APPLE);
        AddressBookStub newData = AddressBookStub.withDeliveries(newDeliveries);

        assertThrows(DuplicateDeliveryException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withCompany(VALID_COMPANY_BOB).withTags(VALID_TAG_HUSBAND)
                .withProducts(VALID_PRODUCT_RICE).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void setDelivery_validTargetAndUpdatedDelivery_success() {
        Delivery delivery = new DeliveryBuilder().build();
        Delivery updatedDelivery = new DeliveryBuilder().withStatus(Status.DELIVERED).build();

        addressBook.addDelivery(delivery);
        addressBook.setDelivery(delivery, updatedDelivery);

        assertEquals(updatedDelivery, addressBook.getDeliveryList().get(0));
    }


    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Delivery> deliveries = FXCollections.observableArrayList();


        public static AddressBookStub withPersons(Collection<Person> persons) {
            AddressBookStub stub = new AddressBookStub();
            stub.persons.setAll(persons);
            return stub;
        }

        public static AddressBookStub withDeliveries(Collection<Delivery> deliveries) {
            AddressBookStub stub = new AddressBookStub();
            stub.deliveries.setAll(deliveries);
            return stub;
        }
        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Delivery> getDeliveryList() {
            return deliveries;
        }
    }

}
