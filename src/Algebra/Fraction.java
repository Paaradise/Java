package Algebra;

public class Fraction
{
    public int nominator;

    public int denominator;

    public Fraction ( int nominator )
    {
        this.nominator = nominator;
        this.denominator = 1;
    }

    public Fraction( int nominator, int denominator )
    {
        this.nominator = nominator;

        assert denominator != 0 : "Denominator cannot equal 0";

        this.denominator = denominator;
    }

    public double getDecimalExpansion()
    {
        var value = (double) this.nominator / (double) this.denominator;

        var decimal =  Math.floor( value );

        return value - decimal;
    }

    public Fraction add( Fraction fraction )
    {
        if ( this.denominator == fraction.denominator )
        {
            return new Fraction( this.nominator + fraction.nominator, this.denominator );
        }

        var lcm = LCM( this.denominator, fraction.denominator );

        var leftNominator = this.nominator * lcm / this.denominator;
        var rightNominator = fraction.nominator * lcm / fraction.denominator;

        return new Fraction( leftNominator + rightNominator, lcm );
    }

    public Fraction subtract( Fraction fraction )
    {
        if ( this.denominator == fraction.denominator )
        {
            return new Fraction( this.nominator - fraction.nominator, this.denominator );
        }

        var lcm = LCM( this.denominator, fraction.denominator );

        return new Fraction( this.nominator * lcm / this.denominator - fraction.nominator * lcm / fraction.denominator, lcm );
    }

    public Fraction multiply( Fraction fraction )
    {
        return new Fraction( this.nominator * fraction.nominator, this.denominator * fraction.denominator );
    }

    public Fraction reverse()
    {
        return new Fraction( this.denominator, this.nominator );
    }

    public Fraction simplify()
    {
        int gcd = GCD( this.nominator, this.denominator );

        return new Fraction( this.nominator / gcd, this.denominator / gcd );
    }

    @Override
    public String toString()
    {
        return String.format( "%d / %d", this.nominator, this.denominator );
    }

    private static int LCM( int a, int b )
    {
        return a / GCD( a, b ) * b;
    }

    private static int GCD( int a, int b )
    {
        while ( a != b )
        {
            if ( a > b )
            {
                a -= b;
            }
            else
            {
                b -= a;
            }
        }

        return a;
    }
}
