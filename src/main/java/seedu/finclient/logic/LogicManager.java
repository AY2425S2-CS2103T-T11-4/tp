package seedu.finclient.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.finclient.commons.core.GuiSettings;
import seedu.finclient.commons.core.LogsCenter;
import seedu.finclient.logic.commands.Command;
import seedu.finclient.logic.commands.CommandResult;
import seedu.finclient.logic.commands.exceptions.CommandException;
import seedu.finclient.logic.parser.FinClientParser;
import seedu.finclient.logic.parser.exceptions.ParseException;
import seedu.finclient.model.Model;
import seedu.finclient.model.ReadOnlyFinClient;
import seedu.finclient.model.person.Person;
import seedu.finclient.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FinClientParser finClientParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        finClientParser = new FinClientParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = finClientParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFinClient(model.getFinClient());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFinClient getFinClient() {
        return model.getFinClient();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getFinClientFilePath() {
        return model.getFinClientFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
