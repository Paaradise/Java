package Arkanoid;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class Ball extends Ellipse2D.Float{
    Grid p;
    int dx,dy;

    Ball(Grid p,int x,int y,int dx,int dy)
    {
        this.x=x;
        this.y=y;
        this.width=10;
        this.height=10;

        this.p=p;
        this.dx=dx;
        this.dy=dy;
    }

    public void HandleCollision(Rectangle2D r)
    {
        int thisOutCode = r.outcode(this.x, this.y);
        //if ball is on left or right then change dx
        if ( ((thisOutCode & r.OUT_LEFT) == r.OUT_LEFT) || ((thisOutCode & r.OUT_RIGHT) == r.OUT_RIGHT))
        {
            this.dx *= -1;
        }

        //if ball is above or below then change dy
        if ( ((thisOutCode & r.OUT_TOP) == r.OUT_TOP) || ((thisOutCode & r.OUT_BOTTOM) == r.OUT_BOTTOM))
        {
            this.dy *= -1;
        }
    }

    void nextStep()
    {
        x+=dx;
        y+=dy;

        if(getMinX()<0 || getMaxX()>p.getWidth())  dx=-dx;
        if(getMinY()<0 || getMaxY()>p.getHeight()) dy=-dy;

        p.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}
