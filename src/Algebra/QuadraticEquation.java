package Algebra;

public class QuadraticEquation
{
    public float a;

    public float b;

    public float c;

    public QuadraticEquation( float a, float b, float c )
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public QuadraticEquation add( QuadraticEquation equation )
    {
        return new QuadraticEquation( this.a + equation.a, this.b + equation.b, this.c + equation.c );
    }

    public QuadraticEquation multiply( float multiplier )
    {
        return new QuadraticEquation( this.a * multiplier, this.b * multiplier, this.c * multiplier );
    }

    public float[] findSquareRoots()
    {
        var delta = this.b * this.b - 4 * this.a * this.c;

        if ( delta < 0 )
        {
            return null;
        }

        if ( delta == 0 )
        {
            return new float[] { -this.b / 2 * this.a };
        }

        var sqrtDelta = (float) Math.sqrt( delta );

        var x1 = ( -this.b + sqrtDelta ) / 2 * this.a;
        var x2 = ( -this.b - sqrtDelta ) / 2 * this.a;

        return new float[] { x1, x2 };
    }

    @Override
    public String toString() {
        return this.a + "x^2" +
                ( this.b < 0 ? " - " : " + " ) +
                Math.abs( this.b ) +
                "x" +
                ( this.c < 0 ? " - " : " + " ) +
                this.c;
    }
}
