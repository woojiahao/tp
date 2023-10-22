package unicash.ui;

/**
 * A class to consolidate and standardize all text styling, text formatting
 * and coloring involved in FXML and associated Controller classes.
 */
public class StyleSheet {

    // Text colour filling
    public static final String TEXT_FILL_RED = "-fx-text-fill: red";
    public static final String TEXT_FILL_GREEN = "-fx-text-fill: green";
    public static final String TEXT_FILL_BLACK = "-fx-text-fill: black";
    public static final String TEXT_FILL_WHITE = "-fx-text-fill: white";

    // Font styling
    public static final String FONT_STYLE_BOLD = "-fx-font-weight: bold";
    public static final String FONT_STYLE_REGULAR = "-fx-font-weight: normal";
    public static final String FONT_STYLE_ITALIC = "-fx-font-style: italic";

    // Custom text formatting
    public static final String TRANSACTION_ID_SEPARATOR = ". ";

    // Color utilities
    public static final String TEXT_BACKGROUND_COLOR = "-fx-background-color: %s";
    public static final int MAX_COLOR_VALUE = 255;
    public static final int HEXADECIMAL_RADIX = 16;

    // Color variables
    public static final int HEX_COLOR_START_INDEX = 0;
    public static final int HEX_COLOR_END_INDEX = 6;
    public static final int BRIGHTNESS_THRESHOLD = 130;
    public static final int BRIGHTNESS_OFFSET = 50;
    public static final int RED_OFFSET = 70;
    public static final int GREEN_OFFSET = 70;
    public static final int BLUE_OFFSET = 0;
    public static final boolean IS_YELLOW_SKEW = true;

    /**
     * Returns a 6-digit hexadecimal number based on an Object's unique
     * hash code, to be used as a color indicator. If the hex color value
     * generated from the hashcode accords poor {@code Label} visibility,
     * the value is offset such that is always of a certain brightness value.
     */
    public static String getBrightCategoryColorFromHash(Object obj) {

        // Absolute value of hash code taken to guard against negative values
        String hexString = Integer.toHexString(Math.abs(obj.hashCode()));

        // Padding for hash codes that are less than 6 digits long
        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }

        // Hex index refers to the specific substring of the hash code to be used.
        // This can be altered according to specific hashing patterns.
        String objectColorString = hexString.substring(
                HEX_COLOR_START_INDEX,
                HEX_COLOR_END_INDEX).toUpperCase();

        int r = Integer.parseInt(objectColorString.substring(0, 2), HEXADECIMAL_RADIX);
        int g = Integer.parseInt(objectColorString.substring(2, 4), HEXADECIMAL_RADIX);
        int b = Integer.parseInt(objectColorString.substring(4, 6), HEXADECIMAL_RADIX);

        int avg = (r + g + b) / 3;

        // The higher this threshold is set in the brighter the category tags will be.
        // Ideal seems to be between 130 and 140
        if (avg < BRIGHTNESS_THRESHOLD) {
            r = adjustBrightness(r);
            g = adjustBrightness(g);
            b = adjustBrightness(b);
        }

        // Skew hex code to favour more warm colors.
        if (IS_YELLOW_SKEW) {
            r = Math.min(MAX_COLOR_VALUE, r + RED_OFFSET);
            g = Math.min(MAX_COLOR_VALUE, g + GREEN_OFFSET);
            b = Math.min(b + BLUE_OFFSET, (r + g) / 4);
        }

        objectColorString = String.format("%02X%02X%02X", r, g, b);
        return "#" + objectColorString;
    }

    private static int adjustBrightness(int value) {
        return Math.min(value + BRIGHTNESS_OFFSET, MAX_COLOR_VALUE);
    }

    /**
     * Returns a 6-digit hexadecimal number based on the Category's unique
     * hash code, to be used as a color indicator, without any color offsetting
     */
    public static String getCategoryColorFromHash(Object obj) {
        // Absolute value of hash code taken to guard against negative values
        String hexString = Integer.toHexString(
                Math.abs(obj.hashCode()));

        // Padding for hash codes that are less than 6 digits long
        while (hexString.length() < 6) {
            hexString = "0" + hexString;
        }

        String categoryColorString = hexString.substring(0, 6).toUpperCase();

        return "#" + categoryColorString;
    }

}
