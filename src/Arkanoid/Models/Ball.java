package Arkanoid.Models;

import Arkanoid.Grid;

import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

public class Ball extends Ellipse2D.Float
{
    Grid grid;

    int dx;

    int dy;

    final static int vmax = 3;

    public Ball( Grid grid, int x, int y, int dx, int dy )
    {
        this.x=x;
        this.y=y;

        this.dx=dx;
        this.dy=dy;

        this.grid=grid;

        this.width=10;
        this.height=10;
    }

    public void HandleCollision( Rectangle2D rectangle, boolean accelerateOnXAxis )
    {
        var position = rectangle.outcode( this.x, this.y );

        var positionedToLeft = ( position & Rectangle2D.OUT_LEFT ) == Rectangle2D.OUT_LEFT;
        var positionedToRight = ( position & Rectangle2D.OUT_RIGHT ) == Rectangle2D.OUT_RIGHT;

        if ( positionedToLeft || positionedToRight )
        {
            this.dx *= -1;

            if ( accelerateOnXAxis )
            {
                this.dx = vmax;
            }
        }

        var positionedToTop = ( position & Rectangle2D.OUT_TOP ) == Rectangle2D.OUT_TOP;
        var positionedToBottom = ( position & Rectangle2D.OUT_BOTTOM ) == Rectangle2D.OUT_BOTTOM;

        if ( positionedToTop || positionedToBottom )
        {
            this.dy *= -1;

            if ( accelerateOnXAxis )
            {
                var dist = ( int )( Math.abs( this.getCenterX() - rectangle.getCenterX() ) );

                if ( dist >= 15 )
                {
                    if ( this.dx == 0 )
                    {
                        this.dx = 1;
                    }
                    else
                    {
                        this.dx *= 2;
                    }
                }
                else
                {
                    this.dx /= 2;
                }
            }

            if ( Math.abs( this.dx ) > vmax )
            {
                this.dx = vmax;
            }
        }
    }

    public void nextStep()
    {
        this.x += this.dx;
        this.y += this.dy;

        if ( getMinX() < 0 || getMaxX() > this.grid.getWidth() )
        {
            this.dx = -this.dx;
        }

        if ( getMinY() < 0 || getMaxY() > this.grid.getHeight() )
        {
            this.dy = -this.dy;
        }

        this.grid.repaint();

        Toolkit.getDefaultToolkit().sync();
    }
}
