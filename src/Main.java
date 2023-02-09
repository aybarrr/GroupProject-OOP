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

        setTitle( "SNAKE ONLINE GAME!" );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    public static void main( String[] args ) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Main();
            ex.setVisible( true );
        });
    }
}