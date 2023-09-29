package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Category;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.Location;
import seedu.address.model.expense.Name;

/**
 * A utility class to help with building Expense objects.
 */
public class ExpenseBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final double DEFAULT_AMOUNT = 123.45;
    public static final String DEFAULT_CATEGORY = "Food";
    public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.now();
    public static final String DEFAULT_LOCATION = "Fairprice";

    private Name name;
    private Amount amount;
    private Category category;
    private LocalDateTime dateTime;
    private Location location;

    /**
     * Creates a {@code ExpenseBuilder} with the default details.
     */
    public ExpenseBuilder() {
        name = new Name(DEFAULT_NAME);
        amount = new Amount(DEFAULT_AMOUNT);
        category = new Category(DEFAULT_CATEGORY);
        dateTime = DEFAULT_DATE_TIME;
        location = new Location(DEFAULT_LOCATION);
    }

    /**
     * Initializes the ExpenseBuilder with the data of {@code personToCopy}.
     */
    public ExpenseBuilder(Expense expenseToCopy) {
        name = expenseToCopy.getName();
        amount = expenseToCopy.getAmount();
        category = expenseToCopy.getCategory();
        dateTime = expenseToCopy.getDateTime();
        location = expenseToCopy.getLocation();
    }

    /**
     * Sets the {@code Name} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withAmount(double amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Category} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withCategory(String category) {
        this.category = new Category(category);
        return this;
    }

    /**
     * Sets the {@code LocalDateTime} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public Expense build() {
        return new Expense(name, amount, category, dateTime, location);
    }
}
