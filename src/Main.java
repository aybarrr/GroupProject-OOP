import Controller.UserController;
import Database.DBConnection;
import Database.IDB;
import Repositories.IUserRepo;
import Repositories.UserRepo;
import SnakeLogic.Map;
import com.sun.jdi.connect.spi.Connection;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.sql.Statement;
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

    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        IUserRepo repo = new UserRepo(db);
        UserController controller = new UserController(repo);
        Myapp app = new Myapp(controller);
        app.start();
    }
}
