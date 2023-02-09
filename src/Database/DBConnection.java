package Database;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public Connection connect( String DB_NAME, String
            DB_USER, String DB_PASSWORD ) {
        Connection conn = null;
        try {
            Class.forName( "org.postgresql.Driver" );
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DB_NAME, DB_USER, DB_PASSWORD);
            if (conn != null) {
                System.out.println( "Connection Success!");
            } else {
                System.out.println( "Connection Failed" );
            }
        } catch ( Exception e ) {
            System.out.println( e );
        }
        return conn;
    }
}
