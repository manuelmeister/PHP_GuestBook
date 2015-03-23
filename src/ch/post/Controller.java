package ch.post;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
        models.addPosts(new Model(username, content, new Date()));
    }




}
