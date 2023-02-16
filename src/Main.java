import SnakeLogic.Map;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame{
    public Main() {
        snakeUI();
    }

    private void snakeUI() {
        Map map = new Map();
        add( map );

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