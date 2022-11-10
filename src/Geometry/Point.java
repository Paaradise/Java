package Geometry;

public class Point
{
    public double x;

    public double y;

    public Point( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    public void translate( double dx, double dy )
    {
        this.x += dx;
        this.y += dy;
    }

    public double distance( Point point )
    {
        return Math.sqrt( Math.pow( ( point.x - this.x ), 2 ) + Math.pow( ( point.y - this.y ), 2 ) );
    }

    @Override
    public String toString()
    {
        return "(" + this.x + "; " + this.y + ")";
    }
}