package Polymorphism;

public final class MyRectangle extends Shape
{
    private double length;
    private double height;

    public MyRectangle( double length, double height )
    {
        this.length = length;
        this.height = height;
    }

    @Override
    public double area()
    {
        return this.height * this.length;
    }

    @Override
    public double circumference()
    {
        return 2 * ( this.height + this.length );
    }
}
