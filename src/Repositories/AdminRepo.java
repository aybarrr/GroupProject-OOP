package Repositories;


import Entities.Admin;
import Repositories.Interfaces.IAdminRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminRepo implements IAdminRepo {
    private Connection conn;
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
        } finally {
            try {
                conn.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        if( count > 0 ) {
            return  true;
        } else {
            return false;
        }

    }
}
