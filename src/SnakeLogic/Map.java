package SnakeLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Map extends JPanel implements ActionListener {

//  MAP PARAMETERS, THESE VARIABLES CAN BE EDITED THROUGH THE ADMIN PANEL
    private int MAP_WIDTH = 500;
    private int MAP_HEIGHT = 500;
    private int BLOCK_SIZE = 10;
    private int ALL_BLOCKS = 900;
    private int DELAY = 140;

    //  ALL COORDINATES OF EACH PART OF THE SNAKE
    private final int x[] = new int[ALL_BLOCKS];
    private final int y[] = new int[ALL_BLOCKS];

    private int dots;
    private int apple_x;
    private int apple_y;


//  VARIABLES FOR DETERMINING THE DIRECTION OF THE SNAKE
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;

    private boolean inGame = true;

//  THE MAIN OBJECTS OF THE GAME
    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;


//  CONSTRUCTOR
    public Map() {
        initBoard();
    }

//  MAP RENDERING AND IMPLEMENTATION OF ALL ITS PARAMETERS
    private void initBoard() {
        addKeyListener( new TAdapter() );
        setBackground( Color.WHITE );
        setFocusable( true );

        setPreferredSize( new Dimension( MAP_WIDTH, MAP_HEIGHT ) );
        loadImages();
        initGame();
    }

//  GETTING ALL THE IMAGES FROM PACKAGE
    private void loadImages() {
        ImageIcon body = new ImageIcon( "src/Images/body.png" );
        ball = body.getImage();

        ImageIcon alma = new ImageIcon( "src/Images/apple.png" );
        apple = alma.getImage();

        ImageIcon bas = new ImageIcon( "src/Images/head.png" );
        head = bas.getImage();
    }


//  THE MAIN LOGIC OF THE SNAKE MOVEMENT
    private void initGame() {
        dots = 3;

        for ( int z = 0; z < dots; z++ ) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();

        timer = new Timer( DELAY, this );
        timer.start();
    }

    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );

        doDrawing(g);
    }
//  RENDER SNAKE DOT BY DOT AND RENDER APPLE
    private void doDrawing( Graphics g ) {
        if ( inGame ) {

            g.drawImage( apple, apple_x, apple_y, this );

            for ( int z = 0; z < dots; z++ ) {
                if ( z == 0 ) {
                    g.drawImage( head, x[z], y[z], this );
                } else {
                    g.drawImage( ball, x[z], y[z], this );
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver( g );
        }
    }

//  THIS FUNCTION IS CALLED WHEN THE SNAKE COLLIDES WITH ITSELF OR WITH A WALL
    private void gameOver( Graphics g ) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, ( MAP_WIDTH - metr.stringWidth(msg)) / 2, MAP_HEIGHT / 2 );
    }

//  THE LOGIC OF COLLIDING WITH APPLES
    private void checkApple() {
        if ( ( x[0] == apple_x ) && ( y[0] == apple_y ) ) {
            dots++;
            locateApple();
        }
    }


//  MAIN MOVEMENT LOGIC
    private void move() {

        for ( int z = dots; z > 0; z-- ) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if ( leftDirection ) {
            x[0] -= BLOCK_SIZE;
        }

        if ( rightDirection ) {
            x[0] += BLOCK_SIZE;
        }

        if ( upDirection ) {
            y[0] -= BLOCK_SIZE;
        }

        if ( downDirection ) {
            y[0] += BLOCK_SIZE;
        }
    }


//  PROCESSING OF ALL POSSIBLE OUTCOMES OF DEFEAT
    private void checkCollision() {
        for ( int z = dots; z > 0; z-- ) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                break;
            }
        }

        if ( y[0] >= MAP_HEIGHT ) {
            inGame = false;
        }

        if ( y[0] < 0 ) {
            inGame = false;
        }

        if ( x[0] >= MAP_WIDTH ) {
            inGame = false;
        }

        if ( x[0] < 0 ) {
            inGame = false;
        }

        if ( !inGame ) {
            timer.stop();
        }
    }

    private void locateApple() {
        int RAND_POS = 29;
        int r = ( int ) ( Math.random() * RAND_POS);
        apple_x = ( ( r * BLOCK_SIZE ) );

        r = ( int ) ( Math.random() * RAND_POS);
        apple_y = ( ( r * BLOCK_SIZE ) );
    }

    @Override
    public void actionPerformed( ActionEvent e ) {
        if ( inGame ) {
            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed( KeyEvent e ) {
            int key = e.getKeyCode();

            if ( ( key == KeyEvent.VK_LEFT ) && ( !rightDirection ) ) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ( ( key == KeyEvent.VK_RIGHT ) && ( !leftDirection ) ) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ( ( key == KeyEvent.VK_UP ) && ( !downDirection ) ) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ( ( key == KeyEvent.VK_DOWN ) && (!upDirection ) ) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

//  SETTERS TO MAIN PARAMETERS OF THE GAME
    public void set_MAP_WIDTH( int MAP_WIDTH ) {
        this.MAP_WIDTH = MAP_WIDTH;
    }

    public void set_MAP_HEIGHT( int MAP_HEIGHT ) {
        this.MAP_HEIGHT = MAP_HEIGHT;
    }

    public void set_BLOCK_SIZE( int BLOCK_SIZE ) {
        this.BLOCK_SIZE = BLOCK_SIZE;
    }

    public void set_ALL_BLOCKS( int ALL_BLOCKS ) {
        this.ALL_BLOCKS = ALL_BLOCKS;
    }

    public void set_DELAY( int DELAY ) {
        this.DELAY = DELAY;
    }
}
