package unicash.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import unicash.commons.exceptions.IllegalValueException;
import unicash.model.ReadOnlyUniCash;
import unicash.model.UniCash;
import unicash.model.transaction.Transaction;

/**
 * An Immutable UniCash that is serializable to JSON format.
 */
@JsonRootName(value = "unicash")
class JsonSerializableUniCash {

    private final List<JsonAdaptedTransaction> transactions = new ArrayList<>();
    private final JsonAdaptedBudget budget;

    /**
     * Constructs a {@code JsonSerializableUniCash} with the given transactions.
     */
    @JsonCreator
    public JsonSerializableUniCash(
            @JsonProperty("transactions") List<JsonAdaptedTransaction> transactions,
            @JsonProperty("budget") JsonAdaptedBudget budget
    ) {
        this.transactions.addAll(transactions);
        this.budget = budget;
    }

    /**
     * Converts a given {@code ReadOnlyUniCash} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableUniCash}.
     */
    public JsonSerializableUniCash(ReadOnlyUniCash source) {
        transactions.addAll(
                source.getTransactionList()
                        .stream()
                        .map(JsonAdaptedTransaction::new)
                        .collect(Collectors.toList())
        );
        if (source.getBudget() != null) {
            budget = new JsonAdaptedBudget(source.getBudget());
        } else {
            budget = null;
        }
    }

    /**
     * Converts this transaction list into the model's {@code UniCash} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public UniCash toModelType() throws IllegalValueException {
        UniCash uniCash = new UniCash();
        for (var jsonAdaptedTransaction : transactions) {
            Transaction transaction = jsonAdaptedTransaction.toModelType();
            uniCash.addTransaction(transaction);
        }
        if (budget != null) {
            uniCash.setBudget(budget.toModelType());
        }
        return uniCash;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof JsonSerializableUniCash)) {
            return false;
        }

        var otherInstance = (JsonSerializableUniCash) other;
        if (budget == null) {
            return transactions.equals(otherInstance.transactions) && otherInstance.budget == null;
        }
        return transactions.equals(otherInstance.transactions) && budget.equals(otherInstance.budget);
    }

}
