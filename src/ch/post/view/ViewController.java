package ch.post.view;

import ch.post.Controller;
import ch.post.Model;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class ViewController {

    @FXML private Parent root;
    @FXML private BorderPane contentArea;

    @FXML private TextField searchField;
    @FXML private Button resetSearchString;
    @FXML private Button searchButton;

    @FXML private VBox posts;
    @FXML private Button buttonSubmit;
    @FXML private TextField inputUsername;
    @FXML private TextArea inputTextArea;
    @FXML private ScrollPane scrollPaneContainer;

    private ViewController viewController;
    private Controller controller;

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public Parent getRoot() {
        return root;
    }

    public InputStream getResourceAsStream(String string){
        return getClass().getResourceAsStream(string);
    }


    @FXML
    public void searchButtonAction(MouseEvent event){
        controller.search(searchField.getText());
    }

    @FXML
    public void submitButtonAction(MouseEvent event){
        controller.addPosts(inputUsername.getText(), inputTextArea.getText());
    }

    @FXML
    public void resetButtonAction(MouseEvent event){
        searchField.setText("");
        controller.clearGetPosts();
    }

    @FXML
    public void searchFieldChanged(){
        controller.search(searchField.getText());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void addPost(Model model){
        posts.getChildren().add(new Post(model.getContent(),model.getUsername(),model.getDate()));
        AnimationTimer timer = new AnimationTimer() {
            public int counter = 15;

            @Override
            public void handle(long now) {
                scrollPaneContainer.setVvalue(scrollPaneContainer.getVvalue() + ((scrollPaneContainer.getVmax()-scrollPaneContainer.getVvalue())/4));
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

    public void clearInputFields() {
        inputTextArea.setText("");
        inputUsername.setText("");
    }

    public void clear() {
        posts.getChildren().clear();
    }
}
