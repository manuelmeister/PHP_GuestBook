package ch.post;

import java.util.Date;


public class Model {
    private int ID;
    private String username;
    private Date date;
    private String content;

    public Model (int ID, String username, String content) {
        this.ID = ID;
        this.username = username;
        this.content = content;
        this.date = new Date();
    }
}

