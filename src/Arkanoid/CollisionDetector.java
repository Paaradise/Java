package Arkanoid;

import Arkanoid.Models.Ball;
import Arkanoid.Models.Bar;
import Arkanoid.Models.Brick;

import java.util.ArrayList;

public class CollisionDetector extends Thread
{
    Ball ball;

    Bar bar;

    ArrayList<Brick> brickList;

    public CollisionDetector(Ball a, Bar b, ArrayList<Brick> brickList)
    {
        this.ball = a;
        this.bar = b;
        this.brickList = brickList;
        start();
    }

    public void run()
    {
        try
        {
            while ( true )
            {
                if ( this.ball.intersects( this.bar.getBounds() ) )
                {
                    this.ball.HandleCollision( this.bar.getBounds2D(), true );

                    sleep( 100 );
                }

                for ( var brick : this.brickList )
                {
                    if ( brick.alive() && this.ball.intersects( brick.getBounds() ) )
                    {
                        this.ball.HandleCollision( brick.getBounds2D(), false );

                        brick.HandleCollision();

                        sleep( 100 );
                    }
                }

                sleep( 15 );
            }
        }
        catch (InterruptedException e)
        {

        }
    }
}
