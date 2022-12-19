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
                a.nextStep();
                sleep(15);
            }
        }
        catch(InterruptedException e){}
    }
}
