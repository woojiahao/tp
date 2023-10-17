package unicash.testutil;

import java.util.Set;

import unicash.logic.commands.AddTransactionCommand;
import unicash.logic.commands.EditTransactionCommand;
import unicash.model.category.Category;
import unicash.model.transaction.Transaction;
import unicash.logic.parser.CliSyntax;

/**
 * A utility class for Transaction.
 */
public class TransactionUtil {

    /**
     * Returns an add command string for adding the {@code transaction}.
     */
    public static String getAddTransactionCommand(Transaction transaction) {
        return AddTransactionCommand.COMMAND_WORD + " " + getTransactionDetails(transaction);
    }

    /**
     * Returns the part of command string for the given {@code transaction}'s details.
     */
    public static String getTransactionDetails(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(CliSyntax.PREFIX_NAME).append(transaction.getName().fullName).append(" ");
        sb.append(CliSyntax.PREFIX_TYPE).append(transaction.getType().type.getOriginalString()).append(" ");
        sb.append(CliSyntax.PREFIX_AMOUNT).append(transaction.getAmount().toString()).append(" ");
        sb.append(CliSyntax.PREFIX_DATETIME).append(transaction.getDateTime().originalString()).append(" ");
        sb.append(CliSyntax.PREFIX_LOCATION).append(transaction.getLocation().location).append(" ");
        transaction.getCategories().forEach(
                category -> sb.append(CliSyntax.PREFIX_CATEGORY).append(category.category).append(" ")
        );
        return sb.toString();
    }

    public static String getEditTransactionDescriptorDetails(EditTransactionCommand.EditTransactionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(CliSyntax.PREFIX_NAME).append(name.fullName)
                .append(" "));
        descriptor.getType().ifPresent(type -> sb.append(CliSyntax.PREFIX_TYPE).append(type.type.getOriginalString())
                .append(" "));
        descriptor.getAmount().ifPresent(amount -> sb.append(CliSyntax.PREFIX_AMOUNT).append(amount.amount)
                .append(" "));
        descriptor.getLocation().ifPresent(location -> sb.append(CliSyntax.PREFIX_LOCATION).append(location)
                .append(" "));
        descriptor.getDateTime().ifPresent(dateTime -> sb.append(CliSyntax.PREFIX_DATETIME).append(dateTime.originalString())
                .append(" "));
        if (descriptor.getCategories().isPresent()) {
            Set<Category> categories = descriptor.getCategories().get();
            if (categories.isEmpty()) {
                sb.append(CliSyntax.PREFIX_CATEGORY);
            } else {
                categories.forEach(s -> sb.append(CliSyntax.PREFIX_CATEGORY).append(s.category).append(" "));
            }
        }
        return sb.toString();
    }
}
