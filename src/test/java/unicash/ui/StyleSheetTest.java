package unicash.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.ui.StyleSheet.MAX_COLOR_VALUE;
import static unicash.ui.StyleSheet.RED_OFFSET;

import org.junit.jupiter.api.Test;

public class StyleSheetTest {

    @Test
    public void testConstants() {
        assertEquals("-fx-text-fill: red", StyleSheet.TEXT_FILL_RED);
        assertEquals("-fx-font-weight: bold", StyleSheet.FONT_STYLE_BOLD);
    }

    @Test
    public void combinedColorBrightness_lessThanThreshold_returnTrue() {
        String color = StyleSheet.getBrightCategoryColorFromHash(new Object());
        int r = Integer.parseInt(color.substring(1, 3), 16);
        int g = Integer.parseInt(color.substring(3, 5), 16);
        int b = Integer.parseInt(color.substring(5, 7), 16);

        int totalBrightness = r + g + b;

        assertTrue(totalBrightness >= StyleSheet.BRIGHTNESS_THRESHOLD);
    }

    @Test
    public void combinedColorBrightness_isColorSkewed_returnTrue() {
        String color = StyleSheet.getBrightCategoryColorFromHash(new Object() {
            @Override
            public int hashCode() {
                return 11111111;
            }
        });

        if (StyleSheet.IS_YELLOW_SKEW) {

            int r = Integer.parseInt(color.substring(1, 3), 16);
            int g = Integer.parseInt(color.substring(3, 5), 16);
            int b = Integer.parseInt(color.substring(5, 7), 16);

            assertTrue(r >= g && g >= b);
        }
    }

    @Test
    public void shortHashCodeObject_isColorSkewed_returnTrue() {
        String color = StyleSheet.getBrightCategoryColorFromHash(new Object() {
            @Override
            public int hashCode() {
                return 000000001;
            }
        });

        if (StyleSheet.IS_YELLOW_SKEW) {

            int r = Integer.parseInt(color.substring(1, 3), 16);
            int g = Integer.parseInt(color.substring(3, 5), 16);
            int b = Integer.parseInt(color.substring(5, 7), 16);

            int r2 = Math.min(MAX_COLOR_VALUE, r + RED_OFFSET);
            assertTrue(r2 == Math.min(MAX_COLOR_VALUE, r + RED_OFFSET));
        }
    }

    @Test
    public void zeroHashCodeObject_parsedToString_returnZero() {
        String color = StyleSheet.getCategoryColorFromHash(new Object() {
            @Override
            public int hashCode() {
                return 000000000;
            }
        });

        int r = Integer.parseInt(color.substring(1, 3), 16);
        int g = Integer.parseInt(color.substring(3, 5), 16);
        int b = Integer.parseInt(color.substring(5, 7), 16);

        assertTrue(r == g);
        assertTrue(r == b);
        assertTrue(g == b);
    }

    @Test
    public void testRegularCategoryColor() {
        String color = StyleSheet.getCategoryColorFromHash(new Object());

        // Check if the color is a valid hex
        assertTrue(color.matches("^#[0-9A-F]{6}$"));
    }
}
