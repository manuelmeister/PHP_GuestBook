package ch.post;

import ch.post.view.ViewController;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Controller {

    private List<Model> models;
    private Repository repository;
    private ViewController viewController;

    public void init() {
        this.models = new ArrayList<Model>();
        repository = new Repository();
        clearGetPosts();
    }

    public void addPosts(String username, String content) {
        if (!
                ((username.contains("'")) ||
                        (username.contains("\"")) ||
                        (username.contains(";")) ||
                        (content.contains("'")) ||
                        (content.contains("\"")) ||
                        (content.contains(";")) ||
                        (username.equals("")) ||
                        (content.equals(""))
                )) {
            //Update delimiter


            //create date string
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date today = new Date();
            String date = df.format(today);

            //Add Post to Repository
            repository.addPost(username, date, content);

            //Get Post from Repository back again with ID
            Model buffer = repository.getLatestPost();

            //Add Post to Modellist
            models.add(buffer);

            //Pass Post to View
            viewController.addPost(buffer);
            viewController.clearInputFields();
        } else {
            viewController.alert("Invalid Username or Content!", "Your username or content cannot contain any special characters and cannot be empty", Alert.AlertType.INFORMATION);

        }
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public Parent getView() {
        return viewController.getRoot();
    }

    public void search(String query) {
        if (!
                ((query.contains("'")) ||
                        (query.contains("\"")) ||
                        (query.contains(";")) ||
                        (query.isEmpty())
                )) {
            List<Model> results = repository.findPosts(query);
            viewController.clear();
            for (Model result : results) {
                viewController.addPost(result);
            }
        }else{
            viewController.alert("Invalid Characters ( \' , \" , ; ) or no searchterm","Your searchterm cannot contain any special characters and cannot be empty", Alert.AlertType.WARNING);
        }
    }

    public void clearGetPosts() {
        viewController.clear();
        this.models = repository.getPosts();
        for (Model i : models) {
            viewController.addPost(i);
        }
    }
}
