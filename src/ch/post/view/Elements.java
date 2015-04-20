package ch.post.view;

import ch.post.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class Elements {

    @FXML
    public static BorderPane Post(Model model){
        return Elements.Post(model.getContent(),model.getUsername(),model.getDate());
    }

    @FXML
    public static BorderPane Post(String content, String username, String date){
        BorderPane post = new BorderPane();
        post.getStyleClass().add("post");

        Text textContent = new Text(content);
        textContent.getStyleClass().addAll("content", "big-text");
        TextFlow textContainer = new TextFlow(textContent);

        Label labelUsername = Label(username, "labelUsername");
        Label labelDate = Label(date,"labelDate");

        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.getChildren().addAll(labelUsername,labelDate);

        VBox post_inner = new VBox();
        post_inner.getStyleClass().add("post-inner");
        post_inner.getChildren().addAll(textContainer,footer);

        post.setCenter(post_inner);
        return post;
    }

    public static Label Label(String content, String css_class){
        Label element = new Label(content);
        element.getStyleClass().add(css_class);
        return element;
    }
}
