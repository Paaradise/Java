package Arkanoid;

import Arkanoid.Models.Ball;
import Arkanoid.Models.Bar;

public class BallEngine extends Thread
{
    Ball a;

    Bar b;

    BallEngine(Ball a, Bar b)
    {
        this.a = a;
        this.b = b;
        start();
    }

    public void run()
    {
        while( true )
        {
            a.nextStep();
            try
            {
                sleep(15);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
