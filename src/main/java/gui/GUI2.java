package gui;

import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;



public class GUI2 {

  public static void main(String[] args) {
    Environment e = new Environment(7, 7);
    Alien jeff = new Alien("Jeff", 15);
    Human bill = new Human("Bill", 15, 5);
    Human tim = new Human("Tim", 15, 5);

    PlasmaCannon p = new PlasmaCannon();
    Pistol pis = new Pistol();
    ChainGun CG = new ChainGun();
    

    e.addLifeForm(jeff, 5, 5);
    e.addLifeForm(bill, 2, 2);
    e.addWeapon(p, 1, 1);
    e.addWeapon(pis, 3, 2);
    e.addWeapon(CG, 1, 5);

    bill.pickUpWeapon(p);

    jeff.setLocation(5, 5);
    bill.setLocation(2,2);




    System.out.println(e.getLifeForm(2, 2).getClass().getName());


    //System.out.println(e.getLifeForm(2, 2).getClass().getName().equals("lifeform.Alien"));
    //System.out.println(e.getLifeForm(2, 2) instanceof Human);
    /*
    ImageIcon imageIcon = new ImageIcon("C:\\Users\\21ste\\Downloads\\DesignPatterns\\newLab5\\src\\main\\java\\gui\\Pistol.png");

    int status = imageIcon.getImageLoadStatus();

    switch (status) {
      case MediaTracker.LOADING:
        System.out.println("Image is still loading...");
        break;
      case MediaTracker.ABORTED:
        System.out.println("Image loading aborted.");
        break;
      case MediaTracker.ERRORED:
        System.out.println("Error loading image.");
        break;
      case MediaTracker.COMPLETE:
        System.out.println("Image loaded successfully.");
        break;
    }*/


    var tv = new TV(e);
    //var r = new SimpleRemote(() -> tv.toggle());;
  }
}

interface Command {
  public void execute();
}

class TV extends JFrame {
  JLabel label;
  boolean off = true;
  boolean up = true;
  int col;
  int row;
  Environment env;
  JButton[][] numberOfButtons;

  ImageIcon offImage = new ImageIcon(new BufferedImage(355, 200,
         BufferedImage.TYPE_3BYTE_BGR));
  ImageIcon onImage = new ImageIcon("news.gif");
  ImageIcon pistol = new ImageIcon("C:\\Users\\21ste\\Downloads\\DesignPatterns\\newLab5\\src\\main\\java\\gui\\Pistol.png");
  ImageIcon human = new ImageIcon("C:\\Users\\21ste\\Downloads\\DesignPatterns\\newLab5\\src\\main\\java\\gui\\Human.jpg");
  ImageIcon alien = new ImageIcon("C:\\Users\\21ste\\Downloads\\DesignPatterns\\newLab5\\src\\main\\java\\gui\\Alien.jpg");

  ImageIcon plasma = new ImageIcon("C:\\Users\\21ste\\Downloads\\DesignPatterns\\newLab5\\src\\main\\java\\gui\\PlasmaCannon.jpg");

  ImageIcon chain = new ImageIcon("C:\\Users\\21ste\\Downloads\\DesignPatterns\\newLab5\\src\\main\\java\\gui\\ChainGun.png");



  JTextArea remoteDisplay;
  JTextArea textArea;

  LifeForm lifeform;
  Weapon[] weapon;
  String lifetype;
  String lifeformWeapon;

  public TV(Environment env) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 1000);
    setLayout(new BorderLayout());
    this.env = env;
    col = this.env.getNumCols();
    row = this.env.getNumRows();
    numberOfButtons = new JButton[row][col];





    JPanel panel = new JPanel();
    panel.setBounds(0, 0, 800, 800);
    panel.setLayout(new GridLayout(row, col));
    panel.setBackground(Color.GRAY);
    //panel.setBounds(0, 0, 250, 250);


    textArea = new JTextArea();
    textArea.setBounds(800, 0, 200, 800);
    textArea.setBackground(Color.GRAY);
    textArea.setEditable(false);

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        numberOfButtons[i][j] = new JButton();
        numberOfButtons[i][j].setPreferredSize(new Dimension(64, 64));
        //numberOfButtons[i][j].setFocusable(false);
        numberOfButtons[i][j].addActionListener(this::actionPerformed);
        if (env.getLifeForm(i, j) == null) {
          numberOfButtons[i][j].setBackground(Color.WHITE);
        } else if (env.getLifeForm(i, j) instanceof Human) {
          numberOfButtons[i][j].setIcon(human);
        } else if (env.getLifeForm(i, j) instanceof Alien) {
          numberOfButtons[i][j].setIcon(alien);
        }
        if (env.getWeapons(i, j) == null) {
          numberOfButtons[i][j].setBackground(Color.WHITE);
        } else if (env.getWeapons(i, j)[0] instanceof Pistol) {
          numberOfButtons[i][j].setIcon(pistol);
        } else if (env.getWeapons(i, j)[0] instanceof PlasmaCannon){
          numberOfButtons[i][j].setIcon(plasma);
        } else if (env.getWeapons(i, j)[0] instanceof ChainGun) {
          numberOfButtons[i][j].setIcon(chain);
        }
        panel.add(numberOfButtons[i][j]);
      }
    }

    var sideBar = new JPanel();

    add(textArea, BorderLayout.CENTER);
    add(panel, BorderLayout.WEST);

    //add(sideBar, BorderLayout.WEST);


    //label = new JLabel(offImage);

    //panel.add(label);

    //pack();
    this.setLocation(100, 100);
    setVisible(true);
  }


  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (e.getSource() == numberOfButtons[i][j]) {
          //numberOfButtons[i][j].setBackground(Color.YELLOW);




          if (env.getLifeForm(i, j) != null) {
            if (env.getLifeForm(i, j).getClass().getName() == "lifeform.Alien"){
              lifetype = "Alien";
            } else if (env.getLifeForm(i, j).getClass().getName() == "lifeform.Human"){
              lifetype = "Human";
            }

            if (env.getLifeForm(i, j).hasWeapon()){
              lifeformWeapon = env.getLifeForm(i, j).getWeapon().toString();
            } else {
              lifeformWeapon = "No Weapons Equipped";
            }
          } else {
            lifetype = "Blank";
            lifeformWeapon = "Not a Lifeform";
          }

          if (env.getWeapons(i, j) != null) {
            weapon = env.getWeapons(i, j);
          }

          textArea.setText("The Current Cell is \n" + "Row: " + i + "\tCol: " + j +  "\n\n" +
                  "LifeForm Type is " + lifetype + " \n\n" +
                  "LifeForm Weapon: " + lifeformWeapon +
                  "\n\nFirst Weapon in Cell: is \n" + weapon[0] +
                  "\n" + "\nSecond Weapon in Cell is \n" + weapon[1] +
                  "\n");



          //textArea.setText("A button was pressed\n");
        }
      }
    }
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