package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    
    private final Ui ui = new Ui();
    private final String FOUR_SPACES = "    ";
    private final String FIVE_SPACES = "     ";
    private final String SEVEN_SPACES = "       ";
    private final String NO_SPACE = "";
    
    @Test
    public void formatSpace() {
        assertEquals(FOUR_SPACES, ui.formatSpace(4));
        assertEquals(SEVEN_SPACES, ui.formatSpace(7));
        assertEquals(FIVE_SPACES, ui.formatSpace(5));
    }

    @Test
    public void getSpace() {
        assertEquals(FOUR_SPACES, ui.getSpace(true, false));        // Horizontal line
        assertEquals(SEVEN_SPACES, ui.getSpace(false, true));       // Task
        assertEquals(FIVE_SPACES, ui.getSpace(false, false));       // General space
        assertEquals(NO_SPACE, ui.getSpace(true, true));
    }

    @Test
    public void getLine() {
        String LINE = "____________________________________________________________";
        assertEquals(FOUR_SPACES + LINE, ui.getLine());
    }

}