package unicash.logic.parser;

import java.util.Set;
import java.util.stream.Stream;

import unicash.logic.UniCashMessages;
import unicash.logic.commands.AddTransactionCommand;
import unicash.logic.parser.exceptions.ParseException;
import unicash.model.category.Category;
import unicash.model.transaction.Amount;
import unicash.model.transaction.DateTime;
import unicash.model.transaction.Location;
import unicash.model.transaction.Name;
import unicash.model.transaction.Transaction;
import unicash.model.transaction.Type;


/**
 * Parses input arguments and creates a new AddTransactionCommand object
 */
public class AddTransactionCommandParser implements Parser<AddTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddTransactionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TYPE, CliSyntax.PREFIX_AMOUNT, CliSyntax.PREFIX_DATETIME,
                        CliSyntax.PREFIX_CATEGORY, CliSyntax.PREFIX_LOCATION);

        // Check if mandatory fields (name, amount, type) are present
        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_AMOUNT, CliSyntax.PREFIX_TYPE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE));
        }

        // Check for duplicates
        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TYPE, CliSyntax.PREFIX_AMOUNT, CliSyntax.PREFIX_DATETIME,
                CliSyntax.PREFIX_CATEGORY, CliSyntax.PREFIX_LOCATION);

        Name name = ParserUtil.parseTransactionName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(CliSyntax.PREFIX_AMOUNT).get());
        Type type = ParserUtil.parseType(argMultimap.getValue(CliSyntax.PREFIX_TYPE).get());

        String dateTimeString = argMultimap.getValue(CliSyntax.PREFIX_DATETIME).orElse("");
        DateTime dateTime = ParserUtil.parseDateTime(dateTimeString);

        String locationString = argMultimap.getValue(CliSyntax.PREFIX_LOCATION).orElse("");
        Location location = ParserUtil.parseLocation(locationString);

        Set<Category> categoryList = ParserUtil.parseCategories(argMultimap.getAllValues(CliSyntax.PREFIX_CATEGORY));

        Transaction transaction = new Transaction(name, type, amount, dateTime, location, categoryList);

        return new AddTransactionCommand(transaction);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
