package Arkanoid;

import java.util.ArrayList;

public class CollisionDetector extends Thread {
    Ball ball;
    Bar bar;
    ArrayList<Brick> brickList;

    CollisionDetector(Ball a, Bar b, ArrayList<Brick> brickList)
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
            while (true)
            {
                //Ball - Bar collision
                if (this.ball.intersects(this.bar.getBounds()))
                {
                    this.ball.HandleCollision(this.bar.getBounds2D(), true);
                    //to prevent detecting one collision multiple times
                    sleep(100);
                }

                for (Brick b : this.brickList )
                {
                    if ( (b.alive()) && (this.ball.intersects(b.getBounds())) )
                    {
                        this.ball.HandleCollision(b.getBounds2D(), false);
                        b.HandleCollision();
                        //to prevent detecting one collision multiple times
                        sleep(100);
                    }
                }
                sleep(15);
            }
        }
        catch (InterruptedException e){}
    }
}
