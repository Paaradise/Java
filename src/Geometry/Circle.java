package Geometry;

public class Circle
{
    public Point centre;

    public double radius;

    public Circle( double radius )
    {
        this.radius = radius;
        this.centre = new Point( 0, 0 );
    }

    public Circle( double radius, Point centre )
    {
        this.centre = centre;
        this.radius = radius;
    }

    public double getArea()
    {
        return Math.PI * this.radius * this.radius;
    }

    public double getCircumference()
    {
        return Math.PI * this.radius * 2;
    }

    public boolean contains( Point point )
    {
        return this.centre.distance( point ) <= this.radius;
    }

    public boolean intersects( Circle circle )
    {
        return this.centre.distance( circle.centre ) <= this.radius + circle.radius;
    }

    public void translate( double u, double v )
    {
        this.centre.translate( u, v );
    }

    // Overridden methods

    public String toString()
    {
        return "Promien: " + this.radius + "\nSrodek: " +
                this.centre.toString() + "\nPole: " + this.getArea() +
                "\nObwod: " + this.getCircumference() + "\n";
    }
}
