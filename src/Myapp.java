import Controller.UserController;
import Database.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Myapp {
    private static UserController controller = null;
    private final Scanner scanner;


    public Myapp(UserController controller) {
        this.controller = controller;
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


    private static void StartGameAsGuest(){ EventQueue.invokeLater(() -> {
        JFrame ex = new Main();
        ex.setVisible(true);
    });
    }
    private static void SignUp(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input your name: ");
        String name = in.nextLine();
        System.out.println("Input nickname: ");
        String nickname = in.nextLine();

        String response = controller.createUser(name, nickname);
        System.out.println(response);
    }

    private static void SignIn(){
        Scanner in = new Scanner(System.in);
        System.out.println("Input your name: ");
        String name = in.nextLine();
        System.out.println("Input nickname: ");
        String nickname = in.nextLine();

        String response = controller.SingIn(name, nickname);
        System.out.println(response);
    }

    private static void RatingShow(){
        System.out.println("Showing Rating...");
    }
    private static void Settings(){
        System.out.println("Settings...");
    }
    private static void Exit(){
        System.out.println("Closing game...");
    }


}

