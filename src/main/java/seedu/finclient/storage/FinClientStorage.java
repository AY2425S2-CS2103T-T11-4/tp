package seedu.finclient.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.finclient.commons.exceptions.DataLoadingException;
import seedu.finclient.model.FinClient;
import seedu.finclient.model.ReadOnlyFinClient;

/**
 * Represents a storage for {@link FinClient}.
 */
public interface FinClientStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFinClientFilePath();

    /**
     * Returns FinClient data as a {@link ReadOnlyFinClient}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyFinClient> readFinClient() throws DataLoadingException;

    /**
     * @see #getFinClientFilePath()
     */
    Optional<ReadOnlyFinClient> readFinClient(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyFinClient} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFinClient(ReadOnlyFinClient addressBook) throws IOException;

    /**
     * @see #saveFinClient(ReadOnlyFinClient)
     */
    void saveFinClient(ReadOnlyFinClient addressBook, Path filePath) throws IOException;

}
