import Controller.AdminController;
import Controller.UserController;
import Entities.User;
import SnakeLogic.Map;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Myapp {
    private static UserController controller = null;
    private static AdminController controllerAdmin = null;

    private static Map map;
    private static Connection conn;


    public Myapp(UserController controller, AdminController controllerAdmin, Map map, Connection conn) {
        this.conn = conn;
        this.map = map;
        Myapp.controller = controller;
        Myapp.controllerAdmin = controllerAdmin;
    }

    public void start() throws SQLException {
        System.out.println("Welcome to online SNAKE GAME!");

        do {
            Scanner in = new Scanner(System.in);
            System.out.println("[1] --> Start Game");
            System.out.println("[2] --> Sign Up");
            System.out.println("[3] --> Rating of Players");
            System.out.println("[4] --> open Admin page");
            System.out.println("[5] --> close app");
            System.err.println("enter key number: ");

            int n = in.nextInt();
            switch (n) {
                case 1 -> StartGame();
                case 2 -> SignUp();
                case 3 -> RatingShow();
                case 4 -> Settings();
                case 5 -> Exit();
                default -> System.out.println("invalid number");
            }
        } while (true);
    }
    private static void StartGame() {
        Scanner in = new Scanner(System.in);
        System.out.println("[1] --> Start Game as Guest");
        System.out.println("[2] --> Sign In to Account");

        do {
            int n = in.nextInt();
            switch (n) {
                case 1 -> StartGameAsGuest();
                case 2 -> SignIn();
                default -> System.out.println("invalid number");
            }
        } while (true);
    }


    private static void StartGameAsGuest(){
        EventQueue.invokeLater(() -> {
            JFrame ex;
            try {
                ex = new Main( "Guest" );
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            ex.setVisible(true);
        });

        map.setRating();
    }


    private static void SignUp(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input your name: ");
        String name = in.nextLine();
        System.out.println("Input nickname: ");
        String nickname = in.nextLine();
        System.out.println("Input password: ");
        String password = in.nextLine();

        String response = controller.createUser(name, nickname, password);
        System.out.println(response);
    }

    private static void SignIn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Input your name: ");
        String name = in.nextLine();
        System.out.println("Input nickname: ");
        String nickname = in.nextLine();
        System.out.println("Input your password: ");
        String password = in.nextLine();

        String response = controller.SingIn(name, nickname, password);
        System.out.println(response);
        String signedIn = "User was Signed in!";
        if (response.equals((signedIn))) {
            System.out.println("Starting as: " + nickname);
            EventQueue.invokeLater(() -> {
                JFrame ex;
                try {
                    ex = new Main(nickname);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                ex.setVisible(true);
            });

            map.setRating();
        } else {
            System.out.println("No such user");
        }
    }

        private static void RatingShow () throws SQLException {
            try {
                String sql = "SELECT * FROM \"Rating\" ORDER BY rating DESC";
                Statement st = conn.createStatement();

                ResultSet rs = st.executeQuery(sql);

                int count = 1;
                System.out.println( "Top 10 users!" );

                while (rs.next()) {
                    System.out.println( count + ": " + rs.getString( "username" ) + " with score: " + rs.getString( "rating" ) );
                    if( count == 10 ) break;
                    count++;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        private static void Settings () {
            Scanner inSet = new Scanner(System.in);
            System.out.println("Input your name: ");
            String nameAdmin = inSet.nextLine();
            System.out.println("Input password: ");
            String passwordAdmin = inSet.nextLine();

            String responseAdmin = controllerAdmin.SignIn(nameAdmin, passwordAdmin);
            System.out.println(responseAdmin);
            String pos = "Admin was Signed in!";
            inSet.close();
            if (responseAdmin.equals(pos)) {
                System.out.println("Welcome to Settings");

                do {
                    Scanner in = new Scanner(System.in);
                    System.out.println("[1] --> First Setting");
                    System.out.println("[2] --> Second setting");
                    System.out.println("[3] --> Exit");
                    System.err.println("enter key number: ");

                    int n = in.nextInt();
                    switch (n) {
                        case 1 -> System.out.println("First setting is selected");
                        case 2 -> System.out.println("Second selected");

                        case 3 -> Exit();
                        default -> System.out.println("invalid number");
                    }
                } while (true);
            }

        }
        private static void Exit () {
            System.out.println("Closing game...");
        }


}

