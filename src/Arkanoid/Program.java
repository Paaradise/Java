package Arkanoid;

import javax.swing.*;

public class Program
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                Grid p;
                p=new Grid();

                JFrame jf=new JFrame();
                jf.add(p);

                jf.setTitle("Genetically Superior Arkanoid");
                jf.setSize(400,370);
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setVisible(true);
            }
        });
    }
}