package Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Grid extends JPanel implements MouseMotionListener
{
    Bar b;
    Ball a;
    BallEngine s;

    Grid()
    {
        super();
        addMouseMotionListener(this);

        b=new Bar(100);
        a=new Ball(this,100,100,1,1);
        s=new BallEngine(a, b);
        new CollisionDetector(a, b);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;

        g2d.fill(b);

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
