package gui;

import command.Invoker;
import command.InvokerBuilder;
import command.MoveCmd;
import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import weapon.ChainGun;
import weapon.Pistol;
import weapon.PlasmaCannon;
import weapon.Weapon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;



public class GUI2 {
  public static int scRow, scCol;

  public static void main(String[] args) {
    Environment e = Environment.getEnvironment(15, 15);
    e.clearBoard();

    Alien jeff = new Alien("Jeff", 15);
    Human bill = new Human("Bill", 15, 5);
    Human tim = new Human("Tim", 15, 5);

    bill.setDirection("East");

    PlasmaCannon p = new PlasmaCannon();
    Pistol pis = new Pistol();
    ChainGun CG = new ChainGun();
    

    e.addLifeForm(jeff, 5, 5);
    e.addLifeForm(bill, 2, 2);
    e.addLifeForm(tim, 1, 2);
    e.addWeapon(p, 1, 1);
    e.addWeapon(pis, 3, 2);
    e.addWeapon(CG, 1, 5);

    
    bill.pickUpWeapon(p);

    jeff.setLocation(5, 5);
    bill.setLocation(2,2);
    tim.setLocation(1, 2);



    //System.out.println(e.getLifeForm(1, 2).getClass().getName());


    //System.out.println();
    //System.out.println(e.getLifeForm(2, 2) instanceof Human);

    //ImageIcon imageIcon = new ImageIcon("src/main/java/gui/Pic/Pistol.png");

    /*String currentPath = System.getProperty("user.dir");
    System.out.println("Current working directory: " + currentPath);*/
    /*
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
    }
    */
    //invoker prams
    InvokerBuilder builder = new InvokerBuilder(e);  // Pass the environment
    Invoker invoker = builder.loadCommands();

    var tv = new TV(e);
    var r = new SimpleRemote(invoker);
  }

  public static int getscRow() {
    return getscRow();
  }

