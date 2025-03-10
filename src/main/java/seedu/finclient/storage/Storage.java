package seedu.finclient.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.finclient.commons.exceptions.DataLoadingException;
import seedu.finclient.model.ReadOnlyFinClient;
import seedu.finclient.model.ReadOnlyUserPrefs;
import seedu.finclient.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FinClientStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFinClientFilePath();

    @Override
    Optional<ReadOnlyFinClient> readFinClient() throws DataLoadingException;

    @Override
    void saveFinClient(ReadOnlyFinClient addressBook) throws IOException;

}
