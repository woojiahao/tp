package unicash.model.category;

import static java.util.Objects.requireNonNull;
import static unicash.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unicash.model.category.exceptions.CategoryNotFoundException;
import unicash.model.category.exceptions.DuplicateCategoryException;
import unicash.model.category.exceptions.MaxCategoryException;

/**
 * A list of categories that enforces uniqueness between its elements and does not allow nulls.
 * A category is considered unique by comparing using {@code Category#equals(Category)}. As such, adding and updating of
 * tags uses Category#equals(Category) for equality so as to ensure that the tag being added or updated is
 * unique in terms of identity in the UniqueCategoryList.
 * <p>
 * Supports a minimal set of list operations.
 */
public class UniqueCategoryList implements Iterable<Category> {
    private static final int MAX_CATEGORIES = 5;
    private final ObservableList<Category> internalList = FXCollections.observableArrayList();
    private final ObservableList<Category> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns {@code true} if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Category toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Returns true is the storage is full, false otherwise.
     */
    public boolean isMax() {
        return internalList.size() == MAX_CATEGORIES;
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(Category toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateCategoryException();
        }
        if (isMax()) {
            throw new MaxCategoryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the tag {@code target} in the list with {@code editedCategory}.
     * {@code target} must exist in the list.
     * The tag identity of {@code editedCategory} must not be the same as another existing tag in the list.
     */
    public void setCategory(Category target, Category editedCategory) {
        requireAllNonNull(target, editedCategory);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new CategoryNotFoundException();
        }

        if (!target.equals(editedCategory) && contains(editedCategory)) {
            throw new DuplicateCategoryException();
        }

        internalList.set(index, editedCategory);
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Category toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new CategoryNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     */
    public void setCategories(UniqueCategoryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setCategories(List<Category> tags) {
        requireAllNonNull(tags);
        if (!categoriesAreUnique(tags)) {
            throw new DuplicateCategoryException();
        }

        internalList.setAll(tags);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Category> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Category> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueCategoryList
                && new HashSet<>(internalList).equals(new HashSet<>(((UniqueCategoryList) other).internalList)));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns {@code true} if {@code tags} contains only unique tags.
     */
    private boolean categoriesAreUnique(List<Category> tags) {
        HashSet<Category> set = new HashSet<>();
        for (Category tag : tags) {
            if (set.contains(tag)) {
                return false;
            }
            set.add(tag);
        }
        return true;
    }
}
