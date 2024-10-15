package seedu.address.model.supplier;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Supplier}'s {@code SupplierName} matches any of the keywords given.
 */
public class SupplierNameContainsKeywordsPredicate implements Predicate<Supplier> {
    private final List<String> keywords;

    public SupplierNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Supplier supplier) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(supplier.getName().supplierName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SupplierNameContainsKeywordsPredicate)) {
            return false;
        }

        SupplierNameContainsKeywordsPredicate otherSupplierNameContainsKeywordsPredicate =
                (SupplierNameContainsKeywordsPredicate) other;
        return keywords.equals(otherSupplierNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
