package Testers;

import Algebra.Complex;
import Algebra.Fraction;
import Algebra.QuadraticEquation;
import Algebra.Vector;
import Interfaces.ITestable;

public class AlgebraTester implements ITestable
{
    private static boolean decimalExpansionTest()
    {
        var fraction = new Fraction( 3, 2 );

        var expansion = fraction.getDecimalExpansion();

        assert expansion == 0.5f : "Received incorrect decimal subtraction";

        return true;
    }

    private static boolean fractionAdditionTest()
    {
        var fraction1 = new Fraction( 2, 3 );
        var fraction2 = new Fraction( 1, 3 );
        var fraction3 = new Fraction(2, 5 );

        var resultOfFirstAndSecond = fraction1.add(fraction2);
        var resultOfSecondAndThird = fraction2.add(fraction3);
        var resultOfFirstAndThird = fraction1.add(fraction3);

        assert resultOfFirstAndSecond.nominator == 3 && resultOfFirstAndSecond.denominator == 3 : "Expected 3 / 3";
        assert resultOfSecondAndThird.nominator == 11 && resultOfSecondAndThird.denominator == 15 : "Expected 11 / 15";
        assert resultOfFirstAndThird.nominator == 16 && resultOfFirstAndThird.denominator == 15 : "Expected 16 / 15";

        return true;
    }

    private static boolean fractionSubtractionTest()
    {
        var fraction1 = new Fraction( 2, 3 );
        var fraction2 = new Fraction( 1, 3 );
        var fraction3 = new Fraction(2, 5 );

        var resultOfFirstAndSecond = fraction1.subtract(fraction2);
        var resultOfSecondAndThird = fraction2.subtract(fraction3);
        var resultOfFirstAndThird = fraction1.subtract(fraction3);

        assert resultOfFirstAndSecond.nominator == 1 && resultOfFirstAndSecond.denominator == 3 : "Expected 1 / 3";
        assert resultOfSecondAndThird.nominator == -1 && resultOfSecondAndThird.denominator == 15 : "Expected -1 / 15";
        assert resultOfFirstAndThird.nominator == 4 && resultOfFirstAndThird.denominator == 15 : "Expected 4 / 15";

        return true;
    }

    private static boolean fractionMultiplicationTest()
    {
        var fraction1 = new Fraction( 2, 3 );
        var fraction2 = new Fraction( 1, 3 );
        var fraction3 = new Fraction(2, 5 );

        var resultOfFirstAndSecond = fraction1.multiply(fraction2);
        var resultOfSecondAndThird = fraction2.multiply(fraction3);
        var resultOfFirstAndThird = fraction1.multiply(fraction3);

        assert resultOfFirstAndSecond.nominator == 2 && resultOfFirstAndSecond.denominator == 9 : "Expected 2 / 9";
        assert resultOfSecondAndThird.nominator == 2 && resultOfSecondAndThird.denominator == 15 : "Expected 2 / 15";
        assert resultOfFirstAndThird.nominator == 4 && resultOfFirstAndThird.denominator == 15 : "Expected 4 / 15";

        return true;
    }

    private static boolean fractionReverseTest()
    {
        var fraction = new Fraction( 3, 6 );

        var reversed = fraction.reverse();

        assert reversed.nominator == 6 && reversed.denominator == 3 : "Expected 6 / 3";

        return true;
    }

    private static boolean fractionSimplificationTest()
    {
        var fraction = new Fraction( 3, 6 );

        var simplified = fraction.simplify();

        assert simplified.nominator == 1 && simplified.denominator == 2 : "Expected 1 / 2";

        return true;
    }

    private static boolean complexAdditionTest()
    {
        var complex1 = new Complex( 2, 7 );

        var complex2 = new Complex( 3, -4);

        var complex3 = complex1.add(complex2);

        assert complex3.re == 5 && complex3.im == 3 : "Expected 5 + 3i";

        return true;
    }

    private static boolean complexSubtractionTest()
    {
        var complex1 = new Complex( 9, 5 );

        var complex2 = new Complex( 4, 7 );

        var complex3 = complex1.subtract(complex2);

        assert complex3.re == 5 && complex3.im == -2 : "Expected 5 - 2i";

        return true;
    }

    private static boolean complexMultiplicationTest()
    {
        var complex1 = new Complex( 3, 2 );

        var complex2 = new Complex( 5, 6 );

        var complex3 = complex1.multiply(complex2);

        assert complex3.re == 3 && complex3.im == 28 : "Expected 3 + 28i";

        return true;
    }

    private static boolean complexDivisionTest()
    {
        var complex1 = new Complex( 3, 2 );

        var complex2 = new Complex( 4, -5 );

        var complex3 = complex1.divide(complex2);

        var re = Math.round( complex3.re * 1000.0 ) / 1000.0;

        var im = Math.round( complex3.im * 1000.0 ) / 1000.0;

        var reTest = Math.round( ( 2f / 41f ) * 1000.0 ) / 1000.0;

        var imTest = Math.round( ( 23f / 41f ) * 1000.0 ) / 1000.0;

        assert re == reTest && im == imTest : "Expected (2 / 41) + (23 / 41)i";

        return true;
    }

