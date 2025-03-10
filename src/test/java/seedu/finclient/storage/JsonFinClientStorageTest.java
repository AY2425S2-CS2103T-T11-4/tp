package seedu.finclient.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.finclient.testutil.Assert.assertThrows;
import static seedu.finclient.testutil.TypicalPersons.ALICE;
import static seedu.finclient.testutil.TypicalPersons.HOON;
import static seedu.finclient.testutil.TypicalPersons.IDA;
import static seedu.finclient.testutil.TypicalPersons.getTypicalFinClient;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.finclient.commons.exceptions.DataLoadingException;
import seedu.finclient.model.FinClient;
import seedu.finclient.model.ReadOnlyFinClient;

public class JsonFinClientStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonFinClientStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFinClient_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFinClient(null));
    }

    private java.util.Optional<ReadOnlyFinClient> readFinClient(String filePath) throws Exception {
        return new JsonFinClientStorage(Paths.get(filePath)).readFinClient(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFinClient("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readFinClient("notJsonFormatFinClient.json"));
    }

    @Test
    public void readFinClient_invalidPersonFinClient_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readFinClient("invalidPersonFinClient.json"));
    }

    @Test
    public void readFinClient_invalidAndValidPersonFinClient_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readFinClient("invalidAndValidPersonFinClient.json"));
    }

    @Test
    public void readAndSaveFinClient_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        FinClient original = getTypicalFinClient();
        JsonFinClientStorage jsonFinClientStorage = new JsonFinClientStorage(filePath);

        // Save in new file and read back
        jsonFinClientStorage.saveFinClient(original, filePath);
        ReadOnlyFinClient readBack = jsonFinClientStorage.readFinClient(filePath).get();
        assertEquals(original, new FinClient(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonFinClientStorage.saveFinClient(original, filePath);
        readBack = jsonFinClientStorage.readFinClient(filePath).get();
        assertEquals(original, new FinClient(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonFinClientStorage.saveFinClient(original); // file path not specified
        readBack = jsonFinClientStorage.readFinClient().get(); // file path not specified
        assertEquals(original, new FinClient(readBack));

    }

    @Test
    public void saveFinClient_nullFinClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinClient(null, "SomeFile.json"));
    }

    /**
     * Saves {@code finClient} at the specified {@code filePath}.
     */
    private void saveFinClient(ReadOnlyFinClient finClient, String filePath) {
        try {
            new JsonFinClientStorage(Paths.get(filePath))
                    .saveFinClient(finClient, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFinClient_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFinClient(new FinClient(), null));
    }
}
