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

    @FXML private ScrollPane container;

    @FXML private VBox posts;

    @FXML private TextArea content;
    @FXML private TextField username;
    @FXML private Button submit;

    @FXML protected void submitButtonAction(MouseEvent mouseEvent){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy HH:mm");
        posts.getChildren().add(Elements.Post(content.getText(), username.getText(), dateFormat.format(date) ) );
        content.setText("");
        username.setText("");
    }

    /**
     * Adds a post to the UI
     * @param model
     */
    public void addPost(Model model) {

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
