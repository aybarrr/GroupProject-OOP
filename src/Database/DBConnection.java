package Database;
import java.sql.*;

public class DBConnection implements IDB {
    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/simpledb";
        try {
            // Here we load the driver’s class file into memory at the runtime
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection con = DriverManager.getConnection(connectionUrl, "postgres", "Babahan2004");

            return con;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void search_by_name(Connection conn, String table_name,String name){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s where name= '%s'",table_name,name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("id")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.print(rs.getString("surname")+" ");
                System.out.print(rs.getString("nickname")+" ");
                System.out.println(rs.getString("level"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
