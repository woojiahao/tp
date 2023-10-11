package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an Income's date.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(LocalDateTime)}
 */
public class DateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should be in the following format dd/mm/yyyy HH:mm";

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date time.
     */
    public DateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = dateTime;
    }

    /**
     * Returns true for all LocalDateTime objects.
     */
    public static boolean isValidDateTime(LocalDateTime amount) {
        return true;
    }

    /**
     * Helper method to stringify LocalDateTime objects into original text string
     * pass by the user.
     *
     * @return text string of the LocalDateTime object
     */
    public String originalString() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("HHmm, MMM d yyyy"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateTime)) {
            return false;
        }

        DateTime otherDateTime = (DateTime) other;
        return Objects.equals(this.dateTime, otherDateTime.dateTime);
    }

    @Override
    public int hashCode() {
        return this.dateTime.hashCode();
    }
}
