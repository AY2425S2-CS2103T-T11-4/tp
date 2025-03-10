package seedu.finclient.logic.commands;

import static seedu.finclient.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.finclient.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.finclient.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.finclient.testutil.TypicalPersons.getTypicalFinClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.finclient.model.Model;
import seedu.finclient.model.ModelManager;
import seedu.finclient.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFinClient(), new UserPrefs());
        expectedModel = new ModelManager(model.getFinClient(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
