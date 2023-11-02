package unicash.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import unicash.commons.core.index.Index;
import unicash.commons.enums.CommandType;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.logic.commands.exceptions.CommandException;
import unicash.model.Model;
import unicash.model.transaction.Transaction;

/**
 * This class handles the deletion of a Transaction from the UniCa$h database.
 */
public class DeleteTransactionCommand extends Command {

    public static final String COMMAND_WORD = CommandType.DELETE_TRANSACTION.getCommandWords();

    public static final String MESSAGE_USAGE = CommandType.DELETE_TRANSACTION.getMessageUsage();

    public static final String MESSAGE_DELETE_TRANSACTION_SUCCESS = CommandType.DELETE_TRANSACTION.getMessageSuccess();

    private final Index targetIndex;

    public DeleteTransactionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Transaction> lastShownList = model.getFilteredTransactionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(UniCashMessages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
        }

        Transaction transactionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTransaction(transactionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(transactionToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTransactionCommand)) {
            return false;
        }

        DeleteTransactionCommand otherDeleteCommand = (DeleteTransactionCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
