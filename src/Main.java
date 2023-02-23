import Controller.AdminController;
import Controller.UserController;
import Database.DBConnection;
import Repositories.AdminRepo;
import Repositories.Interfaces.IAdminRepo;
import Repositories.Interfaces.IUserRepo;
import Repositories.UserRepo;
import SnakeLogic.Map;
import javax.swing.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;

public class Main extends JFrame {
    private static UserController controller;
    private Scanner scanner;

    public Main() {
        snakeUI();
    }

    private void snakeUI() {
        add(new Map());

        setResizable(false);
        pack();


        setTitle("Snake Online Game");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBConnection db = new DBConnection();
        Connection conn = db.getConnection( "oop", "postgres", "Babahan2004" );

        IUserRepo repo = new UserRepo( conn );
        UserController controller = new UserController(repo);

        IAdminRepo repoAdmin = new AdminRepo( conn );
        AdminController controllerAdmin = new AdminController(repoAdmin);

        Myapp app = new Myapp(controller, controllerAdmin);
        app.start();
    }
}