  public static int getscCol() {
    return getscCol();
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
  final int width = 800;
  final int height = 600;
  Environment env;
  ImageIcon human = new ImageIcon("src/main/java/gui/Human.jpg");
  ImageIcon alien = new ImageIcon("src/main/java/gui/Alien.jpg");
  JButton[][] numberOfButtons;

  ImageIcon offImage = new ImageIcon(new BufferedImage(355, 200,
          BufferedImage.TYPE_3BYTE_BGR));
  ImageIcon onImage = new ImageIcon("news.gif");
  ImageIcon pistol = new ImageIcon("src/main/java/gui/Pistol.png");

  ImageIcon plasma = new ImageIcon("src/main/java/gui/PlasmaCannon.jpg");

  ImageIcon chain = new ImageIcon("src/main/java/gui/ChainGun.png");



  JTextArea remoteDisplay;
  JTextArea textArea;

  LifeForm lifeform;
  Weapon[] weapon;
  String lifetype;
  String lifeformWeapon;
  String lifeformDirection;

  public TV(Environment env) {


    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(width, height);
    setLayout(new BorderLayout());
    this.env = env;
    col = this.env.getNumCols();
    row = this.env.getNumRows();
    numberOfButtons = new JButton[row][col];

    MoveCmd move = new MoveCmd(this.env);
    move.execute(2,2);





    JPanel panel = new JPanel();
    panel.setBounds(0, 0, (int) (width * 0.7), height);
    panel.setLayout(new GridLayout(row, col));
    panel.setBackground(Color.GRAY);
    //panel.setBounds(0, 0, 250, 250);


    textArea = new JTextArea();
    textArea.setBounds((int) (width * 0.7), 0, (int) (width * 0.3), height);
    textArea.setBackground(Color.GRAY);
    textArea.setEditable(false);
    Font newTextFieldFont=new Font(textArea.getFont().getName(),textArea.getFont().getStyle(),16);
    textArea.setFont(newTextFieldFont);

    //textArea.setPreferredSize(new Dimension(200, 800));

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        numberOfButtons[i][j] = new JButton();
        numberOfButtons[i][j].setPreferredSize(new Dimension((int) (width * 0.7)/row, height/col));
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
    this.setLocation(50, 50);
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

            lifeformDirection = env.getLifeForm(i, j).getCurrentDirection();
          } else {
            lifetype = "Blank";
            lifeformWeapon = "Not a Lifeform";
            lifeformDirection = "Lifeform is NOT currently selected";
          }

          if (env.getWeapons(i, j) != null) {
            weapon = env.getWeapons(i, j);
          }

          textArea.setText("The Current Cell is \n" + "Row: " + i + "\tCol: " + j +  "\n\n" +
                  "LifeForm Type is:\n" + lifetype + " \n\n" +
                  "LifeForm Weapon:\n" + lifeformWeapon +
                  "\n\nFirst Weapon in Cell is \n" + weapon[0] +
                  "\n" + "\nSecond Weapon in Cell is \n" + weapon[1] +
                  "\n" + "\nLifeForm is facing:\n" + lifeformDirection);


          //textArea.setText("A button was pressed\n");
          //I did not want to rewrite the code above so I just got your values, this is dumb and should not stay like this
          GUI2.scRow = i;
          GUI2.scCol = j;
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
    //System.out.println("Button Clicked" + GUI2.scCol + " " + GUI2.scRow);

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
    //Command c;
    JTextArea text;
    JPanel moveAttack;
    JPanel top;
    JPanel bottom;
    private final Invoker invoker;  // Make it a private field


    public SimpleRemote(Invoker invoker) {
      this.invoker = invoker;
      setLayout(new BorderLayout());
      moveAttack = new JPanel();
      moveAttack.setLayout(new GridLayout(1, 3));

      top = new JPanel();
      top.setLayout(new GridLayout(1, 3));

      bottom = new JPanel();
      bottom.setLayout(new GridLayout(1, 3));

      JButton down = new JButton("West");
      //down.setSize(5, 5);
      down.setBounds(100, 200, 100, 5);
      moveAttack.add(down, BorderLayout.WEST);

      JButton b = new JButton("Move");
      //b.setSize(500, 500);
      //b.setBounds(250, 100, 500, 50);
      moveAttack.add(b, BorderLayout.CENTER);

      JButton up = new JButton("East");
      //up.setSize(5, 5);
      up.setBounds(400, 100, 100, 5);
      moveAttack.add(up, BorderLayout.EAST);

      JButton getWeap1 = new JButton("Get Weapon 1");
      //North.setSize(5, 5);
      //getWeap1.setBounds(100, 300, 100, 5);
      top.add(getWeap1, BorderLayout.WEST);


      JButton North = new JButton("North");
      //North.setSize(5, 5);
      //North.setBounds(100, 300, 100, 5);
      top.add(North, BorderLayout.CENTER);

      JButton getWeap2 = new JButton("Get Weapon 2");
      //North.setSize(5, 5);
      //getWeap2.setBounds(100, 300, 100, 5);
      top.add(getWeap2, BorderLayout.EAST);

      JButton Drop = new JButton("Drop Weapon");
      //South.setSize(10, 10);
      //South.setBounds(100, 400, 100, 5);
      bottom.add(Drop, BorderLayout.WEST);

      JButton South = new JButton("South");
      //South.setSize(10, 10);
      South.setBounds(100, 400, 100, 5);
      bottom.add(South, BorderLayout.CENTER);

      JButton attack = new JButton("Attack");
      //attack.setSize(50, 50);
      //attack.setBounds(350, 100, 500, 50);
      bottom.add(attack, BorderLayout.EAST);

      add(top, BorderLayout.NORTH);
      add(moveAttack, BorderLayout.CENTER);
      add(bottom, BorderLayout.SOUTH);

      /// deprecated
//      b.addActionListener(a -> c.execute());
//      up.addActionListener(a -> c.execute());
//      down.addActionListener(a -> c.execute());
//      North.addActionListener(a -> c.execute());
//      South.addActionListener(a -> c.execute());
//      attack.addActionListener(a -> c.execute());
      ///


      down.addActionListener(a -> pressButton("west", GUI2.scRow, GUI2.scCol));
      b.addActionListener(a -> pressButton("move", GUI2.scRow, GUI2.scCol));
      up.addActionListener(a -> pressButton("east", GUI2.scRow, GUI2.scCol));
      North.addActionListener(a -> pressButton("north", GUI2.scRow, GUI2.scCol));
      //getWeap1.addActionListener(a -> pressButton("get1", 0, 0));
      //getWeap2.addActionListener(a -> pressButton("get2", 0, 0));
      //Drop.addActionListener(a -> pressButton("drop", 0, 0));
      South.addActionListener(a -> pressButton("south", GUI2.scRow, GUI2.scCol));
      attack.addActionListener(a -> pressButton("attack", GUI2.scRow, GUI2.scCol));


      setSize(500, 200);
      setVisible(true);
      setLocation(700, 200);
    }

    public JTextArea getTextArea() {
      return text;
    }

    public void pressButton(String s, int row, int col) {
      System.out.println("from GUI " + s + row + " " + col);

      try {
        switch (s) {
          case "north":
            invoker.getNorthCmd().execute(row, col);
            break;
          case "south":
            invoker.getSouthCmd().execute(row, col);
            break;
          case "east":
            invoker.getEastCmd().execute(row, col);
            break;
          case "west":
            invoker.getWestCmd().execute(row, col);
            break;
          case "attack":
            invoker.getAttackCmd().execute(row, col);
            break;
          case "drop":
            invoker.getDropCmd().execute(row, col);
            break;
          case "get1":
            invoker.getGet1Cmd().execute(row, col);
            break;
          case "get2":
            invoker.getGet2Cmd().execute(row, col);
            break;
          case "move":
            invoker.getMoveCmd().execute(row, col);
            break;
          case "reload":
            invoker.getReloadCmd().execute(row, col);
            break;
          default:
            System.out.println("Unknown command");
        }
      } catch (WeaponException | EnvironmentException e) {
        e.printStackTrace();
      }


    }

  }