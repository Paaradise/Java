package Arkanoid;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;

public class Brick extends Rectangle2D.Float {
    Graphics2D g2d;
    Color c0;
    Color c1;
    Color c2;
    Color c3;
    int lives;
    Brick (int x, int y, int lives)
    {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 20;
        this.lives = lives;

        //dark gray color if brick has 3 lives
        int fc = 50;
        this.c3 = new Color(fc, fc, fc);

        //lighter gray if brick has 2 lives
        fc = 100;
        this.c2 = new Color(fc, fc, fc);

        //light gray if brick has 1 live
        fc = 150;
        this.c1 = new Color(fc, fc, fc);

        //color white if this brick is killed
        fc = 255;
        this.c0 = UIManager.getColor("Panel.background");
    }

    public Color getBrickColor()
    {
        if (this.lives == 3)
        {
            return this.c3;
        }

        if (this.lives == 2)
        {
            return this.c2;
        }

        if (this.lives == 1)
        {
            return this.c1;
        }

        return this.c0;

    }

    public void HandleCollision()
    {
        if (this.alive())
        {
            this.lives -= 1;
        }
    }

    public boolean alive()
    {
        if (this.lives > 0)
        {
            return true;
        }

        return false;
    }


}
