package seedu.finclient.model;

import java.nio.file.Path;

import seedu.finclient.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFinClientFilePath();

}
