package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.UniCashMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Type;

/**
 * Edits the details of an existing transaction in the transactions list.
 */
public class EditTransactionCommand extends Command {

    public static final String COMMAND_WORD = "edit_transaction";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the transaction identified "
            + "by the index number used in the displayed transaction list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TYPE + "TYPE] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_DATETIME + "DATETIME] "
            + "[" + PREFIX_LOCATION + "LOCATION]"
            + "[" + PREFIX_CATEGORY + "CATEGORY]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Buying groceries "
            + PREFIX_TYPE + "expense "
            + PREFIX_AMOUNT + "300 "
            + PREFIX_DATETIME + "18-08-2001 19:30 "
            + PREFIX_LOCATION + "ntuc"
            + PREFIX_CATEGORY + "household expenses ";


    public static final String MESSAGE_EDIT_TRANSACTION_SUCCESS = "Edited Transaction: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TRANSACTION = "This transaction already exists"
            + " in the transactions list.";

    private final Index index;
    private final EditTransactionCommand.EditTransactionDescriptor editTransactionDescriptor;

    /**
     * @param index of the transaction in the filtered transaction list to edit
     * @param editTransactionDescriptor details to edit the transaction with
     */
    public EditTransactionCommand(Index index,
                                  EditTransactionCommand.EditTransactionDescriptor editTransactionDescriptor) {
        requireNonNull(index);
        requireNonNull(editTransactionDescriptor);

        this.index = index;
        this.editTransactionDescriptor = new EditTransactionCommand
                .EditTransactionDescriptor(editTransactionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Transaction> lastShownList = model.getFilteredTransactionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(UniCashMessages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
        }

        Transaction transactionToEdit = lastShownList.get(index.getZeroBased());
        Transaction editedTransaction = createEditedTransaction(transactionToEdit, editTransactionDescriptor);

        model.setTransaction(transactionToEdit, editedTransaction);
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(editedTransaction)));
    }

    /**
     * Creates and returns a {@code Transaction} with the details of {@code transactionToEdit}
     * edited with {@code editTransactionDescriptor}.
     */
    private static Transaction createEditedTransaction(Transaction transactionToEdit, EditTransactionCommand
            .EditTransactionDescriptor editTransactionDescriptor) {
        assert transactionToEdit != null;

        Name updatedName = editTransactionDescriptor.getName().orElse(transactionToEdit.getName());
        Amount updatedAmount = editTransactionDescriptor.getAmount().orElse(transactionToEdit.getAmount());
        DateTime updatedDateTime = editTransactionDescriptor.getDateTime().orElse(transactionToEdit.getDateTime());
        Location updatedLocation = editTransactionDescriptor.getLocation().orElse(transactionToEdit.getLocation());
        Type updatedType = editTransactionDescriptor.getType().orElse(transactionToEdit.getType());
        Set<Category> updatedCategories = editTransactionDescriptor.getCategories()
                .orElse(transactionToEdit.getCategories());

        return new Transaction(updatedName, updatedType, updatedAmount, updatedDateTime,
                updatedLocation, updatedCategories);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTransactionCommand)) {
            return false;
        }

        EditTransactionCommand otherEditTransactionCommand = (EditTransactionCommand) other;
        return index.equals(otherEditTransactionCommand.index)
                && editTransactionDescriptor.equals(otherEditTransactionCommand.editTransactionDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editTransactionDescriptor", editTransactionDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the transaction with. Each non-empty field value will replace the
     * corresponding field value of the transaction.
     */
    public static class EditTransactionDescriptor {
        private Name name;
        private Amount amount;
        private DateTime datetime;
        private Location location;
        private Type type;
        private HashSet<Category> categories;

        public EditTransactionDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTransactionDescriptor(EditTransactionCommand.EditTransactionDescriptor toCopy) {
            setName(toCopy.name);
            setAmount(toCopy.amount);
            setDateTime(toCopy.datetime);
            setLocation(toCopy.location);
            setType(toCopy.type);
            setCategories(toCopy.categories);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, amount, datetime, location, type, categories);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setDateTime(DateTime datetime) {
            this.datetime = datetime;
        }

        public Optional<DateTime> getDateTime() {
            return Optional.ofNullable(datetime);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        public void setType(Type type) {
            this.type = type;
        }

        public Optional<Type> getType() {
            return Optional.ofNullable(type);
        }

        /**
         * Sets {@code categories} to this object's {@code categories}.
         * A defensive copy of {@code categories} is used internally.
         */
        public void setCategories(Set<Category> categories) {
            this.categories = (categories != null) ? new HashSet<>(categories) : null;
        }

        /**
         * Returns an unmodifiable category set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code categories} is null.
         */
        public Optional<Set<Category>> getCategories() {
            return (categories != null) ? Optional.of(Collections.unmodifiableSet(categories)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTransactionCommand.EditTransactionDescriptor)) {
                return false;
            }

            EditTransactionCommand.EditTransactionDescriptor otherEditTransactionDescriptor = (EditTransactionCommand
                    .EditTransactionDescriptor) other;
            return Objects.equals(name, otherEditTransactionDescriptor.name)
                    && Objects.equals(amount, otherEditTransactionDescriptor.amount)
                    && Objects.equals(datetime, otherEditTransactionDescriptor.datetime)
                    && Objects.equals(location, otherEditTransactionDescriptor.location)
                    && Objects.equals(type, otherEditTransactionDescriptor.type)
                    && Objects.equals(categories, otherEditTransactionDescriptor.categories);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("amount", amount)
                    .add("datetime", datetime)
                    .add("location", location)
                    .add("type", type)
                    .add("categories", categories)
                    .toString();
        }
    }

}
