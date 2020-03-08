package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicateFoodException;
import seedu.address.model.person.exceptions.FoodNotFoundException;

public class UniqueFoodList implements Iterable<Food> {

    private final ObservableList<Food> internalList = FXCollections.observableArrayList();
    private final ObservableList<Food> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Food toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameFood);
    }

    /**
     * Adds a food to the list.
     * The person must not already exist in the list.
     */
    public void add(Food toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateFoodException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the food {@code target} in the list with {@code editedFood}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedFood} must not be the same as another existing food in the list.
     */
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new FoodNotFoundException();
        }

        if (!target.isSameFood(editedFood) && contains(editedFood)) {
            throw new DuplicateFoodException();
        }

        internalList.set(index, editedFood);
    }

    /**
     * Removes the equivalent food from the list.
     * The food must exist in the list.
     */
    public void remove(Food toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new FoodNotFoundException();
        }
    }

    public void setFoods(UniqueFoodList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setFoods(List<Food> foods) {
        requireAllNonNull(foods);
        if (!foodsAreUnique(foods)) {
            throw new DuplicateFoodException();
        }

        internalList.setAll(foods);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Food> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Food> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueFoodList // instanceof handles nulls
                && internalList.equals(((UniqueFoodList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean foodsAreUnique(List<Food> foods) {
        for (int i = 0; i < foods.size() - 1; i++) {
            for (int j = i + 1; j < foods.size(); j++) {
                if (foods.get(i).isSameFood(foods.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}