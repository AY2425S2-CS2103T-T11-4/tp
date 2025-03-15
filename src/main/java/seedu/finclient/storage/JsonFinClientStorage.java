package seedu.finclient.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.finclient.commons.core.LogsCenter;
import seedu.finclient.commons.exceptions.DataLoadingException;
import seedu.finclient.commons.exceptions.IllegalValueException;
import seedu.finclient.commons.util.FileUtil;
import seedu.finclient.commons.util.JsonUtil;
import seedu.finclient.model.ReadOnlyFinClient;

/**
 * A class to access FinClient data stored as a json file on the hard disk.
 */
public class JsonFinClientStorage implements FinClientStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFinClientStorage.class);

    private Path filePath;

    public JsonFinClientStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFinClientFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFinClient> readFinClient() throws DataLoadingException {
        return readFinClient(filePath);
    }

    /**
     * Similar to {@link #readFinClient()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyFinClient> readFinClient(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableFinClient> jsonFinClient = JsonUtil.readJsonFile(
                filePath, JsonSerializableFinClient.class);
        if (!jsonFinClient.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFinClient.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveFinClient(ReadOnlyFinClient addressBook) throws IOException {
        saveFinClient(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveFinClient(ReadOnlyFinClient)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFinClient(ReadOnlyFinClient addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFinClient(addressBook), filePath);
    }

}
