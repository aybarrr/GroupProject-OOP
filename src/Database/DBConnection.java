package Database;
import java.sql.*;

import static java.lang.System.out;

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
                out.println( "Success" );
            } else {
                out.println( "Fail" );
            }

            assert conn != null;
            Statement st = conn.createStatement();
            String sql = """
                    CREATE TABLE IF NOT EXISTS \"User\" (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, nickname VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL);
                    CREATE TABLE IF NOT EXISTS \"Rating\" (id SERIAL PRIMARY KEY, username VARCHAR(255) NOT NULL, rating INT NOT NULL);
                    CREATE TABLE IF NOT EXISTS \"Admin\" (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL) ;
                    """;
            st.execute(sql);

        } catch (SQLException e) {
            out.println(e);
        }

        return conn;
    }
}
