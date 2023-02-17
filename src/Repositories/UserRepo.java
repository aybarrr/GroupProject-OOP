package Repositories;

import Database.DBConnection;
import Database.IDB;
import Entities.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserRepo implements IUserRepo{
    private DBConnection db = new DBConnection();

    public UserRepo(DBConnection db) {
        this.db = db;
    }

//    @Override
//    public boolean CreateUserTable() {
//        Connection con = null;
//        try {
//            con = db.getConnection( "quandyq", "postgres", "Mechta.01!" );
//            String sql = "-- Table: public.Users\n" +
//                    "\n" +
//                    "-- DROP TABLE IF EXISTS public.\"Users\";\n" +
//                    "\n" +
//                    "CREATE TABLE IF NOT EXISTS public.\"Users\"\n" +
//                    "(\n" +
//                    "    id integer NOT NULL DEFAULT 'nextval('\"Users_id_seq\"'::regclass)',\n" +
//                    "    name character varying(50) COLLATE pg_catalog.\"default\",\n" +
//                    "    surname character varying(50) COLLATE pg_catalog.\"default\",\n" +
//                    "    nickname character varying(50) COLLATE pg_catalog.\"default\",\n" +
//                    "    level integer NOT NULL,\n" +
//                    "    CONSTRAINT \"Users_pkey\" PRIMARY KEY (id)\n" +
//                    ")\n" +
//                    "\n" +
//                    "TABLESPACE pg_default;\n" +
//                    "\n" +
//                    "ALTER TABLE IF EXISTS public.\"Users\"\n" +
//                    "    OWNER to postgres;";
//
//            PreparedStatement st = con.prepareStatement(sql);
//            st.execute();
//            return true;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                con.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return false;
//    }

    @Override
    public boolean createUser(User user) {
        Connection con = null;
        try {
            con = db.getConnection( "quandyq", "postgres", "Mechta.01!" );
            String sql = "INSERT INTO Users(name,surname,nickname) VALUES (?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getNickname());

            st.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public User getUser(int id) {
        Connection con = null;
        try {
            con = db.getConnection( "quandyq", "postgres", "Mechta.01!" );
            String sql = "SELECT name,surname,nickname FROM Users WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("nickname"));

                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection con = null;
        try {
            con = db.getConnection( "quandyq", "postgres", "Mechta.01!" );
            String sql = "SELECT id,name,surname,gender FROM users";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new LinkedList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("nickname"));

                users.add(user);
            }

            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
    @Override
    public void setLvl(User user, int lvl){
        Connection con = null;
        try {
            con = db.getConnection( "quandyq", "postgres", "Mechta.01!" );
            String sql = "INSERT INTO Users(level) VALUES (?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, user.getLevel());
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
