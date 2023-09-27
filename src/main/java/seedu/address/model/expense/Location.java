package seedu.address.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Location {

    public static final String MESSAGE_CONSTRAINTS =
            "Locations should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String location;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Location(String location) {
        requireNonNull(location);
        checkArgument(isValidLocation(location), MESSAGE_CONSTRAINTS);
        this.location = location;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidLocation(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return location;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return location.equals(otherLocation.location);
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }
}
