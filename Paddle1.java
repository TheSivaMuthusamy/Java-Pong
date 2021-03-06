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
public class Paddle1 implements Paddle {
    double y, yVel;
    boolean upAccel, downAccel;
    final double FRICTION = 0.94;
    int player, x, score;
   
    
    public Paddle1(int player) {
        upAccel = false; downAccel = false;
        y= 210; yVel = 0;
        score = 0;
        if (player == 1)
            x = 20;
        else 
            x= 660;
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, (int)y, 20, 80);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 48));
        g.drawString(Integer.toString(getScore()), 525, 50);
    }

    public void move() {
        if(upAccel) {
            yVel -= 2;
        }
        
        else if(downAccel) {
            yVel += 2;
        }
        
        else if(!upAccel && !downAccel) {
            yVel *= FRICTION;
        }
        
        if(yVel >= 5)
            yVel = 5;
        else if (yVel <= -5)
            yVel = -5;
        y += yVel;
        
        if(y < 0)
            y = 0;
        if(y > 420)
            y = 420;
    }

    public void setUpAccel(boolean input) {
        upAccel = input;
    }
    
    public void setDownAccel(boolean input) {
        downAccel = input;
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
