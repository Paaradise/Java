package Polymorphism;

public final class MyCircle extends Shape
{
    public double radius;

    public MyCircle( double radius )
    {
        this.radius = radius;
    }

    @Override
    public double area()
    {
        return Math.PI * Math.pow( this.radius, 2 );
    }

    @Override
    public double circumference()
    {
        return 2 * Math.PI * this.radius;
    }
}
