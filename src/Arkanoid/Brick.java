package Arkanoid;

import java.awt.geom.Rectangle2D;
import java.awt.*;

public class Brick extends Rectangle2D.Float {
    Grid p;
    Color c1;
    Color c2;
    Color c3;
    int lives;
    Brick (Grid p, int x, int y)
    {
        this.p = p;
        this.x = x;
        this.y = y;
        this.lives = 3;
        int fc = 255/3;
        this.c1 = new Color(fc, fc, fc);
        fc = 2 * fc;
        this.c2 = new Color(fc, fc, fc);
    }
}
