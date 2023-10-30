package unicash.logic.parser;

import java.util.List;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_AMOUNT = new Prefix("amt/");
    public static final Prefix PREFIX_DATETIME = new Prefix("dt/");
    public static final Prefix PREFIX_TYPE = new Prefix("type/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("c/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_MONTH = new Prefix("month/");
    public static final Prefix PREFIX_YEAR = new Prefix("year/");
    public static final Prefix PREFIX_INTERVAL = new Prefix("interval/");

    /* Returns the prefixes associated with a Transaction as a list */
    public static final List<Prefix> TRANSACTION_PREFIX_LIST =
            List.of(PREFIX_NAME, PREFIX_TYPE, PREFIX_AMOUNT,
            PREFIX_DATETIME, PREFIX_CATEGORY, PREFIX_LOCATION);
}
