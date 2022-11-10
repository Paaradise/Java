package Geometry;

public class Rectangle
{
    public Point centre;

    public double length;

    public double height;

    public Rectangle( double length, double height )
    {
        this.length = length;
        this.height = height;
        this.centre = new Point( 0, 0 );
    }

    public Rectangle( double length, double height, Point centre )
    {
        this.length = length;
        this.height = height;
        this.centre = centre;
    }

    public double getArea()
    {
        return this.length * this.height;
    }

    public double getCircumference()
    {
        return 2 * ( this.length + this.height );
    }

    public void translate( double u, double v )
    {
        this.centre.translate( u, v );
    }

    public boolean contains( Point point )
    {
        double x1 = this.centre.x - this.length / 2;
        double y1 = this.centre.y - this.height / 2;

        double x2 = this.centre.x + this.length / 2;
        double y2 = this.centre.y + this.height / 2;

        return point.x >= x1 && point.x <= x2 && point.y >= y1 && point.y <= y2;
    }

    public boolean intersects( Circle circle )
    {
        double dx = Math.abs( circle.centre.x - this.centre.x );
        double dy = Math.abs( circle.centre.y - this.centre.y );

        if ( dx > this.length / 2 + circle.radius )
        {
            return false;
        }

        if ( dy > this.height / 2 + circle.radius )
        {
            return false;
        }

        if ( dx <= this.length / 2 )
        {
            return true;
        }

        if ( dy <= this.height / 2 )
        {
            return true;
        }

        double corner_x = Math.pow( circle.centre.x - this.length / 2, 2);
        double corner_y = Math.pow( circle.centre.y - this.height / 2, 2);

        return corner_x + corner_y <= Math.pow( circle.radius, 2 );
    }

    @Override
    public String toString()
    {
        return "[Length: " + this.length + ", Height: " + this.height + "]\t" + this.centre.toString();
    }
}
