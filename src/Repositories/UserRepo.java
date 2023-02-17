package Repositories;

import Database.IDB;
import Entities.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepo implements IUserRepo{
    private final Connection conn;

    public UserRepo( Connection conn ) {
        this.conn = conn;
    }

    @Override
    public boolean createUser(User user) {
        try {
            String sql = String.format( "INSERT INTO Users(name,nickname) VALUES (%s,?%s)", user.getName(), user.getNickname() );
            Statement st = conn.createStatement();
            st.execute( sql );
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public User getUser(int id) {
        try {
            String sql = "SELECT name,surname,nickname FROM Users WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("nickname"));

                return user;
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
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            String sql = "SELECT id,name,surname,gender FROM users";
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("nickname"));

                users.add(user);
            }

            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public void setLvl(User user, int lvl){
        try {
            String sql = "INSERT INTO Users(level) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, user.getLevel());
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
