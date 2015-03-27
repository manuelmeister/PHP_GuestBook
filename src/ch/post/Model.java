package ch.post;

import java.util.Date;


public class Model {
    private String username;
    private String date;
    private String content;
    private int ID;

    public Model (String username, String content, String date, int ID) {
        this.username = username;
        this.content = content;
        this.date = date;
        this.ID = ID;
    }

    public String getUsername(){ return this.username; }
    public String getDate(){ return this.date; }
    public String getContent(){ return this.content; }
    public int getID(){ return this.ID; }


}

