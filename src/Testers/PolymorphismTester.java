package Testers;

import Interfaces.ITestable;
import Polymorphism.*;
import Polymorphism.Shape;

import java.awt.*;
import java.io.*;

public class PolymorphismTester implements ITestable
{
    public static boolean documentSerializedTest()
    {
        System.out.println("-- do zapisu --");
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        ID z=new ID(br);
        System.out.println(z);

        try
        {
            ObjectOutputStream outp=new ObjectOutputStream(new FileOutputStream("plik.dat"));
            outp.writeObject(z);
            outp.close();
        }
        catch(Exception e){throw new RuntimeException("Couldn't save file");}



        System.out.println("\n-- z pliku --");
        ObjectInputStream inp;

        try
        {
            inp=new ObjectInputStream(new FileInputStream("plik.dat"));
            Object o=inp.readObject();
            ID x=(ID)o;
            inp.close();
            System.out.println(x);
            assert x.equals(z);
        }
        catch(FileNotFoundException e){throw new RuntimeException("Couldn't find this file...");}
        catch(Exception e){throw new RuntimeException("Couldn't read this file");}

        return true;
    }

    public static boolean documentComparisonTest()
    {
        Document[] dataBase = {
                new Passport("Jan", "Nowak", 2001),
                new ID("Grzegorz", "Gorniak", 1994),
                new Passport("Kamil", "Gorniak", 1985)};

        Document z;
        String pattern = "Gorniak";

        int found = 0;

        for (int i=0; i<dataBase.length; i++)
        {
            z = dataBase[i];
            if (z.isFitting(pattern))
            {
                found++;
            }
        }

        assert found == 2 : "Document comparison failed";
        return true;
    }

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

        if (documentComparisonTest())
        {
            System.out.println("Passed Document comparison test");
        }

        if (documentSerializedTest())
        {
            System.out.println("Passed Document saving and reading test");
        }
    }
}
