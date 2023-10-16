package seedu.address.logic.parser.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.transaction.EditTransactionCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTransactionCommand object
 */
public class EditTransactionCommandParser implements Parser<EditTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTransactionCommand
     * and returns an EditTransactionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTransactionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TYPE, PREFIX_AMOUNT, PREFIX_DATETIME,
                        PREFIX_CATEGORY, PREFIX_LOCATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTransactionCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_TYPE, PREFIX_AMOUNT, PREFIX_DATETIME,
                PREFIX_CATEGORY, PREFIX_LOCATION);

        EditTransactionCommand.EditTransactionDescriptor editTransactionDescriptor = new EditTransactionCommand
                .EditTransactionDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editTransactionDescriptor.setName(
                    ParserUtil.parseTransactionName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            editTransactionDescriptor.setType(
                    ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editTransactionDescriptor.setAmount(
                    ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editTransactionDescriptor.setDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editTransactionDescriptor.setCategory(
                    ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editTransactionDescriptor.setLocation(
                    ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }

        if (!editTransactionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTransactionCommand(index, editTransactionDescriptor);
    }

}
