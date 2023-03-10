package SnakeLogic;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Map extends JPanel implements ActionListener {

    private final int B_WIDTH = 700;
    private final int B_HEIGHT = 700;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 2700;
    private int DELAY = 150;
    private String player;

    private final int[] x = new int[ALL_DOTS];
    private final int[] y = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Connection conn;

    public Map(String player, Connection conn) {
        setConn( conn );
        setPlayer( player );
        initBoard();
    }

    private void initBoard() {
        addKeyListener( new TAdapter() );
        setBackground( Color.black );
        setFocusable( true );

        setPreferredSize( new Dimension( B_WIDTH, B_HEIGHT ) );
        loadImages();
        initGame();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("src/Images/body.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("src/Images/apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("src/Images/head.png");
        head = iih.getImage();

    }

    private void initGame() {
        dots = 3;

        for ( int z = 0; z < dots; z++ ) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        locateApple();
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        try {
            doDrawing(g);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void doDrawing( Graphics g ) throws SQLException, ClassNotFoundException {
        String msg = "Player: " + player;
        Font small = new Font("Helvetica", Font.BOLD, 14);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, 20, 20);

        if (inGame) {
            g.drawImage( apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } else {
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over. Your level: " + ( DELAY - 150 );
        Font small = new Font( "Helvetica", Font.BOLD, 14 );
        FontMetrics metr = getFontMetrics( small );

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    public void setRating() {
        try {
            String sql = String.format( "INSERT INTO \"Rating\"(username, rating) VALUES ('%s','%s')", player, DELAY - 150 );
            Statement st = conn.createStatement();
            st.execute( sql );
            System.out.println( "Your score: " + ( DELAY - 150 ) + " is recorded" );
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {
            dots++;
            locateApple();
        }
    }

    private void move() {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }

        if (!inGame) {
            timer.stop();
        }
    }


    private void locateApple() {
        int RAND_POS = 30;
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));

        DELAY = DELAY + 1;
        timer = new Timer( DELAY, this );
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }

    }

    public void setPlayer( String  player ) {
        this.player = player;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}


