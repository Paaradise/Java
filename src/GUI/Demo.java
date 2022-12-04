package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Demo implements ActionListener
{
    JTextField t1;
    JButton b1;
    JButton b2;

    public void actionPerformed(ActionEvent e)
    {
        Object target = e.getSource();

        if(target==b1||target==t1)
        {
            int k;
            try {
                k=Integer.parseInt(t1.getText());
            }
            catch (NumberFormatException exception) {
                t1.setText("NaN");
                return;
            }
            t1.setText(Integer.toString(k*k));
            t1.requestFocus();
        }
        else if (target==b2)
        {
            t1.setText("");
            t1.requestFocus();
        }
    }

    void init()
    {
        t1=new JTextField(6);
        b1=new JButton("^2");
        b2=new JButton("clear");

        JPanel p=new JPanel();
        p.add(t1);
        p.add(b1);
        p.add(b2);

        t1.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);

        JFrame f=new JFrame();
        Container c=f.getContentPane();
        c.add(p);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args)
    {
        //do wersji 1.4
        //new Demo().init();

        //od wersji 1.5
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Demo().init();
            }
        });
    }
}
