package ch.post.view;

import ch.post.Model;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class View {

    @FXML private ScrollPane scrollPaneContainer;

    @FXML private VBox posts;

    @FXML private TextArea inputTextArea;
    @FXML private TextField inputUsername;
    @FXML private Button buttonSubmit;

    public View(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/view.fxml"));
        String css = this.getClass().getResource("view/style.css").toExternalForm();

        primaryStage.setTitle("GuestBook");
        Scene scene = new Scene(root, 768, 768);

        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

        AnimationTimer timer = new AnimationTimer() {
            public int counter = 4;

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
     * Displays a information popup using Model
     * @param message String
     */
    public void alert(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    /**
     * Displays a information popup
     * @param message String
     */
    public void alert(String message, String furtherInfos, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        alert.setContentText(furtherInfos);
        alert.showAndWait();
    }

    /**
     * Removes all posts from the View
     */
    public void clear() {
        posts.getChildren().clear();
    }
}
