import java.util.ArrayList;
import java.util.Arrays;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <task number>";
    public static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:";
    public static final String MESSAGE_EMPTY_TASKLIST = "The task list is empty. Please add a task first.";
    
    protected int taskNumber;
    
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    public int getTaskNumber() {
        return taskNumber;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        
        int taskNumber = getTaskNumber();
        int taskListSize = taskList.getSize();
        final String MESSAGE_NONEXISTENT_TASK = "Task number " + taskNumber + " not found. Please enter a valid task number from 1 to " + taskListSize + ".";

        int indexToMark = taskNumber - 1;
        Task taskToMark = null;
        try {
            taskToMark = taskList.getTask(indexToMark);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(MESSAGE_NONEXISTENT_TASK);
        }
        final String MESSAGE_ALREADY_MARKED = "The task `" + taskToMark + "` is already marked as done. No action done.";
        
        String[] messageList = null;

        boolean isMarkedSuccess = taskList.markTask(indexToMark);
        if (isMarkedSuccess == true) {
            storage.saveTasks(taskList);
            messageList = new String[]{MESSAGE_MARK_SUCCESS, ui.formatSpace(2) + taskToMark};
        } else {
            messageList = new String[]{MESSAGE_ALREADY_MARKED};
        }

        ui.printMessage(messageList);
    }
}