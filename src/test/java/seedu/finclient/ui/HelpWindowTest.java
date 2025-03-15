package seedu.finclient.ui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Tests for {@code HelpWindow} that can run in a headless environment.
 */
public class HelpWindowTest extends ApplicationTest {
    private HelpWindow helpWindow;

    @BeforeAll
    public static void setupHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("glass.platform", "Monocle");
        System.setProperty("monocle.platform", "Headless");
        System.setProperty("prism.order", "sw");
    }

    @Override
    public void start(Stage stage) {
        helpWindow = new HelpWindow(stage);
    }

    @AfterEach
    public void cleanup() throws TimeoutException {
        // Run on FX thread to avoid threading issues
        Platform.runLater(() -> {
            if (helpWindow.isShowing()) {
                helpWindow.hide();
            }
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void isShowing_windowNotShown_returnsFalse() {
        // isShowing should be thread-safe already, but call on FX thread to be safe
        Platform.runLater(() -> {
            assertFalse(helpWindow.isShowing());
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void show_windowShown_returnsTrue() {
        // Must run on FX thread
        Platform.runLater(() -> {
            helpWindow.show();
            assertTrue(helpWindow.isShowing());
        });
        WaitForAsyncUtils.waitForFxEvents();
    }

    @Test
    public void hide_windowHidden_returnsFalse() {
        // Must run on FX thread
        Platform.runLater(() -> {
            helpWindow.show();
            helpWindow.hide();
            assertFalse(helpWindow.isShowing());
        });
        WaitForAsyncUtils.waitForFxEvents();
    }
}
