package Arkanoid.Models;

import java.awt.geom.Rectangle2D;

public class Bar extends Rectangle2D.Float
{
    public Bar( int x )
    {
        this.x = x;
        this.y = 270;
        this.width = 60;
        this.height = 10;
    }

    void setX( int x )
    {
        this.x = x;
    }
}
