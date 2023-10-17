package unicash.logic.parser;

import unicash.logic.commands.GetTotalExpenditureCommand;
import unicash.logic.parser.exceptions.ParseException;
import unicash.model.category.Category;
import unicash.logic.UniCashMessages;

/**
 * Parses get_total_expenditure command to extract parameters.
 */
public class GetTotalExpenditureCommandParser implements Parser<GetTotalExpenditureCommand> {

    @Override
    public GetTotalExpenditureCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_CATEGORY);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT, GetTotalExpenditureCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_CATEGORY);

        String categoryString = argMultimap.getValue(CliSyntax.PREFIX_CATEGORY).orElse(null);
        Category category = null;
        if (categoryString != null) {
            category = ParserUtil.parseCategory(categoryString);
        }

        try {
            String monthString = argMultimap.getPreamble();
            int month = Integer.parseInt(monthString);
            return new GetTotalExpenditureCommand(month, category);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid month value, must be number!");
        }
    }
}
