/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Siva
 */
public class Pong extends Applet implements Runnable, KeyListener {
    final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    Paddle1 p1;
    AIPaddle p2;
    Ball b1;
    boolean gameStarted;
    Graphics gfx;
    Image img;
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    public void init() {
        this.resize(WIDTH, HEIGHT);
        this.addKeyListener(this);
        gameStarted = false;
        p1 = new Paddle1(2);
        b1 = new Ball();
        p2 = new AIPaddle(1, b1);
        //img = createImage(WIDTH, HEIGHT);
        //gfx = img.getGraphics();
        thread = new Thread(this);
        thread.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if(!gameStarted) {
            g.setColor(Color.WHITE);
            g.drawString("Pong", 340, 100);
            g.drawString("Press Enter to Begin..", 310, 130);
        }
        else {
            if(b1.getX() < -10 || b1.getX() > 710) {
                g.setColor(Color.white);
                g.drawString("Game Over", 350, 250);
            }
            else {
                p1.draw(g);
                b1.draw(g);
                p2.draw(g);
                g2d.setColor(Color.white);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
            }
        }
        //g.drawImage(img, 0, 0, this);
    }
    
    public void update(Graphics g) {
        paint(g);
    }

    public void run() {
        for(;;) {
            if(gameStarted) {
                p1.move();
                p2.move();
                b1.move();
                b1.checkPaddleCollision(p2, p1);
            }
            repaint();
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER){
            gameStarted = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            p1.setUpAccel(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            p1.setDownAccel(false);
        }
    }
}
