import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class sqlConnect {

    static java.sql.Connection conn;

    public static void createConnection() {
        try {
            try{
                Class.forName("org.sqlite.JDBC");
            }catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }

            String url = "jdbc:sqlite:BookReview.db";
            conn = DriverManager.getConnection(url);
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void killConnection() {
        if(conn != null) {
            try{
                conn.close();
            }catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
