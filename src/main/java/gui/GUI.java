package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUI extends JFrame implements ActionListener {

  JButton textButton, imageButton;
  JLabel textLabel, imageLabel;

  public GUI(){
    setLayout(new BorderLayout());

    textLabel = new JLabel("North");
    add("North",textLabel);
    imageButton = new JButton(createImage());
    add("West",imageButton);
    imageLabel = new JLabel(createImage());
    add("South",imageLabel);
    textButton = new JButton("A Button");
    textButton.addActionListener(this);
    add("East",textButton);
    JPanel centerPanel = new JPanel(new GridLayout(2,3));
    JLabel[][] labelArray = new JLabel[2][3];
    for (int r=0;r<2;r++)
    {
      for (int c=0;c<3;c++)
      {
        labelArray[r][c] = new JLabel(" ("+r+":"+c+") ");
        centerPanel.add(labelArray[r][c]);
      }
    }
    add("Center",centerPanel);
    pack();
    setVisible(true);
  }


  public ImageIcon createImage(){
    BufferedImage exampleImage = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
    Graphics drawer = exampleImage.getGraphics();
    drawer.setColor(new Color(200,200,200));
    drawer.fillRect(0, 0, 50, 50);
    drawer.setColor(new Color(0,255,0));
    drawer.fillOval(20, 20, 10, 10);
    return new ImageIcon(exampleImage);
  }


  public static void main(String[] args) {
    GUI gui = new GUI();
    gui.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == textButton){
      textLabel.setText("Pushed");
    }
  }
}
