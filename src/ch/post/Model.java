package ch.post;

import java.util.Date;


public class Model {
    private String username;
    private String date;
    private String content;

    public Model (String username, String content, String date) {
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public String getUsername(){
        return username;
    }
    public String getDate(){
        return date;
    }
    public String getContent(){
        return content;
    }
}

