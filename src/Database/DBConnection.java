package Database;
import java.sql.*;

public class DBConnection implements IDB {
    @Override
    public Connection getConnection( String DB_NAME, String DB_USER, String DB_PASSWORD ) throws SQLException, ClassNotFoundException {
        try {
            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DB_NAME, DB_USER, DB_PASSWORD);
            System.out.println( "Successfully connected to db" );
            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
