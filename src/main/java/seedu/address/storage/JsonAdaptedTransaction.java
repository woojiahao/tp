package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Category;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Type;

/**
 * Jackson-friendly version of {@link Transaction}.
 */
public class JsonAdaptedTransaction {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Transaction's %s field is missing!";

    private final String name;
    private final double amount;
    private final String category;
    private final String dateTime;
    private final String location;
    private final String type;

    /**
     * Constructs a {@code JsonAdaptedTransaction} with the given transaction details.
     */
    @JsonCreator
    public JsonAdaptedTransaction(
            @JsonProperty("name") String name,
            @JsonProperty("amount") double amount,
            @JsonProperty("category") String category,
            @JsonProperty("dateTime") String dateTime,
            @JsonProperty("location") String location,
            @JsonProperty("type") String type
    ) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.dateTime = dateTime;
        this.location = location;
        this.type = type;
    }

    /**
     * Constructs a {@code JsonAdaptedTransaction} with the given transaction details.
     */
    public JsonAdaptedTransaction(Transaction source) {
        name = source.getName().fullName;
        amount = source.getAmount().amount;
        category = source.getCategory().category;
        dateTime = source.getDateTime().originalString();
        location = source.getLocation().location;
        type = source.getType().type.getOriginalString();
    }

    /**
     * Converts this Jackson-friendly adapted transaction object into the model's {@code Transaction} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted format.
     */
    public Transaction toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(formatMissingFieldMessage(Name.class));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        if (category == null) {
            throw new IllegalValueException(formatMissingFieldMessage(Category.class));
        }
        if (!Category.isValidCategory(category)) {
            throw new IllegalValueException(Category.MESSAGE_CONSTRAINTS);
        }
        final Category modelCategory = new Category(category);

        if (dateTime == null) {
            throw new IllegalValueException(formatMissingFieldMessage(DateTime.class));
        }
        if (!DateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(DateTime.MESSAGE_CONSTRAINTS);
        }
        final DateTime modelDateTime = new DateTime(dateTime);

        if (location != null && Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (type == null) {
            throw new IllegalValueException(formatMissingFieldMessage(Type.class));
        }
        if (!Type.isValidType(type)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
        final Type modelType = new Type(type);

        return new Transaction(modelName, modelType, modelAmount, modelCategory, modelDateTime, modelLocation);
    }

    /**
     * Pretty formats missing field message with the class name.
     */
    private <T> String formatMissingFieldMessage(Class<T> fieldName) {
        return String.format(MISSING_FIELD_MESSAGE_FORMAT, fieldName.getSimpleName());
    }
}
