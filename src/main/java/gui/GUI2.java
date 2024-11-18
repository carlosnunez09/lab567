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
    Pistol pistol = new Pistol();
    

    e.addLifeForm(jeff, 1, 1);
    e.addLifeForm(bill, 2, 2);
    e.addLifeForm(tim, 4, 5);
    e.addWeapon(p, 1, 1);
    e.addWeapon(pis, 3, 2);
    e.addWeapon(CG, 1, 5);
    e.addWeapon(pistol, 1, 1);

    
    bill.pickUpWeapon(p);

    jeff.setLocation(1, 1);
    bill.setLocation(2,2);
    tim.setLocation(4, 5);



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
    var r = new SimpleRemote(invoker, e);
  }

}

interface Command {
  public void execute();
}

class TV extends JFrame{
  JLabel label;
  boolean off = true;
  boolean up = true;
  int col;
  int row;
  final int width = 800;
  final int height = 600;
  Environment env;
  //ImageIcon human = new ImageIcon("src/main/java/gui/Human.jpg");
  //ImageIcon alien = new ImageIcon("src/main/java/gui/Alien.jpg");
  JButton numberOfButtons[][];

  ImageIcon offImage = new ImageIcon(new BufferedImage(355, 200,
          BufferedImage.TYPE_3BYTE_BGR));
  ImageIcon pistol = new ImageIcon("src/main/java/gui/Pistol.jpg");

  ImageIcon plasma = new ImageIcon("src/main/java/gui/PlasmaCannon.jpg");

  ImageIcon chain = new ImageIcon("src/main/java/gui/ChainGun.jpg");



  JTextArea remoteDisplay;
  JTextArea textArea;

  LifeForm tempLifeform;
  Weapon[] weapon;
  String lifetype;
  String lifeformWeapon;
  String lifeformDirection;
  String lifeName;
  String lifeHealth;
  String maxHealth;
  MoveCmd move;
  JPanel panel;

  int lifeformRow;
  int lifeformCol;


  public TV(Environment env) {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(width, height);
    setLayout(new BorderLayout());
    this.env = env;
    col = this.env.getNumCols();
    row = this.env.getNumRows();
    numberOfButtons = new JButton[row][col];





    panel = new JPanel();
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

    var sideBar = new JPanel();

    createGrid();

    add(textArea, BorderLayout.CENTER);
    add(panel, BorderLayout.WEST);

    //add(sideBar, BorderLayout.WEST);


    //label = new JLabel(offImage);

    //panel.add(label);

    //pack();
    this.setLocation(50, 50);
    setVisible(true);
  }


