/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Siva
 */
public class AIPaddle implements Paddle {
    double y, yVel;
    boolean upAccel, downAccel;
    final double FRICTION = 0.94;
    int player, x, score;
    Ball b1;
    
    public AIPaddle(int player, Ball b) {
        upAccel = false; downAccel = false;
        b1 = b;
        y= 210; yVel = 0;
        if (player == 1)
            x = 20;
        else 
            x= 660;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int)y, 20, 80);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 48));
        g.drawString(Integer.toString(getScore()), 175, 50);
    }

    public void move() {
        y = b1.getY() - 40;
        
        if(y < 0)
            y = 0;
        if(y > 420)
            y = 420;
    }
    
    public int getY() {
        return (int)y;
    }
    
    public int getScore() {
        return score;
    }
    
    public void increaseScore() {
        score++;
    }
    
}
