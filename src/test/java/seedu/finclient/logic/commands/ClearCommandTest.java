package seedu.finclient.logic.commands;

import static seedu.finclient.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.finclient.testutil.TypicalPersons.getTypicalFinClient;

import org.junit.jupiter.api.Test;

import seedu.finclient.model.FinClient;
import seedu.finclient.model.Model;
import seedu.finclient.model.ModelManager;
import seedu.finclient.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyFinClient_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyFinClient_success() {
        Model model = new ModelManager(getTypicalFinClient(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalFinClient(), new UserPrefs());
        expectedModel.setFinClient(new FinClient());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
