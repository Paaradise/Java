package Arkanoid;

public class CollisionDetector extends Thread {
    Ball a;
    Bar b;

    CollisionDetector( Ball a, Bar b)
    {
        this.a = a;
        this.b = b;
        start();
    }

    public void run()
    {
        try
        {
            while (true)
            {
                //Ball - Bar collision
                if (this.a.intersects(this.b.getBounds()))
                {
                    this.a.HandleCollision(this.b.getBounds2D());
                }
                sleep(15);
            }
        }
        catch (InterruptedException e){}
    }
}
