package Arkanoid.Models;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import java.util.Map;

public class Brick extends Rectangle2D.Float
{
    int lives;

    private static final Map<Integer, Color> colors = Map.of(
        0, UIManager.getColor( "Panel.background" ),
        1, new Color( 50, 50, 50 ),
        2, new Color( 100, 100, 100 ),
        3, new Color( 150, 150, 150 )
    );

    public Brick( int x, int y, int lives )
    {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 20;
        this.lives = lives;
    }

    public Color getBrickColor()
    {
        return colors.get( this.lives );
    }

    public void HandleCollision()
    {
        if ( this.alive() )
        {
            this.lives -= 1;
        }
    }

    public boolean alive()
    {
        return this.lives > 0;
    }
}
