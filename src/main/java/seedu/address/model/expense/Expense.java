package seedu.address.model.expense;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

public class Expense {
    private final Name name;
    private final Amount amount;
    private final String category;
    private final LocalDate date;
    private final String location;

    public Expense(Name name, Amount amount, String category, LocalDate date, String location) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.location = location;
    }

    public Name getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, category, date, location);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expense)) {
            return false;
        }

        Expense otherExpense = (Expense) other;
        return name.equals(otherExpense.name)
                && amount == otherExpense.amount
                && category.equals(otherExpense.category)
                && date.equals(otherExpense.date)
                && location.equals(otherExpense.location);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("amount", amount)
                .add("category", category)
                .add("date", date)
                .add("location", location)
                .toString();
    }
}
