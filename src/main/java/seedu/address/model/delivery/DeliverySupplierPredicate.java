package seedu.address.model.delivery;

import java.util.function.Predicate;

import seedu.address.model.supplier.Supplier;

/**
 * Tests that a {@code Delivery}'s {@code Supplier} matches the given supplier.
 */
public class DeliverySupplierPredicate implements Predicate<Delivery> {
    private final Supplier supplier;

    public DeliverySupplierPredicate(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean test(Delivery delivery) {
        return delivery.getDeliverySender().equals(supplier);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliverySupplierPredicate // instanceof handles nulls
                && supplier.equals(((DeliverySupplierPredicate) other).supplier)); // state check
    }
}

