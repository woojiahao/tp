package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + "}";
        assertEquals(expected, guiSettings.toString());
    }

    @Test
    public void equals() {
        var settings = new GuiSettings(400, 500, 0, 0);
        assertEquals(settings, settings);

        assertFalse(settings.equals(5));

        var changedWidth = new GuiSettings(15, 500, 0, 0);
        assertNotEquals(settings, changedWidth);

        var changedHeight = new GuiSettings(400, 400, 0, 0);
        assertNotEquals(settings, changedHeight);

        var changedCoordinates = new GuiSettings(400, 500, 15, 15);
        assertNotEquals(settings, changedCoordinates);
    }
}
