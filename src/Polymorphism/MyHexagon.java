package Polymorphism;

public final class MyHexagon extends Shape
{
    public double side;

    public MyHexagon( double side )
    {
        this.side = side;
    }

    @Override
    public double area()
    {
        return 3 * Math.sqrt( 3 ) * Math.pow( this.side, 2 ) / 2;
    }

    @Override
    public double circumference()
    {
        return 6 * this.side;
    }
}
