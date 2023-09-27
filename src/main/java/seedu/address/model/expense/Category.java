package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Expense's category.
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS =
            "Categories should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the category must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String category;

    /**
     * Constructs a {@code Category}.
     *
     * @param category A valid category.
     */
    public Category(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        this.category = category;
    }

    /**
     * Returns true if a given string is a valid category.
     */
    public static boolean isValidCategory(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return category;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Category)) {
            return false;
        }

        Category otherCategory = (Category) other;
        return category.equals(otherCategory.category);
    }

    @Override
    public int hashCode() {
        return category.hashCode();
    }
}
