package seedu.address.testutil;

import seedu.address.logic.commands.transaction.EditTransactionCommand;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Category;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Type;

/**
 * A utility class to help with building EditTransactionDescriptor objects.
 */
public class EditTransactionDescriptorBuilder {
    private EditTransactionCommand.EditTransactionDescriptor descriptor;

    public EditTransactionDescriptorBuilder() {
        descriptor = new EditTransactionCommand.EditTransactionDescriptor();
    }

    public EditTransactionDescriptorBuilder(EditTransactionCommand.EditTransactionDescriptor descriptor) {
        this.descriptor = new EditTransactionCommand.EditTransactionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTransactionDescriptor} with fields containing {@code transaction}'s details
     */
    public EditTransactionDescriptorBuilder(Transaction transaction) {
        descriptor = new EditTransactionCommand.EditTransactionDescriptor();
        descriptor.setName(transaction.getName());
        descriptor.setAmount(transaction.getAmount());
        descriptor.setCategory(transaction.getCategory());
        descriptor.setDateTime(transaction.getDateTime());
        descriptor.setLocation(transaction.getLocation());
        descriptor.setType(transaction.getType());
    }

    /**
     * Sets the {@code Name} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withAmount(double amount) {
        descriptor.setAmount(new Amount(amount));
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withCategory(String category) {
        descriptor.setCategory(new Category(category));
        return this;
    }

    /**
     * Sets the {@code DateTime} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new DateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Sets the {@code Type} of the {@code EditTransactionDescriptor} that we are building.
     */
    public EditTransactionDescriptorBuilder withType(String type) {
        descriptor.setType(new Type(type));
        return this;
    }

    public EditTransactionCommand.EditTransactionDescriptor build() {
        return descriptor;
    }
}
