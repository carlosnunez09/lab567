package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.util.Scanner;

public class GUI extends JFrame implements ActionListener {
  JButton textButton, imageButton;
  JLabel textLabel, imageLabel;


  public GUI() {
    setLayout(new BorderLayout());

    textLabel = new JLabel("North");
    add("North", textLabel);

    imageButton = new JButton(createImage());
    add("West", imageButton);

    imageLabel = new JLabel(createImage());
    add("South", imageLabel);

    textButton = new JButton("A Button");
    textButton.addActionListener(this);
    add("East", textButton);

    JPanel centerPanel = new JPanel(new GridLayout(2, 3));
    JButton[][] buttonArray = new JButton[2][3];
    for (int r = 0; r < 2; r++) {
      for (int c = 0; c < 3; c++) {
        buttonArray[r][c] = new JButton(" (" + r + ":" + c + ") ");
        buttonArray[r][c].setActionCommand(r + " " + c);
        buttonArray[r][c].addActionListener(this);
        centerPanel.add(buttonArray[r][c]);
      }
    }
    add("Center", centerPanel);


    pack();
    setVisible(true);

  }

  public ImageIcon createImage() {
    BufferedImage exampleImage = new
            BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
    Graphics drawer = exampleImage.getGraphics();

    drawer.setColor(new Color(200, 200, 200));
    drawer.fillRect(0, 0, 50, 50);

    drawer.setColor(new Color(0, 255, 0));
    drawer.fillOval(20, 20, 10, 10);

    return new ImageIcon(exampleImage);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }


  interface Command {
    public void execute();
  }


  class SimpleRemote extends JFrame {
    Command c;
    JTextArea text;

    public SimpleRemote(Command cc) {
      c = cc;
      setLayout(new BorderLayout());

      JButton b = new JButton("On/Off");
      b.setSize(50, 50);
      add(b, BorderLayout.CENTER);

      JButton up = new JButton("Channel Up");
      up.setSize(50, 50);
      add(up, BorderLayout.EAST);

      JButton down = new JButton("Channel Down");
      down.setSize(50, 50);
      add(down, BorderLayout.WEST);

      b.addActionListener(a -> c.execute());
      up.addActionListener(a -> c.execute());
      down.addActionListener(a -> c.execute());

      text = new JTextArea("Simple Remote\n");
      text.setRows(4);
      add(text, BorderLayout.SOUTH);

      setSize(500, 200);
      setVisible(true);
    }


    public static void main(String[] args) {
      GUI gui = new GUI();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */

    /*@Override
    public void actionPerformed(ActionEvent event) {
      if (event.getSource() == textButton) {
        textButton.setText("Pushed");
        //SampleGUI.this.pack();
      } else {                       // somehow assume it's a button in the grid
        String msg = event.getActionCommand();
        Scanner sc = new Scanner(msg);
        int row = sc.nextInt();
        int col = sc.nextInt();
        System.out.println("row: " + row + " col: " + col);
      }*/


  }
}
