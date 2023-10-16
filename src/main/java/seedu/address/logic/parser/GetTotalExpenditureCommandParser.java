package seedu.address.logic.parser;

import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.address.logic.commands.GetTotalExpenditureCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Category;

/**
 * Parses get_total_expenditure command to extract parameters.
 */
public class GetTotalExpenditureCommandParser implements Parser<GetTotalExpenditureCommand> {

    @Override
    public GetTotalExpenditureCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetTotalExpenditureCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CATEGORY);

        String categoryString = argMultimap.getValue(PREFIX_CATEGORY).orElse("");
        Category category = ParserUtil.parseCategory(categoryString);

        try {
            String monthString = argMultimap.getPreamble();
            int month = Integer.parseInt(monthString);
            return new GetTotalExpenditureCommand(month, category);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid month value, must be number!");
        }
    }
}
