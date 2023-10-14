package seedu.address.logic.parser.transaction;

import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.address.logic.commands.transaction.GetTotalExpenditureCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Category;

public class GetTotalExpenditureCommandParser implements Parser<GetTotalExpenditureCommand> {

    @Override
    public GetTotalExpenditureCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        if (!argMultimap.getPreamble().isEmpty()) {
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
