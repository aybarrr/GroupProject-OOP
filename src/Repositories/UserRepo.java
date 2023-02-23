package Repositories;

import Entities.User;
import Repositories.Interfaces.IUserRepo;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepo implements IUserRepo {
    private final Connection conn;

    public UserRepo( Connection conn ) {
        this.conn = conn;
    }

    @Override
    public boolean createUser(User user) {
        try {
            String sql = String.format( "INSERT INTO \"User\"(name,nickname,password) VALUES ('%s','%s','%s')", user.getName(), user.getNickname(), user.getPassword() );
            Statement st = conn.createStatement();
            st.execute( sql );
            return true;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean SignIn(User user) {
        int count = 0;
        try {
            String sql = String.format( "SELECT * FROM \"User\" WHERE name = '%s' AND nickname = '%s' AND password = '%s' ;", user.getName(), user.getNickname(), user.getPassword() );
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( sql );
            while ( rs.next() ) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return count > 0;
    }

    @Override
    public User getUser(int id) {
        try {
            String sql = "SELECT name,nickname,password FROM Users WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("name"),
                        rs.getString("nickname"),
                        rs.getString("password"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            String sql = "SELECT id,name,nickname FROM users";
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
        }
        return null;
    }

    public void setLvl(User user, int lvl){
        try {
            String sql = "INSERT INTO Users(level) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, user.getLevel());
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
