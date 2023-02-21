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
    public boolean createAdmin(Admin admin) {
        try {
            String sql = String.format( "INSERT INTO \"Admin\"(name,password) VALUES ('%s','%s')", admin.getUsername(), admin.getPassword() );
            Statement st = conn.createStatement();
            st.execute( sql );
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean SignIn(Admin admin) {
        int count = 0;
        try {
            String sql = String.format( "SELECT * FROM \"Admin\" WHERE name = '%s' AND password = '%s';", admin.getUsername(), admin.getPassword());
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( sql );
            while ( rs.next() ) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if( count > 0 ) {
            return  true;
        } else {
            return false;
        }

    }
}
