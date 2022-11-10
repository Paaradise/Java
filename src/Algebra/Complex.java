package Algebra;

public class Complex
{
    public float re;

    public float im;

    public Complex( float re, float im )
    {
        this.re = re;
        this.im = im;
    }

    public Complex add( Complex number )
    {
        return new Complex( this.re + number.re, this.im + number.im );
    }

    public Complex subtract( Complex number )
    {
        return new Complex( this.re - number.re, this.im - number.im );
    }

    public Complex multiply( Complex number )
    {
        return new Complex( this.re * number.re - this.im * number.im, this.re * number.im + this.im * number.re );
    }

    public Complex divide( Complex number )
    {
        var re = this.re * number.re + this.im * number.im;

        var im = this.im * number.re - this.re * number.im;

        var dominator = number.re * number.re + number.im * number.im;

        return new Complex( re / dominator, im / dominator );
    }

    @Override
    public String toString()
    {
        return re + ( im < 0 ? " - " : " + " ) + Math.abs( im ) + "i";
    }
}
