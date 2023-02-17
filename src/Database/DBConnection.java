package Database;
import java.sql.*;

public class DBConnection implements IDB {
    @Override
    public Connection getConnection( String DB_NAME, String DB_USER, String DB_PASSWORD ) throws SQLException, ClassNotFoundException {
        Connection conn = null;

        try {
            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DB_NAME, DB_USER, DB_PASSWORD);

            if( conn != null ) {
                System.out.println( "Success" );
            } else {
                System.out.println( "Fail" );
            }

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null , "User", null);
            Statement st = conn.createStatement();

            if( !tables.next() ) {
                String sql = "CREATE TABLE \"User\" (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, nickname VARCHAR(255) NOT NULL);";
                st.execute(sql);
                System.out.println("Table User created successfully");
            } else {
                System.out.println("Table User exists.");
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return conn;
    }
}