  public void createGrid() {
    //numberOfButtons = new JButton[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        numberOfButtons[i][j] = new JButton();
        numberOfButtons[i][j].setPreferredSize(new Dimension((int) (width * 0.7)/row, height/col));
        //numberOfButtons[i][j].setFocusable(false);
        numberOfButtons[i][j].addActionListener(this::actionPerformed);
        if (env.getWeapons(i, j) == null) {
          numberOfButtons[i][j].setBackground(Color.WHITE);
        } else if (env.getWeapons(i, j)[0] instanceof Pistol) {
          numberOfButtons[i][j].setIcon(pistol);
        } else if (env.getWeapons(i, j)[0] instanceof PlasmaCannon){
          numberOfButtons[i][j].setIcon(plasma);
        } else if (env.getWeapons(i, j)[0] instanceof ChainGun) {
          numberOfButtons[i][j].setIcon(chain);
        } else if (env.getWeapons(i, j)[1] instanceof Pistol) {
          numberOfButtons[i][j].setIcon(pistol);
        } else if (env.getWeapons(i, j)[1] instanceof PlasmaCannon){
          numberOfButtons[i][j].setIcon(plasma);
        } else if (env.getWeapons(i, j)[1] instanceof ChainGun) {
          numberOfButtons[i][j].setIcon(chain);
        }

        if (env.getLifeForm(i, j) == null) {
          numberOfButtons[i][j].setBackground(Color.WHITE);
        } else {
          numberOfButtons[i][j].setIcon(getIcon(env.getLifeForm(i, j)));

        }
        panel.add(numberOfButtons[i][j]);
      }
    }
  }

  public void removePanel(){
    remove(panel);
    panel = new JPanel();
    panel.setBounds(0, 0, (int) (width * 0.7), height);
    panel.setLayout(new GridLayout(row, col));
    panel.setBackground(Color.GRAY);
    add(panel, BorderLayout.WEST);

  }


  public void updateGUI(){
    removePanel();
    createGrid();
  }

  public ImageIcon getIcon(LifeForm l) {
    ImageIcon icon = new ImageIcon("src/main/java/gui/Pic/" + l.getLifetype(l) + l.getCurrentDirection() + ".jpg");
    return icon;

  }

  public void updateIcon(Environment e, int r, int c){
    if (r != lifeformRow || c != lifeformCol) {
      System.out.println("Icon been changed");
      JButton button = numberOfButtons[lifeformRow][lifeformCol];
      JButton button2 = numberOfButtons[r][c];
      button2.setIcon(getIcon(e.getLifeForm(r, c)));
      button.setIcon(null);
      button.setBackground(Color.white);
    }
  }

  public void updateText(){
    for (int i = 0; i < tempLifeform.getRow() + 1; i++) {
      for (int j = 0; j < tempLifeform.getCol() + 1; j++) {
          if (env.getLifeForm(i, j) != null) {
            lifetype = env.getLifeForm(i, j).getLifetype(env.getLifeForm(i, j));
            lifeformRow = i;
            lifeformCol = j;

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
                "LifeForm Health is at:\n" + lifeHealth + "\\" + maxHealth +  "\n\n" +
                "LifeForm Name is:\n" + lifeName + " \n\n" +
                "LifeForm Type is:\n" + lifetype + " \n\n" +
                "LifeForm Weapon:\n" + lifeformWeapon +
                "\n\nFirst Weapon in Cell is \n" + weapon[0] +
                "\n" + "\nSecond Weapon in Cell is \n" + weapon[1] +
                "\n" + "\nLifeForm is facing:\n" + lifeformDirection);
      }
    }

  }



  public void actionPerformed(ActionEvent e) {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (e.getSource() == numberOfButtons[i][j]) {
          //numberOfButtons[i][j].setBackground(Color.YELLOW);
          //createGrid();
          //updateGrid(i, j);



          if (env.getLifeForm(i, j) != null) {
            tempLifeform = env.getLifeForm(i, j);
            lifetype = env.getLifeForm(i, j).getLifetype(env.getLifeForm(i, j));
            lifeHealth = String.valueOf(env.getLifeForm(i, j).getCurrentLifePoints());
            maxHealth = String.valueOf(env.getLifeForm(i, j).getMaxLife());
            lifeName = env.getLifeForm(i, j).getName();

            lifeformRow = i;
            lifeformCol = j;

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
            lifeHealth = "Lifeform is NOT currently selected";
            lifeName = "Lifeform is NOT currently selected";
          }

          if (env.getWeapons(i, j) != null) {
            weapon = env.getWeapons(i, j);
          }

          textArea.setText("The Current Cell is \n" + "Row: " + i + "\tCol: " + j +  "\n\n" +
                  "LifeForm Health is at:\n" + lifeHealth + "\\" + maxHealth +  "\n\n" +
                  "LifeForm Name is:\n" + lifeName + " \n\n" +
                  "LifeForm Type is:\n" + lifetype + " \n\n" +
                  "LifeForm Weapon:\n" + lifeformWeapon +
                  "\n\nFirst Weapon in Cell is \n" + weapon[0] +
                  "\n" + "\nSecond Weapon in Cell is \n" + weapon[1] +
                  "\n" + "\nLifeForm is facing:\n" + lifeformDirection);


          //textArea.setText("A button was pressed\n");
          //I did not want to rewrite the code above so I just got your values, this is dumb and should not stay like this
          updateGUI();
        }
      }
    }
  }




}

class SimpleRemote extends JFrame {
  //Command c;
  JTextArea text;
  JPanel moveAttack;
  JPanel top;
  JPanel bottom;
  JPanel panel;
  private final Invoker invoker;  // Make it a private field
  TV tv;
  Environment env;


  public SimpleRemote(Invoker invoker, Environment e) {
    tv = new TV(e);
    env = tv.env;
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

    JButton move = new JButton("Move");
    //b.setSize(500, 500);
    //b.setBounds(250, 100, 500, 50);
    moveAttack.add(move, BorderLayout.CENTER);

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


    down.addActionListener(a -> pressButton("west", tv.lifeformRow, tv.lifeformCol));
    move.addActionListener(a -> pressButton("move", tv.lifeformRow, tv.lifeformCol));
    up.addActionListener(a -> pressButton("east", tv.lifeformRow, tv.lifeformCol));
    North.addActionListener(a -> pressButton("north", tv.lifeformRow, tv.lifeformCol));
    South.addActionListener(a -> pressButton("south", tv.lifeformRow, tv.lifeformCol));
    attack.addActionListener(a -> pressButton("attack", tv.lifeformRow, tv.lifeformCol));
    getWeap1.addActionListener(a -> pressButton("get1", tv.lifeformRow, tv.lifeformCol));
    getWeap2.addActionListener(a -> pressButton("get2", tv.lifeformRow, tv.lifeformCol));


    setSize(500, 200);
    setVisible(true);
    setLocation(700, 200);
  }

  public JTextArea getTextArea() {
    return text;
  }

  public void updateFrame() throws WeaponException, EnvironmentException {
    LifeForm l = tv.env.getLifeForm(tv.lifeformRow, tv.lifeformCol);

    int r = tv.lifeformRow;
    int c = tv.lifeformCol;
    invoker.getMoveCmd().execute(r, c);

    int lifeformCol = l.getCol();
    int lifeformRow = l.getRow();

    if (r != lifeformRow || c != lifeformCol) {
      System.out.println("Icon been changed");
      JButton button2 = tv.numberOfButtons[lifeformRow][lifeformCol];
      JButton button = tv.numberOfButtons[r][c];
      button2.setIcon(tv.getIcon(env.getLifeForm(lifeformRow, lifeformCol)));
      button.setIcon(null);
      button.setBackground(Color.white);
    }
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
          //invoker.getMoveCmd().execute(row, col);
          updateFrame();
          //tv.updateGUI();
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
    tv.updateText();
    tv.updateGUI();
  }

}