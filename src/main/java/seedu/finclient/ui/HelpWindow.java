package seedu.finclient.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import seedu.finclient.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String ADD_SYNTAX = "Add contact: add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]";
    public static final String EDIT_SYNTAX = "Edit contact: edit INDEX [n/NAME] "
            + "[p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]";
    public static final String LIST_SYNTAX = "List all saved contacts: list";
    public static final String FIND_SYNTAX = "Find contact by name: find KEYWORD [MORE_KEYWORDS]";
    public static final String DELETE_SYNTAX = "Delete contact: delete INDEX";
    public static final String CLEAR_SYNTAX = "Clear all saved contacts: clear";
    public static final String HELP_SYNTAX = "Getting help for available commands: help";
    public static final String EXIT_SYNTAX = "Exit FinClient: exit";
    public static final String ESCAPE_SYNTAX = "Close help window: hit the ESC key or click on the 'X'"
            + " located at the top right of this window";
    public static final String ADDITIONAL_COMMAND_SYNTAX = "Commands coming in a following update "
            + "include sort and remark";
    public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";

    public static final String HELP_MESSAGE = "Welcome to FinClient, here are the commands available: \n\n"
            + ADD_SYNTAX + "\n" + EDIT_SYNTAX + "\n" + LIST_SYNTAX + "\n" + FIND_SYNTAX + "\n"
            + DELETE_SYNTAX + "\n" + CLEAR_SYNTAX + "\n" + HELP_SYNTAX + "\n"
            + EXIT_SYNTAX + "\n\n" + ADDITIONAL_COMMAND_SYNTAX
            + "\n\n" + ESCAPE_SYNTAX + "\n\nFor a more detailed guide, please head to "
            + USERGUIDE_URL;

    //public static final String USERGUIDE_URL = "https://se-education.org/addressbook-level3/UserGuide.html";
    //public static final String HELP_MESSAGE = "Refer to the user guide: " + USERGUIDE_URL + "\n" + ADD_SYNTAX;


    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        Stage stage = getRoot();

        // Add event handler to close Help Message upon ESC key hit.
        stage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                hide();
            }
        });
        stage.show();
        stage.centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }
}
