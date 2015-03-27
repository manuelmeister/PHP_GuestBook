package ch.post;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public void addPost(String username, String datum, String content){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO POSTS (USERNAME, SQL_DATE, MESSAGE) " +
                    "VALUES (username, datum, content);";
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
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM POSTS;" );
            while ( rs.next() ) {
                String username = rs.getString("username");
                String content = rs.getString("message");
                String datum = rs.getString("sql_date");
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
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM POSTS ORDER BY ID_Post DESC LIMIT 1;" );
            while ( rs.next() ) {
                String username = rs.getString("username");
                String content = rs.getString("message");
                String datum = rs.getString("sql_date");
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
