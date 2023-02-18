package Database;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDB {
    Connection getConnection( String DB_USER, String DB_NAME, String DB_PASSWORD ) throws SQLException, ClassNotFoundException;
}
