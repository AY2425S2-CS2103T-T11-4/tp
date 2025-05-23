package seedu.finclient.model;

import javafx.collections.ObservableList;
import seedu.finclient.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyFinClient {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
