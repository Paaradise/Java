package Arkanoid;

import javax.swing.*;

public class Program
{
    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(() ->
        {
            var p = new Grid();
            var frame = new JFrame();

            frame.add(p);
            frame.setTitle("Genetically Superior Arkanoid");
            frame.setSize(400,370);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}