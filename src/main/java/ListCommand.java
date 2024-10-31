import java.util.ArrayList;
import java.util.Arrays;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        ArrayList<String> messageList = new ArrayList<>(Arrays.asList("Here are the tasks in your list:"));
        
        int taskListSize = taskList.getSize();
        
        // if (taskList.isEmpty()) {
        //     throw new TaskException("Good job! You're all caught up!");
        // }
        
        for (int i = 0; i < taskListSize; i++) {
            Task current = taskList.getTask(i);         // taskList.get(i) contains the checkbox
            String index = Integer.toString(i + 1);
            String line = index + ". " + current;
            messageList.add(line);
        }
        
        ui.printMessage(messageList);
    }
}
