package ch.post.view;

import ch.post.Model;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class View {

    @FXML private ScrollPane scrollPaneContainer;

    @FXML private VBox posts;

    @FXML private TextArea inputTextArea;
    @FXML private TextField inputUsername;
    @FXML private Button buttonSubmit;

    @FXML protected void submitButtonAction(MouseEvent mouseEvent){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy HH:mm");
        String dateString = dateFormat.format(date);
        addPost(inputTextArea.getText(), inputUsername.getText(), dateString);
        inputTextArea.setText("");
        inputUsername.setText("");
    }

    /**
     * Adds a post to the UI
     * @param model
     */
    public void addPost(Model model) {
        addPost(model.getContent(), model.getUsername(), model.getDate());
    }

    public void addPost(String content, String username, String date){
        posts.getChildren().add(Elements.Post(content, username, date ) );
    }

    /**
     * Displays a information popup
     * @param message
     */
    public void alert(String message) {

    }

    /**
     * Removes all posts from the View
     */
    public void clear() {

    }
}
