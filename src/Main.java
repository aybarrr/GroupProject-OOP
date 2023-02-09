import SnakeLogic.Map;

import java.awt.EventQueue;
import javax.swing.JFrame;

import java.util.Scanner;
public class Main extends JFrame{
    public Main() {
        snakeUI();
    }

    private void snakeUI() {
        add( new Map() );

        setResizable( false );
        pack();

        setTitle( "Snake" );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    public static void main( String[] args ) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to online SNAKE GAME!");
        System.out.println("[1] --> Start Game");
        System.out.println("[2] --> Sign Up");
        System.out.println("[3] --> Rating of Players");
        System.out.println("[4] --> open Admin page");
        System.out.println("[5] --> close app");
        System.err.println("enter key number: ");

        do {
            int n = in.nextInt();
            switch (n) {
                case 1 -> EventQueue.invokeLater(() -> {
                    JFrame ex = new Main();
                    ex.setVisible(true);
                });
                case 2 -> SignUp();
                case 3 -> RatingShow();
                case 4 -> Settings();
                case 5 -> Exit();
                default -> System.out.println("invalid number");
            }
        } while (true);
    }

    private static void SignUp(){
        System.out.println("Sign Up to the game!");
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
