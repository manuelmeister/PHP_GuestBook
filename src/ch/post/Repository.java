package ch.post;
import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private String databaseURI = "database.db";

    public void addPost(String username, String datum, String content){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:" + databaseURI);
            c.setAutoCommit(false);

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
    }

    public List<Model> getPosts(){

        Connection c = null;
        Statement stmt = null;
        List<Model> posts = new ArrayList<Model>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:" + databaseURI);
            c.setAutoCommit(false);

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
        return posts;
    }


    public Model getLatestPost(){

        Connection c = null;
        Statement stmt = null;
        Model post = new Model("[administrator]", "Beim einlesen des letzten Eintrags ist dem Programm ein Fehler unterlaufen", "[]", -1);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:" + databaseURI);
            c.setAutoCommit(false);

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
        return post;
    }

    public List<Model> findPosts(String query) {

        Connection c = null;
        Statement stmt = null;
        List<Model> posts = new ArrayList<Model>();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite::resource:" + databaseURI);
            c.setAutoCommit(false);

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Posts WHERE Message LIKE '%"+query+"%' OR Username LIKE '%"+query+"%' ORDER BY ID_Post DESC;" );
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
        return posts;
    }
}
