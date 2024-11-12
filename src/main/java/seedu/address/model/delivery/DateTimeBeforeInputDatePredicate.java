package seedu.address.model.delivery;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code Delivery}'s {@code DateTime} value is earlier than input completion date.
 */
public class DateTimeBeforeInputDatePredicate implements Predicate<Delivery> {
    private final DateTime completionDateTime;

    /**
     * Constructs a {@code DateTimeBeforeInputDatePredicate}.
     *
     * @param completionDateTime The completion date to compare against.
     */
    public DateTimeBeforeInputDatePredicate(DateTime completionDateTime) {
        this.completionDateTime = completionDateTime;
    }
    /**
     * Determines if predicate is true for a given input delivery by comparing the DateTime values.
     *
     * @param delivery Delivery object to test.
     * @return True if Delivery input has a DateTime value that is earlier than completionDateTime.
     */
    @Override
    public boolean test(Delivery delivery) {
        return delivery.hasEarlierDateThan(completionDateTime);
    }

    /**
     * Returns true if DateTime object of both objects are same.
     *
     * @param other Object to be compared with.
     * @return True if object is an instance of DateTimeBeforeInputDatePredicate and both
     *         DateTime objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTimeBeforeInputDatePredicate)) {
            return false;
        }

        DateTimeBeforeInputDatePredicate otherDateTimeBeforeInputDatePredicate =
                (DateTimeBeforeInputDatePredicate) other;
        return completionDateTime.equals(otherDateTimeBeforeInputDatePredicate.completionDateTime);
    }
    /**
     * Represents the String value of predicate.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("completionDateTime", completionDateTime).toString();
    }
}