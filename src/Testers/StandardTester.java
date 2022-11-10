package Testers;

import Interfaces.ITestable;
import Standard.CurrencyConverter;

import java.awt.*;
import java.util.Map;
import java.util.Scanner;

public class StandardTester implements ITestable
{
    public static void exercise2()
    {
        var firstRectangle = new Rectangle( 0, 0, 4, 3 );
        var secondRectangle = new Rectangle( 1, 1, 4, 3 );

        var intersection = firstRectangle.intersection( secondRectangle );

        System.out.println( intersection );
    }

    public static void exercise3()
    {
        var firstRectangle = new Rectangle( 1, 1, 4, 5 );
        var secondRectangle = new Rectangle( 2, 0, 2, 3 );

        var isInside = firstRectangle.contains( secondRectangle );

        System.out.println( "Contains rectangle: " + isInside );
    }

    public static void exercise4()
    {
        var rectangle = new Rectangle( -3, 0, 6, 3 );
        var point = new Point( 2, -1 );

        var isInside = rectangle.contains( point );

        System.out.println( "Contains point: " + isInside );
    }

    public static void exercise5()
    {
        var firstRectangle = new Rectangle( 1, 1, 4, 5 );
        var secondRectangle = new Rectangle( 4, -3, 4, 3 );

        var intersects = firstRectangle.intersects( secondRectangle );

        System.out.println( "Intersects rectangle: " + intersects );
    }

    public static void exercise6()
    {
        var rates = Map.of(
                "pln", 1f,
                "eur", 4.79f,
                "usd", 4.88f
        );

        var converter = new CurrencyConverter( rates );

        var scanner = new Scanner( System.in );

        while ( true )
        {
            System.out.print( "Currency source [ pln / eur / usd ]: " );

            var source = scanner.nextLine().toLowerCase();

            if ( !rates.containsKey( source ) )
            {
                throw new IllegalArgumentException( "Selected unsupported currency" );
            }

            System.out.print( "Currency target [ pln / eur / usd ]: " );

            var target = scanner.nextLine().toLowerCase();

            if ( !rates.containsKey( target ) )
            {
                throw new IllegalArgumentException( "Selected unsupported currency" );
            }

            System.out.print( "Enter value in " + source.toUpperCase() + ": " );

            var value = scanner.nextFloat();

            var result = converter.convert( source, target, value );

            System.out.println( value + source.toUpperCase() + " - " + result + target.toUpperCase() );

            System.out.println( "Wish to repeat? [ y / n ]: " );

            var shouldRestart = scanner.next();

            if ( shouldRestart.equals( "n" ) )
            {
                break;
            }
        }
    }

    @Override
    public void evaluate()
    {
        exercise2();
        exercise3();
        exercise4();
        exercise5();
        // exercise6();
    }
}
