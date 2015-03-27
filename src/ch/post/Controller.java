package ch.post;
import ch.post.view.View;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Controller {

    private List<Model> models;
    private Repository repository;
    private View view;

    public Controller(Stage primarystage) throws Exception{
        this.models = new ArrayList<Model>();
        view = new View(primarystage);
        repository = new Repository();
        this.models = repository.getPosts();
        for (Model i : models) {
            view.addPost(i);
        }
    }

    public void addPosts(String username, String content){
        if (!
                ((username.contains("'")) ||
                (username.contains("\"")) ||
                (username.contains(";")) ||
                (content.contains("'")) ||
                (content.contains("\"")) ||
                (content.contains(";"))
        )) { //Update delimiter


            //create date string
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date today = new Date();
            String date = df.format(today);

            //Add Post to Repository
            repository.addPost(username, date, content);

            //Get Post from Repository back again with ID
            Model buffer = repository.getLatestPost();

            //Add Post to Modellist
            models.add(buffer);

            //Pass Post to View
            view.addPost(buffer);
        } else {
            view.alert("Invalid Username!","Your username cannot contain any special characters", Alert.AlertType.INFORMATION);
        }
    }

    public void updateView(){
        view.clear();
        for (Model i : models) {
            view.addPost(i);
        }
    }




}
