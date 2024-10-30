package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class GUI2 {
  public static void main(String[] args) {
    var tv = new TV();
    var r = new SimpleRemote(() -> tv.toggle());;
  }
}

interface Command {
  public void execute();
}

class TV extends JFrame {
  JLabel label;
  boolean off = true;
  boolean up = true;
  int col = 7;
  int row = 7;

  ImageIcon offImage = new ImageIcon(new BufferedImage(355, 200,
          BufferedImage.TYPE_3BYTE_BGR));
  ImageIcon onImage = new ImageIcon("news.gif");
  ImageIcon upImage = new ImageIcon("gif.gif");

  JTextArea remoteDisplay;

  public TV() {
    setSize(500, 500);
    setLayout(null);


    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 300, 300);
    panel.setLayout(new GridLayout(col, row));
    panel.setBackground(Color.GRAY);
    //panel.setBounds(0, 0, 250, 250);


    JTextArea textArea = new JTextArea();
    textArea.setBounds(300, 0, 200, 500);
    textArea.setBackground(Color.GRAY);
    textArea.setEditable(false);

    for (int i = 0; i < (col * row); i++){
      panel.add(new JButton());
    }

    var sideBar = new JPanel();

    add(panel);
    add(textArea);
    //add(sideBar, BorderLayout.WEST);



    //label = new JLabel(offImage);

    //panel.add(label);

    //pack();
    setLocation(100, 100);
    setVisible(true);
  }
  /*
  public void paint(Graphics g) {
    super.paint(g);
    for (int i = 0; i < col; i++){
      for (int j = 0; j < row; j++){
        g.drawRect(50 * i, 50 * j, 50, 50);
      }
    }

    // Maybe Use Buttons Instead of Making a 3d Grid

     // Draw a line from (50, 50) to (200, 200)
  }*/

  public void toggle() {
    if (!off) {
      off();
    } else {
      on();
    }

  }

  public void off() {
    label.setIcon(offImage);
    off = true;
    remoteDisplay.append("Off\n");
  }

  public void on() {
    label.setIcon(onImage);
    off = false;
    remoteDisplay.append("On\n");
  }

  public void setDisplay(JTextArea t) {
    remoteDisplay = t;
  }
}

class SimpleRemote extends JFrame {
  Command c;
  JTextArea text;

  public SimpleRemote(Command cc) {
    c = cc;
    setLayout(new BorderLayout());

    JButton b = new JButton("Move");
    //b.setSize(5, 5);
    b.setBounds(250, 100, 50, 5);
    add(b, BorderLayout.CENTER);

    JButton attack = new JButton("Attack");
    //attack.setSize(5, 5);
    attack.setBounds(350, 100, 50, 5);
    add(attack, BorderLayout.CENTER);

    JButton up = new JButton("East");
    //up.setSize(5, 5);
    up.setBounds(400, 100, 100, 5);
    add(up, BorderLayout.EAST);

    JButton down = new JButton("West");
    //down.setSize(5, 5);
    down.setBounds(100, 200, 100, 5);
    add(down, BorderLayout.WEST);

    JButton North = new JButton("North");
    //North.setSize(5, 5);
    North.setBounds(100, 300, 100, 5);
    add(North, BorderLayout.NORTH);

    JButton South = new JButton("South");
    //South.setSize(10, 10);
    South.setBounds(100, 400, 100, 5);
    add(South, BorderLayout.SOUTH);

    b.addActionListener(a -> c.execute());
    up.addActionListener(a -> c.execute());
    down.addActionListener(a -> c.execute());
    North.addActionListener(a -> c.execute());
    South.addActionListener(a -> c.execute());
    attack.addActionListener(a -> c.execute());

    setSize(500, 500);
    setVisible(true);
    setLocation(400, 400);
  }

  public JTextArea getTextArea() {
    return text;
  }
}