package seedu.finclient.model;

import static java.util.Objects.requireNonNull;
import static seedu.finclient.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.finclient.commons.core.GuiSettings;
import seedu.finclient.commons.core.LogsCenter;
import seedu.finclient.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final FinClient finClient;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given finClient and userPrefs.
     */
    public ModelManager(ReadOnlyFinClient addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.finClient = new FinClient(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.finClient.getPersonList());
    }

    public ModelManager() {
        this(new FinClient(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getFinClientFilePath() {
        return userPrefs.getFinClientFilePath();
    }

    @Override
    public void setFinClientFilePath(Path finClientFilePath) {
        requireNonNull(finClientFilePath);
        userPrefs.setFinClientFilePath(finClientFilePath);
    }

    //=========== FinClient ================================================================================

    @Override
    public void setFinClient(ReadOnlyFinClient finClient) {
        this.finClient.resetData(finClient);
    }

    @Override
    public ReadOnlyFinClient getFinClient() {
        return finClient;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return finClient.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        finClient.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        finClient.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        finClient.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return finClient.equals(otherModelManager.finClient)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
