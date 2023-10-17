package unicash.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import unicash.commons.core.index.Index;
import unicash.logic.commands.EditTransactionCommand;
import unicash.logic.parser.exceptions.ParseException;
import unicash.model.category.Category;
import unicash.logic.UniCashMessages;

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
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TYPE, CliSyntax.PREFIX_AMOUNT, CliSyntax.PREFIX_DATETIME,
                        CliSyntax.PREFIX_CATEGORY, CliSyntax.PREFIX_LOCATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTransactionCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TYPE, CliSyntax.PREFIX_AMOUNT, CliSyntax.PREFIX_DATETIME,
                CliSyntax.PREFIX_CATEGORY, CliSyntax.PREFIX_LOCATION);

        EditTransactionCommand.EditTransactionDescriptor editTransactionDescriptor = new EditTransactionCommand
                .EditTransactionDescriptor();

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editTransactionDescriptor.setName(
                    ParserUtil.parseTransactionName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_TYPE).isPresent()) {
            editTransactionDescriptor.setType(
                    ParserUtil.parseType(argMultimap.getValue(CliSyntax.PREFIX_TYPE).get())
            );
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_AMOUNT).isPresent()) {
            editTransactionDescriptor.setAmount(
                    ParserUtil.parseAmount(argMultimap.getValue(CliSyntax.PREFIX_AMOUNT).get())
            );
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_DATETIME).isPresent()) {
            editTransactionDescriptor.setDateTime(
                    ParserUtil.parseDateTime(argMultimap.getValue(CliSyntax.PREFIX_DATETIME).get())
            );
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_LOCATION).isPresent()) {
            editTransactionDescriptor.setLocation(
                    ParserUtil.parseLocation(argMultimap.getValue(CliSyntax.PREFIX_LOCATION).get())
            );
        }

        parseCategoriesForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_CATEGORY))
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
