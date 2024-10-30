package gui;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;

public class GUI2 {
  public static void main(String[] args) {
    var tv = new TV();
    var r = new SimpleRemote(() -> tv.toggle());

    tv.setDisplay(r.getTextArea());
  }
}

interface Command {
  public void execute();
}

class TV extends JFrame {
  JLabel label;
  boolean off = true;
  boolean up = true;

  ImageIcon offImage = new ImageIcon(new BufferedImage(355, 200,
          BufferedImage.TYPE_3BYTE_BGR));
  ImageIcon onImage = new ImageIcon("news.gif");
  ImageIcon upImage = new ImageIcon("gif.gif");

  JTextArea remoteDisplay;

  public TV() {
    var panel = new JPanel();

    add(panel);

    label = new JLabel(offImage);

    panel.add(label);

    pack();
    setLocation(240, 250);
    setVisible(true);
  }

  public void toggle() {
    if (!off) {
      off();
    } else {
      on();
    }

    if (!up && !off){
      Down();
    } else if (up && !off){
      Up();
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

  public void Up() {
    label.setIcon(upImage);
    up = true;
    remoteDisplay.append("Up\n");
  }

  public void Down() {
    label.setIcon(onImage);
    up = false;
    remoteDisplay.append("Down\n");
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
    b.setSize(5, 5);
    add(b, BorderLayout.CENTER);

    JButton up = new JButton("East");
    up.setSize(5, 5);
    add(up, BorderLayout.EAST);

    JButton down = new JButton("West");
    down.setSize(5, 5);
    add(down, BorderLayout.WEST);

    JButton North = new JButton("North");
    North.setSize(5, 5);
    add(North, BorderLayout.NORTH);

    JButton South = new JButton("South");
    South.setSize(10, 10);
    add(South, BorderLayout.SOUTH);

    b.addActionListener(a -> c.execute());
    up.addActionListener(a -> c.execute());
    down.addActionListener(a -> c.execute());
    North.addActionListener(a -> c.execute());
    South.addActionListener(a -> c.execute());

    text = new JTextArea("Simple Remote\n");
    text.setRows(4);
    add(text, BorderLayout.SOUTH);

    setSize(500, 500);
    setVisible(true);
  }

  public JTextArea getTextArea() {
    return text;
  }
}