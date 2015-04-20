package ch.post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private String databaseURI = "src/ch/post/database.db";

    public void addPost(String username, String datum, String content){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + databaseURI);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO Posts (Username, SQL_Date, Message)  VALUES ('"+username+"', '"+datum+"', '"+content+"');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

    public List<Model> getPosts(){

        Connection c = null;
        Statement stmt = null;
        List<Model> posts = new ArrayList<Model>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + databaseURI);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Posts;" );
            while ( rs.next() ) {
                String username = rs.getString("Username");
                String content = rs.getString("Message");
                String datum = rs.getString("SQL_Date");
                int ID = rs.getInt("ID_Post");
                posts.add(new Model(username, content, datum, ID));
            }
            rs.close();
            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return posts;
    }


    public Model getLatestPost(){

        Connection c = null;
        Statement stmt = null;
        Model post = new Model("[administrator]", "Beim einlesen des letzten Eintrags ist dem Programm ein Fehler unterlaufen", "[]", -1);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + databaseURI);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Posts ORDER BY ID_Post DESC LIMIT 1;" );
            while ( rs.next() ) {
                String username = rs.getString("Username");
                String content = rs.getString("Message");
                String datum = rs.getString("SQL_Date");
                int ID = rs.getInt("ID_Post");
                post = new Model(username, content, datum, ID);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return post;
    }

}
