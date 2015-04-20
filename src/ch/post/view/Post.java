package ch.post.view;

import ch.post.Model;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Created by manuelmeister on 20.04.15.
 */
public class Post extends BorderPane {

    private String content;
    private String username;
    private String date;
    private Model model;

    public Post(Model model){
        this.model = model;
        init();
    }

    public Post(String content, String username, String date) {
        this.content = content;
        this.username = username;
        this.date = date;
        init();
    }

    private void init() {
        this.getStyleClass().add("post");
        Text textContent = new Text(this.content);
        textContent.getStyleClass().addAll("content", "big-text");
        TextFlow textContainer = new TextFlow(textContent);

        Label labelUsername = new Label(this.username);
        labelUsername.getStyleClass().add("labelUsername");
        Label labelDate = new Label(this.date);
        labelUsername.getStyleClass().add("labelDate");

        HBox footer = new HBox();
        footer.getStyleClass().add("footer");
        footer.getChildren().addAll(labelUsername,labelDate);

        VBox post_inner = new VBox();
        post_inner.getStyleClass().add("post-inner");
        post_inner.getChildren().addAll(textContainer,footer);

        this.setCenter(post_inner);
    }
}
