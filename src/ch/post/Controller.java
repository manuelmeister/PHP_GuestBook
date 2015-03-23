package ch.post;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<Model> models;
    private View view;

    public Controller(List<Model> models){
        this.models = models;
        view = new View();
    }
    public Controller(){
        this.models = new ArrayList<Model>();
        view = new View();
    }

    public void add(String username, String content){
        models.add(new Model(username, content));
    }



}
