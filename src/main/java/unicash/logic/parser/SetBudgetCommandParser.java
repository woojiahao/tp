package unicash.logic.parser;

import static unicash.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static unicash.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static unicash.logic.parser.CliSyntax.PREFIX_INTERVAL;

import java.util.stream.Stream;

import unicash.logic.commands.SetBudgetCommand;
import unicash.logic.parser.exceptions.ParseException;
import unicash.model.budget.Budget;
import unicash.model.budget.Interval;
import unicash.model.commons.Amount;

/**
 * Parses input arguments and creates a new SetBudgetCommand.
 */
public class SetBudgetCommandParser implements Parser<SetBudgetCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public SetBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_INTERVAL);

        // check if mandatory fields (amt, interval) are present
        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT, PREFIX_INTERVAL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE)
            );
        }

        // check for duplicates
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_AMOUNT, PREFIX_INTERVAL);

        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Interval interval = ParserUtil.parseInterval(argMultimap.getValue(PREFIX_INTERVAL).get());

        Budget budget = new Budget(amount, interval);

        return new SetBudgetCommand(budget);
    }

    /**
     * Returns true if all the given {@code prefixes} are found in the {@code argumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
