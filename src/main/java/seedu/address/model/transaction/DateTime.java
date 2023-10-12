package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Objects;

/**
 * Represents a Transaction's dateTime.
 * Guarantees: immutable;
 */
public class DateTime {
    public static final String DATETIME_PATTERN = "dd-MM-uuuu HH:mm";
    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should be in the following format " + DATETIME_PATTERN;
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter
            .ofPattern(DATETIME_PATTERN);

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime}.
     * Defaults to current date if not provided.
     *
     * @param dateTime A valid date time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        if (dateTime.isEmpty()) {
            this.dateTime = LocalDateTime.now();
            return;
        }
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, DATETIME_FORMATTER);
    }

    /**
     * Helper method to stringify LocalDateTime objects into original text string
     * pass by the user.
     *
     * @return text string of the LocalDateTime object
     */
    public String originalString() {
        return dateTime.format(DateTimeFormatter.ofPattern(DATETIME_PATTERN));
    }

    /**
     * Returns true if a given string is a valid dateTime.
     */
    public static boolean isValidDateTime(String dateString) {
        try {
            LocalDateTime.parse(dateString, DATETIME_FORMATTER.withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
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
