import Controller.AdminController;
import Controller.UserController;
import Database.DBConnection;
import Repositories.AdminRepo;
import Repositories.Interfaces.IAdminRepo;
import Repositories.Interfaces.IUserRepo;
import Repositories.UserRepo;
import SnakeLogic.Map;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends JFrame {
    static DBConnection db = new DBConnection();
    static Connection conn;

    static {
        try {
            conn = db.getConnection( "oop", "postgres", "Mechta.01!" );
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Map map = new Map( "Guest", conn );

    public Main( String player ) throws SQLException, ClassNotFoundException {
        snakeUI( player );
    }

    private void snakeUI( String player ) {
        map.setPlayer( player );
        add( map );

        setResizable(false);
        pack();

        setTitle("Snake Online Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws SQLException {
        IUserRepo repo = new UserRepo( conn );
        UserController controller = new UserController(repo);

        IAdminRepo repoAdmin = new AdminRepo( conn );
        AdminController controllerAdmin = new AdminController(repoAdmin);

        Myapp app = new Myapp(controller, controllerAdmin, map, conn);
        app.start();
    }
}
