package seedu.finclient.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.finclient.commons.core.GuiSettings;
import seedu.finclient.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getFinClientFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setFinClientFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setFinClient(ReadOnlyFinClient addressBook);

    /** Returns the FinClient */
    ReadOnlyFinClient getFinClient();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Hides details of person(s) that matches the predicate
     * @param predicate
     */
    void hidePerson(Predicate<Person> predicate);

    /**
     * Hides details of the person
     * @param person
     */
    void hidePerson(Person person);

    /**
     * Reveals details of person(s) that matches the predicate
     * @param predicate
     */
    void revealPerson(Predicate<Person> predicate);

    /**
     * Reveals details of the person
     * @param person
     */
    void revealPerson(Person person);

    /**
     * Hides details of all persons
     */
    void hideAllPersons();

    /**
     * Reveals all hidden persons
     */
    void revealAllPersons();

    /**
     * Returns the clearing price based on current orders.
     */
    Optional<Double> calculateClearingPrice();

    /**
     * Sorts the persons in the address book by the given criteria.
     */
    void sortPersons(String criteria);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);
    List<Person> getUpcomingPersons(int count);
}
