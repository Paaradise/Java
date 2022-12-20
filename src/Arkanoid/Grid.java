package Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Grid extends JPanel implements MouseMotionListener
{
    Bar b;
    Ball a;
    BallEngine s;
    ArrayList<Brick> brickList = new ArrayList<>(20);

    Grid()
    {
        super();
        addMouseMotionListener(this);

        b=new Bar(100);
        a=new Ball(this,100,120,1,1);

        int initialx = 65;
        int dx = 50;

        int initialy = 20;
        int dy = 25;

        int l = 3;

        for (int row=0; row<3; row++)
        {
            for (int column=0; column<5; column++)
            {
                brickList.add(new Brick(initialx + column*dx, initialy + row*dy, 3-row));
            }
        }
        s=new BallEngine(a, b);
        new CollisionDetector(a, b, brickList);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;

        g2d.fill(b);

        for (Brick brick : brickList)
        {
            g2d.setPaint(brick.getBrickColor());
            g2d.fill(brick);
        }

        g2d.setPaint(Color.RED);
        g2d.fill(a);
    }

    public void mouseMoved(MouseEvent e)
    {
        b.setX(e.getX()-50);
        repaint();
    }

    public void mouseDragged(MouseEvent e)
    {

    }
}
