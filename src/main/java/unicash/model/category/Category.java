package unicash.model.category;

import static java.util.Objects.requireNonNull;
import static unicash.commons.util.AppUtil.checkArgument;
import static unicash.ui.StyleSheet.CATEGORY_BRIGHTNESS_THRESHOLD;

/**
 * Represents a Category in UnICash.
 * Guarantees: immutable; categoryName is valid as declared in {@link #isValidCategory(String)}
 */
public class Category {

    public static final String MESSAGE_CONSTRAINTS =
            "Category names should be alphanumeric and up to 15 characters long.";
    // Category can only be up to 15 characters long
    public static final String VALIDATION_REGEX = "\\p{Alnum}{1,15}$";

    public static final String CATEGORY_PREFIX_SYMBOL = "#";

    public final String category;

    /**
     * Constructs a {@code Category}.
     *
     * @param category A valid category name.
     */
    public Category(String category) {
        requireNonNull(category);
        checkArgument(isValidCategory(category), MESSAGE_CONSTRAINTS);
        this.category = category.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid category.
     */
    public static boolean isValidCategory(String test) {
        return test.matches(VALIDATION_REGEX);
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

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return category;
    }

    /**
     * Returns category with a prefixed hashtag
     */
    public String hashTagToString() {
        return CATEGORY_PREFIX_SYMBOL + category.toString();
    }

    /**
     * Returns a 6-digit hexadecimal number based on the Category's unique
     * hash code, to be used as a color indicator.
     */
    public String getCategoryColorFromHash() {
        String hexString = Integer.toHexString(
                Math.abs(this.hashCode()));

        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }

        String categoryColorString = hexString.substring(0, 6).toUpperCase();

        return "#" + categoryColorString;
    }

    /**
     * Returns a 6-digit hexadecimal number based on the Category's unique
     * hash code, to be used as a color indicator. If the hex color value
     * generate from the hashcode accords poor {@code Label} visibility,
     * the value is offset such that is always of a certain brightness value.
     */
    public String getBrightCategoryColorFromHash() {
        // Absolute value of hash code taken for
        String hexString = Integer.toHexString(Math.abs(this.hashCode()));

        // Padding for hex numbers that
        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }

        String categoryColorString = hexString.substring(0, 6).toUpperCase();

        int r = Integer.parseInt(categoryColorString.substring(0, 2), 16);
        int g = Integer.parseInt(categoryColorString.substring(2, 4), 16);
        int b = Integer.parseInt(categoryColorString.substring(4, 6), 16);

        int avg = (r + g + b) / 3;

        // The higher this threshold is set in the StyleSheets class, the
        // brighter the category tags will be.
        if (avg < CATEGORY_BRIGHTNESS_THRESHOLD) {
            r = adjustBrightness(r);
            g = adjustBrightness(g);
            b = adjustBrightness(b);
        }

        categoryColorString = String.format("%02X%02X%02X", r, g, b);
        return "#" + categoryColorString;
    }

    private int adjustBrightness(int value) {
        return Math.min(value + 64, 255);
    }

}
