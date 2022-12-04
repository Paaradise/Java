package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Kalk implements ActionListener, KeyListener
{
    JTextField t1;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    JButton b6;
    JButton b7;
    JButton b8;
    JButton b9;
    JButton b0;
    ArrayList<JButton> numbers = new ArrayList<JButton>() {
        {
            add(b0);
            add(b1);
            add(b2);
            add(b3);
            add(b4);
            add(b5);
            add(b6);
            add(b7);
            add(b8);
            add(b9);
        }
    };
    JButton bplus,brow,bminus,btimes,bdivide;

    double x,buf;
    double result = 0;
    String lastAction = "";
    String[] actions = { "+", "-", "*", "/" };

    double getResult(String action, double n1, double n2)
    {
        switch (action)
        {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                return n1 / n2;
            case "":
                return n2;
        }
        return 0;
    }

    public void actionPerformed(ActionEvent e)
    {
        Object target = e.getSource();

        String button = ((JButton)target).getText();
        try {
            int i = Integer.parseInt(button);
            t1.setText(t1.getText()+((JButton)target).getText());
            t1.requestFocus();
        }
        catch (NumberFormatException exception) {}

        if(Arrays.asList(actions).contains(button))
        {
            buf=Double.parseDouble(t1.getText());
            result = getResult(lastAction, result, buf);
            lastAction = button;
            t1.setText("");
            t1.requestFocus();
        }

        else if(target==brow||target==t1)
        {
            buf=Double.parseDouble(t1.getText());
            result = getResult(lastAction, result, buf);
            lastAction = "";
            t1.setText(Double.toString(result));
            t1.requestFocus();
        }
    }

    void init()
    {
        //try
        //{
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //}
        //catch(Exception e){}

        JFrame f=new JFrame();
        Container c=f.getContentPane();

        GridBagLayout gbl=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.HORIZONTAL;
        c.setLayout(gbl);



        t1=new JTextField(15);
        t1.addActionListener(this);
        t1.addKeyListener(this);
        t1.setEditable(false);
        t1.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=5;
        gbc.ipadx=0;
        gbc.ipady=5;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(t1,gbc);
        c.add(t1);


        gbc.insets=new Insets(5,5,0,0);
        int xVal = 1;
        int yVal = 4;

        for (int i=0; i<10; i++)
        {
            JButton b = numbers.get(i);
            b=new JButton(String.valueOf(i));
            b.addActionListener(this);
            b.setFocusable(false);
            gbc.gridx=xVal;
            gbc.gridy=yVal;
            gbc.gridwidth=1;
            gbc.ipadx=0;
            gbc.ipady=0;
            gbl.setConstraints(b,gbc);
            c.add(b);
            xVal = (i)%3;
            yVal = (i)/3 + 1;
        }


        bplus=new JButton("+");
        bplus.addActionListener(this);
        bplus.setFocusable(false);
        bplus.setToolTipText("dodawanie");
        gbc.gridx=3;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.ipadx=30;
        gbc.ipady=0;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(bplus,gbc);
        c.add(bplus);



        bminus=new JButton("-");
        bminus.addActionListener(this);
        bminus.setFocusable(false);
        bminus.setToolTipText("odejmowanie");
        gbc.gridx=3;
        gbc.gridy=2;
        gbc.gridwidth=2;
        gbc.ipadx=30;
        gbc.ipady=0;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(bminus,gbc);
        c.add(bminus);




        btimes=new JButton("*");
        btimes.addActionListener(this);
        btimes.setFocusable(false);
        btimes.setToolTipText("mnozenie");
        gbc.gridx=3;
        gbc.gridy=3;
        gbc.gridwidth=2;
        gbc.ipadx=30;
        gbc.ipady=0;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(btimes,gbc);
        c.add(btimes);



        bdivide=new JButton("/");
        bdivide.addActionListener(this);
        bdivide.setFocusable(false);
        bdivide.setToolTipText("dzielenie");
        gbc.gridx=3;
        gbc.gridy=4;
        gbc.gridwidth=2;
        gbc.ipadx=30;
        gbc.ipady=0;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(bdivide,gbc);
        c.add(bdivide);



        brow=new JButton("=");
        brow.addActionListener(this);
        brow.setFocusable(false);
        brow.setToolTipText("wykonaj działanie");
        gbc.gridx=0;
        gbc.gridy=5;
        gbc.gridwidth=4;
        gbc.ipadx=30;
        gbc.ipady=0;
        gbc.insets=new Insets(5,5,5,0);
        gbl.setConstraints(brow,gbc);
        c.add(brow);



        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Kalkulator");
        f.setVisible(true);
    }

    public static void main(String[] args)
    {
        //do wersji 1.4
        //new Kalk().init();

        //od wersji 1.5
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Kalk().init();
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //check if key is number or dot
        if (
                ( (code >= KeyEvent.VK_0) && (code <= KeyEvent.VK_9) ) ||
                ( (code >= KeyEvent.VK_NUMPAD0) && (code <= KeyEvent.VK_NUMPAD9) ) ||
                (code == KeyEvent.VK_PERIOD))
        {
            t1.setText(t1.getText()+e.getKeyChar());
            t1.requestFocus();
        }

        //numpad dot might be interpreted as comma, so we need second check to prevent error
        if (code == KeyEvent.VK_DECIMAL)
        {
            t1.setText(t1.getText()+'.');
            t1.requestFocus();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
}
