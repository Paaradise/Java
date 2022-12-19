package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Kalk implements ActionListener, KeyListener
{
    JTextField t1;
    JTextArea t2;
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
    JButton reverse;
    BufferedWriter writer;
    BufferedReader reader;

    double n1, n2;
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
            n2=Double.parseDouble(t1.getText());
            n1 = result;

            result = getResult(lastAction, n1, n2);

            if (!lastAction.equals(""))
            {
                t2.setText(n1 + " " + lastAction + " " + n2 + " = " + result);
                try {
                    saveAction(n1, n2, lastAction);
                } catch (IOException ex) {}
            }

            lastAction = button;
            t1.setText("");
            t1.requestFocus();
        }

        else if(target==brow||target==t1)
        {
            n2=Double.parseDouble(t1.getText());
            n1 = result;

            result = getResult(lastAction, n1, n2);

            if (!lastAction.equals(""))
            {
                t2.setText(n1 + " " + lastAction + " " + n2 + " = " + result);
                try {
                    saveAction(n1, n2, lastAction);
                } catch (IOException ex) {}
            }

            lastAction = "";
            t1.setText(Double.toString(result));
            t1.requestFocus();
        }

        else if (target == reverse)
        {
            try {
                reverseAction();
            } catch (IOException ex) {}
        }
    }

    //saves all actions in reversed order
    void saveAction(double n1, double n2, String action) throws IOException {
        //creating a reader to collect all saved lines
        reader = new BufferedReader(new FileReader(new File("history.csv")));

        //saving all lines
        List<String> content = reader.lines().collect(Collectors.toList());

        //closing reader
        reader.close();

        //creating writer and clearing all lines from file
        writer = new BufferedWriter(new FileWriter(new File("history.csv"), false));

        //writing current operation at first line
        writer.write(n1 + ";" + n2 + ";" + action + "\n");
        writer.flush();

        //writing all other operations next
        while (content.size() > 0)
        {
            writer.write(content.remove(0) + "\n");
            writer.flush();
        }

        //closing writer
        writer.close();
    }

    void reverseAction() throws IOException {
        //creating reader
        reader = new BufferedReader(new FileReader(new File("history.csv")));

        //saving all lines to list
        List<String> content = reader.lines().collect(Collectors.toList());

        //closing reader
        reader.close();

        //assure there's any operation to reverse
        if (content.size() < 2)
        {
            return;
        }

        //recreating state from last operation
        String[] line = content.get(1).split(";");
        n1 = Double.parseDouble(line[0]);
        n2 = Double.parseDouble(line[1]);
        lastAction = line[2];
        result = getResult(lastAction, n1, n2);

        //setting all the text fields
        t2.setText(n1 + " " + lastAction + " " + n2 + " = " + result);

        lastAction = "";
        t1.setText(Double.toString(result));
        t1.requestFocus();

        //creating writer and clearing all lines from file
        writer = new BufferedWriter(new FileWriter(new File("history.csv"), false));

        //saving all lines without the one that is reversed now
        while (content.size() > 1)
        {
            writer.write(content.remove(1) + "\n");
            writer.flush();
        }

        //closing writer
        writer.close();

    }

    void init()
    {
        //try
        //{
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //}
        //catch(Exception e){}

        try {
            //clear the file while initializing
            writer = new BufferedWriter(new FileWriter(new File("history.csv"), false));
            writer.close();
        } catch (IOException e) {}

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
        gbc.gridy=1;
        gbc.gridwidth=5;
        gbc.ipadx=0;
        gbc.ipady=5;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(t1,gbc);
        c.add(t1);


        t2 = new JTextArea();
        t2.setEditable(false);
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=5;
        gbc.ipadx=0;
        gbc.ipady=5;
        gbl.setConstraints(t2,gbc);
        c.add(t2);



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
            gbc.gridy=yVal + 1;
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
        gbc.gridy=2;
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
        gbc.gridy=3;
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
        gbc.gridy=4;
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
        gbc.gridy=5;
        gbc.gridwidth=2;
        gbc.ipadx=30;
        gbc.ipady=0;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(bdivide,gbc);
        c.add(bdivide);


        reverse = new JButton("<<");
        reverse.addActionListener(this);
        reverse.setFocusable(false);
        reverse.setToolTipText("Cofanie operacji");
        gbc.gridx=3;
        gbc.gridy=6;
        gbc.gridwidth=2;
        gbc.ipadx=30;
        gbc.ipady=0;
        gbc.insets=new Insets(5,5,0,5);
        gbl.setConstraints(reverse, gbc);
        c.add(reverse);



        brow=new JButton("=");
        brow.addActionListener(this);
        brow.setFocusable(false);
        brow.setToolTipText("wykonaj działanie");
        gbc.gridx=0;
        gbc.gridy=6;
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
