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
                posts.add(new Model(username, content, datum));
                return posts;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }


    }

}