    private static boolean vectorAdditionTest()
    {
        var vector1 = new Vector( 1, 1, 1 );

        var vector2 = new Vector( 2, 2, 2 );

        var vector3 = vector1.add(vector2);

        assert vector3.x == 3 && vector3.y == 3 && vector3.z == 3 : "Expected: x:3 y:3 z:3";

        return true;
    }

    private static boolean vectorSubtractionTest()
    {
        var vector1 = new Vector( 1, 1, 1 );

        var vector2 = new Vector( 2, 2, 2 );

        var vector3 = vector1.subtract(vector2);

        assert vector3.x == -1 && vector3.y == -1 && vector3.z == -1 : "Expected: x:-1 y:-1 z:-1";

        return true;
    }

    private static boolean vectorMultiplicationTest()
    {
        var vector1 = new Vector( 1, 1, 1 );

        var vector2 = vector1.multiply( 2.5f );

        assert vector2.x == 2.5 && vector2.y == 2.5 && vector2.z == 2.5 : "Expected: x:3 y:3 z:3";

        return true;
    }

    private static boolean vectorDotProductTest()
    {
        var vector1 = new Vector( 1, 2, 3 );

        var vector2 = new Vector( 1, 5, 7 );

        var dotProduct = vector1.dot(vector2);

        assert dotProduct == 32 : "Expected: 32";

        return true;
    }

    private static boolean vectorCrossProductTest()
    {
        var vector1 = new Vector( 1, 2, 3 );

        var vector2 = new Vector( 1, 5, 7 );

        var vector3 = vector1.cross(vector2);

        assert vector3.x == -1 && vector3.y == -4 && vector3.z == 3 : "Expected: 32";

        return true;
    }

    private static boolean quadraticEquationAdditionTest()
    {
        var equation1 = new QuadraticEquation( 3, 4, 5 );

        var equation2 = new QuadraticEquation( 4, 5, 6 );

        var equation3 = equation1.add(equation2);

        assert equation3.a == 7 && equation3.b == 9 && equation3.c == 11 : "Expected: a:7 b:9 c:11";

        return true;
    }

    private static boolean quadraticEquationMultiplicationTest()
    {
        var equation1 = new QuadraticEquation( 3, 4, 5 );

        var equation2 = equation1.multiply( 2 );

        assert equation2.a == 6 && equation2.b == 8 && equation2.c == 10 : "Expected: a:6 b:8 c:10";

        return true;
    }

    private static boolean quadraticEquationSquareRootsTest()
    {
        var equation1 = new QuadraticEquation( -3, 4, 4 );

        var roots1 = equation1.findSquareRoots();

        assert roots1[0] == -6 && roots1[1] == 18 : "Expected: x1=-2/3 x2=2";

        var equation2 = new QuadraticEquation( 1, 2, 1 );

        var roots2 = equation2.findSquareRoots();

        assert roots2[0] == -1 : "Expected: x=-1";

        var equation3 = new QuadraticEquation( 1, 1, 1 );

        var roots3 = equation3.findSquareRoots();

        assert roots3 == null : "Expected: null";

        return true;
    }

    @Override
    public void evaluate()
    {
        if ( decimalExpansionTest() )
        {
            System.out.println( "Passed fraction decimal expansion test" );
        }

        if ( fractionAdditionTest() )
        {
            System.out.println( "Passed fraction addition test" );
        }

        if ( fractionSubtractionTest() )
        {
            System.out.println( "Passed fraction subtraction test" );
        }

        if ( fractionMultiplicationTest() )
        {
            System.out.println( "Passed fraction multiplication test" );
        }

        if ( fractionReverseTest() )
        {
            System.out.println( "Passed fraction reverse test" );
        }

        if ( fractionSimplificationTest() )
        {
            System.out.println( "Passed fraction simplification test" );
        }

        if ( complexAdditionTest() )
        {
            System.out.println( "Passed complex number addition test" );
        }

        if ( complexSubtractionTest() )
        {
            System.out.println( "Passed complex number subtraction test" );
        }

        if ( complexMultiplicationTest() )
        {
            System.out.println( "Passed complex number multiplication test" );
        }

        if ( complexDivisionTest() )
        {
            System.out.println( "Passed complex number division test" );
        }

        if ( vectorAdditionTest() )
        {
            System.out.println( "Passed vector addition test" );
        }

        if ( vectorSubtractionTest() )
        {
            System.out.println( "Passed vector subtraction test" );
        }

        if ( vectorMultiplicationTest() )
        {
            System.out.println( "Passed vector multiplication test" );
        }

        if ( vectorDotProductTest() )
        {
            System.out.println( "Passed vector dot product test" );
        }

        if ( vectorCrossProductTest() )
        {
            System.out.println( "Passed vector cross product test" );
        }

        if ( quadraticEquationAdditionTest() )
        {
            System.out.println( "Passed quadratic equation addition test" );
        }

        if ( quadraticEquationMultiplicationTest() )
        {
            System.out.println( "Passed quadratic equation multiplication test" );
        }

        if ( quadraticEquationSquareRootsTest() )
        {
            System.out.println( "Passed quadratic equation square roots test" );
        }
    }
}
