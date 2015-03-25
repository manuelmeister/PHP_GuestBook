package ch.post.view;

import ch.post.Model;

import com.sun.javafx.tools.ant.Platform;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    /**

     * On click submit
     * @param mouseEvent MouseEvent
     */
    @FXML protected void submitButtonAction(MouseEvent mouseEvent){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy HH:mm");
        String dateString = dateFormat.format(date);
        addPost(inputTextArea.getText(), inputUsername.getText(), dateString);
        inputTextArea.setText("");
        inputUsername.setText("");

        AnimationTimer timer = new AnimationTimer() {
            public int counter = 5;

            @Override
            public void handle(long now) {
                scrollPaneContainer.setVvalue(scrollPaneContainer.getVmax());
                if ( this.counter <= 0){
                    this.stop();
                }else {
                    this.counter--;
                }
            }
        };
        timer.start();
    }



    /**
     * Add post using model
     * @param model Model
     */
    public void addPost(Model model) {
        addPost(model.getContent(), model.getUsername(), model.getDate());
    }

    /**
     * Add post using Strings
     * @param content String
     * @param username String
     * @param date String
     */
    public void addPost(String content, String username, String date){
        posts.getChildren().add(Elements.Post(content, username, date));
    }

    /**
     * Displays a information popup
     * @param message String
     */
    public void alert(String message) {

    }

    /**
     * Removes all posts from the View
     */
    public void clear() {

    }
}
