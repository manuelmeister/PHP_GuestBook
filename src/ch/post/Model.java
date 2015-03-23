package ch.post;

import java.util.Date;


public class Model {
    private String username;
    private Date date;
    private String content;

    public Model (String username, String content) {
        this.username = username;
        this.content = content;
        this.date = new Date();
    }
}

