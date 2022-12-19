package Arkanoid;

public class BallEngine extends Thread {
    Ball a;
    Bar b;

    BallEngine(Ball a, Bar b)
    {
        this.a=a;
        this.b = b;
        start();
    }

    public void run()
    {
        try
        {
            while(true)
            {
                if (this.a.intersects(this.b.getBounds()))
                {
                    this.a.dy = -this.a.dy;
                }

                a.nextStep();
                sleep(15);
            }
        }
        catch(InterruptedException e){}
    }
}
