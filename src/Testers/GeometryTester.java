package Testers;

import Geometry.Circle;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.ITestable;

public class GeometryTester implements ITestable
{
    private static boolean Exercise3()
    {
        Rectangle rectangle = new Rectangle( 5, 8 );

        double circumference = rectangle.getCircumference();

        assert circumference == ( 5 + 8 ) * 2 : "Circumference does not equal its proper value";

        return true;
    }

    private static boolean Exercise5()
    {
        Point point = new Point( 5, 6 );

        point.translate( 3, 4 );

        assert point.x == 8 && point.y == 10 : "Point failed to be translated";

        return true;
    }

    private static boolean Exercise6()
    {
        Rectangle rectangle = new Rectangle( 4, 10 );

        rectangle.translate( 5, 6 );

        assert rectangle.centre.x == 5 && rectangle.centre.y == 6 : "Rectangle failed to be translated";

        return true;
    }

    private static boolean Exercise7()
    {
        Circle circle = new Circle( 4 );

        double area = circle.getArea();
        double circumference = circle.getCircumference();

        assert area == Math.PI * Math.pow( circle.radius, 2 ) : "Invalid circle area";
        assert circumference == 2 * Math.PI * circle.radius : "Invalid circle circumference";

        return true;
    }

    private static boolean Exercise8()
    {
        Point sample = new Point( 0, 5 );

        Rectangle rectangle = new Rectangle( 4, 10 );

        assert rectangle.contains( sample ) : "False negative pass for rectangle";

        Circle circle = new Circle( 4 );

        assert !circle.contains( sample ) : "False positive pass for circle";

        return true;
    }

    private static boolean Exercise9()
    {
        Circle circle = new Circle( 3 );

        Circle testCircle = new Circle( 6 );

        testCircle.translate( 5, 0 );

        assert circle.intersects(testCircle) : "False negative pass";

        return true;
    }

    private static boolean Exercise10()
    {
        Circle circle = new Circle( 3 );

        Rectangle rectangle = new Rectangle( 5, 7 );

        circle.translate( 5, 0 );

        assert rectangle.intersects(circle) : "False negative pass";

        return true;
    }

    @Override
    public void evaluate()
    {
        if ( Exercise3() )
        {
            System.out.println( "Passed rectangle circumference test" );
        }

        if ( Exercise5() )
        {
            System.out.println( "Passed point translation test" );
        }

        if ( Exercise6() )
        {
            System.out.println( "Passed rectangle translation test" );
        }

        if ( Exercise7() )
        {
            System.out.println( "Passed circle area and circumference test" );
        }

        if ( Exercise8() )
        {
            System.out.println( "Passed circle and rectangle containment test" );
        }

        if ( Exercise9() )
        {
            System.out.println( "Passed circle to circle intersection test" );
        }

        if ( Exercise10() )
        {
            System.out.println( "Passed rectangle to circle intersection test" );
        }
    }
}
