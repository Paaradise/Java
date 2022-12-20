package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel
{
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        var shapes = new Shape[]
        {
            new Rectangle2D.Float( 100,100,140,100 ),
            new Ellipse2D.Float( 100, 100, 200, 150 ),
            new Ellipse2D.Float( 140, 100, 200, 150)
        };
    }

    public void RectangleIntersectsEllipse(Graphics g, Shape rectangle, Shape ellipse)
    {
        var rectangleBounds = rectangle.getBounds2D();
        var ellipseBounds = ellipse.getBounds2D();

        g.drawString(rectangleBounds.intersects(ellipseBounds)
                ? "Shapes intersect"
                : "Shapes don't intersect" ,
                100,
                300);
    }

    public void RectangleContainsEllipse(Graphics g, Shape rectangle, Shape ellipse)
    {
        var rectangleBounds = rectangle.getBounds2D();
        var ellipseBounds = ellipse.getBounds2D();

        g.drawString(rectangleBounds.contains(ellipseBounds)
                ? "Rect contains ellipse"
                : "Rect doesn't contain ellipse" ,
                100,
                310);
    }

    public void EllipseContainsPoint(Graphics g, Shape ellipse)
    {
        var point = new Point2D.Float( 140, 140 );

        g.drawOval((int)point.x, (int)point.y, 5, 5);

        g.drawString(ellipse.contains(point)
                ? "Contains point"
                : "Does not contain point",
                100,
                320);
    }

    public void FilledRectangle(Graphics2D g, Shape rectangle)
    {
        var rect = (Rectangle2D) rectangle;

        g.setColor(Color.GREEN);
        g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
    }

    public void GradientEllipse(Graphics2D g, Shape ellipse)
    {
        var ell = (Ellipse2D) ellipse;

        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        var gradient = new GradientPaint(
                (float)ell.getX(), (float)ell.getY(), Color.GREEN,
                310, 190, Color.BLACK);

        g.setPaint(gradient);
        g.fill(ellipse);
    }

    public void RadialGradientEllipse(Graphics2D g, Shape ellipse)
    {
        var ell = (Ellipse2D) ellipse;

        var paint = new RadialGradientPaint(
                (float)ell.getCenterX(),
                (float)ell.getCenterY(),
                (float)ell.getWidth(),
                new float[] { 0.0f, 0.5f, 1.0f },
                new Color[] { Color.RED, Color.GREEN, Color.BLACK}
        );

        g.setPaint(paint);
        g.fill(ell);
    }

    public void ImageIntoEllipse(Graphics2D g, Shape ellipse) throws IOException
    {
        var path = "D:\\University\\3rd year\\Java\\src\\Graphics\\holyc.png";
        var image = ImageIO.read(new File(path));

        g.setClip(ellipse);
        g.draw(ellipse);
        g.drawImage(image, -200, -200, null);
    }

    public void BlendingEllipses(Graphics2D g, Shape firstEllipse, Shape secondEllipse)
    {
        g.setColor(new Color(255, 0, 0, 80));
        g.draw(firstEllipse);
        g.fill(firstEllipse);
        g.setColor(new Color(0, 0, 255, 80));
        g.draw(secondEllipse);
        g.fill(secondEllipse);
    }

    public void Arc(Graphics2D g)
    {
        g.setColor( Color.BLACK );
        g.drawArc( 15, 35, 100, 100, 25, 225 );
    }

    public void DifferentLines(Graphics2D g)
    {
        // First line
        //
        g.drawLine(100, 50, 100, 225);

        // Second line
        //
        g.setStroke(new BasicStroke(5));
        g.drawLine(120, 50, 120, 225);

        // Third line
        //
        g.drawRoundRect(140, 50, 3, 175, 5, 5);

        // Fourth line
        //
        g.drawRoundRect(160, 50, 3, 10, 5, 5);
        g.drawRoundRect(160, 70, 3, 25, 5, 5);
        g.drawRoundRect(160, 120, 3, 10, 5, 5);
        g.drawRoundRect(160, 140, 3, 25, 5, 5);
        g.drawRoundRect(160, 180, 3, 10, 5, 5);
        g.drawRoundRect(160, 200, 3, 25, 5, 5);
    }

    public void OverlappingTriangles(Graphics2D g)
    {
        var x = new int[] { 60, 135, 200 };
        var y = new int[] { 125, 50, 125 };

        var thinStroke = new BasicStroke( 2.25f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);

        g.setStroke(thinStroke);
        g.drawPolygon(x, y, 3);

        var scX = 1.75;
        var scY = 1.75;

        var transform = AffineTransform.getScaleInstance(scX, scY);
        var thickStroke = new BasicStroke( 3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);

        g.setStroke(thickStroke);
        g.setTransform(transform);
        g.translate(-20, -15);
        g.drawPolygon(x, y, 3);
    }

    public static void Test()
    {
        var board = new Board();
        var frame = new JFrame();

        frame.add( board );
        frame.setTitle( "Test" );
        frame.setSize( 400,400 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
    }
}
