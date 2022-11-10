package Testers;

import Interfaces.ITestable;
import Polymorphism.AdherentRectangle;
import Polymorphism.MyCircle;
import Polymorphism.MyHexagon;
import Polymorphism.MyRectangle;
import Polymorphism.Shape;

import java.awt.*;

public class PolymorphismTester implements ITestable
{
    private static boolean customConstructorTest()
    {
        var vertex = new Point( 10, 20 );
        var rectangle = new AdherentRectangle( vertex, 10, 20 );

        assert rectangle.x == 10 : "Invalid x parameter assignment";
        assert rectangle.y == 20 : "Invalid y parameter assignment";
        assert rectangle.width == 10 : "Invalid width parameter assignment";
        assert rectangle.height == 20 : "Invalid height parameter assignment";

        return true;
    }

    private static boolean adherenceTest()
    {
        var point = new Point( 0, 0 );
        var rectangle = new AdherentRectangle( point, 10, 20 );

        var topVertex = new Point( point.x, point.y + 20 );
        var topAdherentRectangle = new AdherentRectangle( topVertex, 10, 20 );

        assert rectangle.adheres( topAdherentRectangle ) : "Top adherence failed";

        var leftVertex = new Point( point.x - 10, point.y );
        var leftAdherentRectangle = new AdherentRectangle( leftVertex, 10, 20 );

        assert rectangle.adheres( leftAdherentRectangle ) : "Left adherence failed";

        var bottomVertex = new Point( point.x, point.y - 20 );
        var bottomAdherentRectangle = new AdherentRectangle( bottomVertex, 10, 20 );

        assert rectangle.adheres( bottomAdherentRectangle ) : "Bottom adherence failed";

        var rightVertex = new Point( point.x + 10, point.y );
        var rightAdherentRectangle = new AdherentRectangle( rightVertex, 10, 20 );

        assert rectangle.adheres( rightAdherentRectangle ) : "Right adherence failed";

        var invalidVertex = new Point( point.x + 30, point.y );
        var invalidRectangle = new AdherentRectangle( invalidVertex, 10, 20 );

        assert !rectangle.adheres( invalidRectangle ) : "Positive adherence to separated rectangle";

        return true;
    }

    private static boolean polymorphismTest()
    {
        var totalArea = 0;

        var shapes = new Shape[]
        {
            new MyCircle( 3 ),
            new MyRectangle( 2, 3 ),
            new MyHexagon( 6 )
        };

        for ( var shape : shapes ) {
            totalArea += shape.area();
        }

        assert ( (int) shapes[0].area() ) == 28 : "Invalid circle area";
        assert ( (int) shapes[1].area() ) == 6 : "Invalid rectangle area";
        assert ( (int) shapes[2].area() ) == 93 : "Invalid hexagon area";
        assert totalArea == 127 : "Invalid total area sum implementation";

        return true;
    }

    @Override
    public void evaluate()
    {
        if ( customConstructorTest() )
        {
            System.out.println( "Passed custom constructor test" );
        }

        if ( adherenceTest() )
        {
            System.out.println( "Passed adherence test" );
        }

        if ( polymorphismTest() )
        {
            System.out.println( "Passed polymorphism test" );
        }
    }
}
