package Arkanoid;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

public class Ball extends Ellipse2D.Float{
    Grid p;
    int dx,dy;
    int counter = 0;

    final static int vmin = 1;
    final static int vmax = 3;

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

    public void HandleCollision(Rectangle2D r, boolean accelx)
    {
        int thisOutCode = r.outcode(this.x, this.y);
        //if ball is on left or right then change dx
        if ( ((thisOutCode & Rectangle2D.OUT_LEFT) == Rectangle2D.OUT_LEFT) || ((thisOutCode & Rectangle2D.OUT_RIGHT) == Rectangle2D.OUT_RIGHT))
        {
            this.dx *= -1;
            if (accelx) this.dx = vmax;
        }

        //if ball is above or below then change dy
        if ( ((thisOutCode & Rectangle2D.OUT_TOP) == Rectangle2D.OUT_TOP) || ((thisOutCode & Rectangle2D.OUT_BOTTOM) == Rectangle2D.OUT_BOTTOM))
        {
            this.dy *= -1;
            if (accelx)
            {
                int dist = (int)(Math.abs(this.getCenterX() - r.getCenterX()));
                if (dist >= 15) this.dx *=2;
                else this.dx /= 2;
            }

            if (Math.abs(this.dx) > vmax)
            {
                this.dx = vmax;
            }
        }
    }

    void nextStep()
    {
        x+=dx;
        y+=dy;

        if(getMinX()<0 || getMaxX()>p.getWidth())  dx=-dx;
        if(getMinY()<0 || getMaxY()>p.getHeight()) dy=-dy;

//        //deccel every 3 seconds
//        if (this.counter == 200)
//        {
//            if (this.dx < 0)
//            {
//                this.dx += 1;
//                if (this.dx == 0)
//                {
//                    this.dx = -1;
//                }
//            }
//            else
//            {
//                this.dx -= 1;
//                if (this.dx == 0)
//                {
//                    this.dx = 1;
//                }
//            }
//        }
//        this.counter += 1;

        p.repaint();
        Toolkit.getDefaultToolkit().sync();
    }
}
