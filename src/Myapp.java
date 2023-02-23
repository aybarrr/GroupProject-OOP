import Controller.AdminController;
import Controller.UserController;
import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Myapp {
    private static UserController controller = null;
    private static AdminController controllerAdmin = null;
    private final Scanner scanner;


    public Myapp(UserController controller, AdminController controllerAdmin) {
        this.controller = controller;
        this.controllerAdmin = controllerAdmin;
        scanner = new Scanner(System.in);
    }

    public void start() {
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
            JFrame ex = new Main( "Guest" );
            ex.setVisible(true);
        });
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
                JFrame ex = new Main(nickname);
                ex.setVisible(true);
            });
        } else {
            System.out.println("No such user");
        }
    }

        private static void RatingShow () {
            System.out.println("Showing Rating...");
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

