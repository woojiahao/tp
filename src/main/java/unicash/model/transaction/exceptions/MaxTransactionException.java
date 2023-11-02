package unicash.model.transaction.exceptions;

/**
 * Represents an error when the maximum number of transactions has been added.
 */
public class MaxTransactionException extends RuntimeException {
    /**
     * Constructs a MaxTransactionException.
     */
    public MaxTransactionException() {
        super("Maximum number of trasactions added.");
    }
}
