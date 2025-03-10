package seedu.finclient.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.finclient.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.finclient.commons.exceptions.IllegalValueException;
import seedu.finclient.commons.util.JsonUtil;
import seedu.finclient.model.FinClient;
import seedu.finclient.testutil.TypicalPersons;

public class JsonSerializableFinClientTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableFinClientTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsFinClient.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonFinClient.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonFinClient.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableFinClient dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableFinClient.class).get();
        FinClient finClientFromFile = dataFromFile.toModelType();
        FinClient typicalPersonsFinClient = TypicalPersons.getTypicalFinClient();
        assertEquals(finClientFromFile, typicalPersonsFinClient);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFinClient dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableFinClient.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableFinClient dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableFinClient.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFinClient.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
