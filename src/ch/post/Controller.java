package ch.post;
import ch.post.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;




public class Controller {

    private List<Model> models;
    private Repository repository;
    private View view;

    public Controller(){
        this.models = new ArrayList<Model>();
        view = new View();
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

            Model buffer = new Model(username, content, date);
            models.add(buffer);
            repository.addPost(buffer.getUsername(), buffer.getDate(), buffer.getContent());
            view.addPost(buffer);
        } else {
            view.alert();
        }
    }

    public void updateView(){
        view.clear();
        for (Model i : models) {
            view.addPost(i);
        }
    }




}
