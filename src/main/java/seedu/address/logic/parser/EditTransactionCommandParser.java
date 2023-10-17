package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;

/**
 * Parses input arguments and creates a new EditTransactionCommand object
 */
public class EditTransactionCommandParser implements Parser<EditTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTransactionCommand
     * and returns an EditTransactionCommand object for execution.
     *
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
                    ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get())
            );
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editTransactionDescriptor.setAmount(
                    ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get())
            );
        }
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            editTransactionDescriptor.setDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get())
            );
        }

        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            editTransactionDescriptor.setLocation(
                    ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get())
            );
        }

        parseCategoriesForEdit(argMultimap.getAllValues(PREFIX_CATEGORY))
                .ifPresent(editTransactionDescriptor::setCategories);

        if (!editTransactionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditTransactionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTransactionCommand(index, editTransactionDescriptor);
    }

    /**
     * Parses {@code Collection<String> categories} into a {@code Set<Category>} if {@code categories} is non-empty.
     * If {@code categories} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Category>} containing zero categories.
     */
    private Optional<Set<Category>> parseCategoriesForEdit(Collection<String> categories) throws ParseException {
        requireNonNull(categories);

        if (categories.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> categoriesSet = categories.size() == 1
                && categories.contains("")
                ? Collections.emptySet()
                : categories;
        return Optional.of(ParserUtil.parseCategories(categoriesSet));
    }
}
