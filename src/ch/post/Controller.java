package ch.post;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.DateFormat;

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
        if ((username.contains("'")) || (username.contains("\"")) || (username.contains(";"))){ //Update delimiter

        }

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date today = new Date();
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
        String reportDate = df.format(today);




        String date =
        Model buffer = new Model(username, content, date);
        models.add(buffer);
        repository.addPost(buffer);
        view.addPost(buffer);
    }

    public void updateView(){
        view.clear();
        for (Model i : models) {
            view.addPost(i);
        }
    }




}
