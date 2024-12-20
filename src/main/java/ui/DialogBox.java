package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
// Solution below referenced from https://se-education.org/guides/tutorials/javaFxPart4.html
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public static DialogBox getJavaroDialog(String text, Image img, String commandType) {
        assert commandType != null : "Command type cannot be null";
        var db = getJavaroDialog(text, img);
        db.changeDialogStyle(commandType);
        return db;
    }

    public static DialogBox getJavaroDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    private void changeDialogStyle(String commandType) {
        switch (commandType) {
            case "commands.add.TodoCommand":
            case "commands.add.DeadlineCommand":
            case "commands.add.EventCommand":
            case "commands.add.FixedDurationCommand":
            case "commands.ArchiveCommand":
            case "commands.DeleteCommand":
            case "commands.FindCommand":
            case "commands.MarkCommand":
            case "commands.ShowCommand":
            case "commands.UnmarkCommand":
                dialog.getStyleClass().add("success-label");
                break;
            case "Error":
                dialog.getStyleClass().add("failure-label");
                break;
            default:
                // Do nothing
        }
    }
}
