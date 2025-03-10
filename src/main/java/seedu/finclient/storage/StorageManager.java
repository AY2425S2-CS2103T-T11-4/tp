package seedu.finclient.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.finclient.commons.core.LogsCenter;
import seedu.finclient.commons.exceptions.DataLoadingException;
import seedu.finclient.model.ReadOnlyFinClient;
import seedu.finclient.model.ReadOnlyUserPrefs;
import seedu.finclient.model.UserPrefs;

/**
 * Manages storage of FinClient data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FinClientStorage finClientStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FinClientStorage finClientStorage, UserPrefsStorage userPrefsStorage) {
        this.finClientStorage = finClientStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ FinClient methods ==============================

    @Override
    public Path getFinClientFilePath() {
        return finClientStorage.getFinClientFilePath();
    }

    @Override
    public Optional<ReadOnlyFinClient> readFinClient() throws DataLoadingException {
        return readFinClient(finClientStorage.getFinClientFilePath());
    }

    @Override
    public Optional<ReadOnlyFinClient> readFinClient(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return finClientStorage.readFinClient(filePath);
    }

    @Override
    public void saveFinClient(ReadOnlyFinClient finClient) throws IOException {
        saveFinClient(finClient, finClientStorage.getFinClientFilePath());
    }

    @Override
    public void saveFinClient(ReadOnlyFinClient finClient, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        finClientStorage.saveFinClient(finClient, filePath);
    }

}
