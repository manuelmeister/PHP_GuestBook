package ch.post;
import java.sql.*;

/**
 * Created by vmadmin on 16.03.2015.
 */
public class Repository {

    private String file;

    public Repository(String file) {
        this.file = file;
    }

    public void updateDatabase(String username, Date date, String content){

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO POSTS (USERNAME, DATE, MESSAGE) " +
                    "VALUES (username, date, content);";
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

    public void getPosts(){

    }

}
