package Repositories;


import Repositories.Interfaces.IAdminRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminRepo implements IAdminRepo {
    private final Connection conn;
    public AdminRepo(Connection conn){this.conn = conn;}


    @Override
    public boolean SignIn(String name, String password) {
        int count = 0;
        try {
            String sql = String.format( "SELECT * FROM \"Admin\" WHERE name = '%s' AND password = '%s';", name, password);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( sql );
            while ( rs.next() ) {
                count++;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return count > 0;

    }
}
