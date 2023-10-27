package unicash.commons.util;

import static java.util.Objects.requireNonNull;
import static unicash.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static unicash.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static unicash.logic.parser.CliSyntax.PREFIX_DATETIME;
import static unicash.logic.parser.CliSyntax.PREFIX_LOCATION;
import static unicash.logic.parser.CliSyntax.PREFIX_MONTH;
import static unicash.logic.parser.CliSyntax.PREFIX_NAME;
import static unicash.logic.parser.CliSyntax.PREFIX_TYPE;
import static unicash.logic.parser.CliSyntax.PREFIX_YEAR;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import unicash.logic.parser.Prefix;

/**
 * Generates example usage of commands given predefined "good" values of each prefix.
 */
public class ExampleGenerator {
    private static final Map<Prefix, String> PREFIX_VALUES = Map.ofEntries(
            new SimpleEntry<>(PREFIX_NAME, "Buying groceries"),
            new SimpleEntry<>(PREFIX_AMOUNT, "300"),
            new SimpleEntry<>(PREFIX_DATETIME, "18-08-2023 19:30"),
            new SimpleEntry<>(PREFIX_TYPE, "expense"),
            new SimpleEntry<>(PREFIX_CATEGORY, "Food"),
            new SimpleEntry<>(PREFIX_LOCATION, "NTUC"),
            new SimpleEntry<>(PREFIX_MONTH, "10"),
            new SimpleEntry<>(PREFIX_YEAR, "2006")
    );

    private ExampleGenerator() {

    }

    public static String generate(String commandWord, Prefix... prefixes) {
        requireNonNull(commandWord);
        var stringBuilder = new StringBuilder();
        stringBuilder.append(commandWord);
        if (prefixes.length == 0) {
            return stringBuilder.toString();
        }

        stringBuilder.append(" ");
        for (var prefix : prefixes) {
            requireNonNull(prefix);
        }

        var arguments = Arrays.stream(prefixes)
                .map(p ->
                        String.format("%s%s", p.getPrefix(), PREFIX_VALUES.getOrDefault(p, "unknown"))
                )
                .collect(Collectors.joining(" "));
        stringBuilder.append(arguments);
        return stringBuilder.toString();
    }

    public static String generate(String commandWord, String argument, Prefix... prefixes) {
        requireNonNull(commandWord, argument);
        var newStart = String.format("%s %s", commandWord, argument);
        return generate(newStart, prefixes);
    }
}
