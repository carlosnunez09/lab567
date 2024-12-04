package gameplay;

import command.Invoker;
import command.InvokerBuilder;
import command.MoveCmd;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import gui.Gui2;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;
import state.AIContext;
import state.ActionState;
import weapon.*;

import java.util.ArrayList;
import java.util.List;

public class Simulator implements TimerObserver {
  private static Environment env = Environment.getEnvironment(20, 20);
  private SimpleTimer timer;
  private List<Human> listHuman;
  private List<Alien> listAlien;
  private ArrayList<LifeForm> listLifeForm = new ArrayList<>();
  public ArrayList<AIContext> ais = new ArrayList<AIContext>();
  public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
  private static Gui2.Grid t;

  public Simulator(Environment env, SimpleTimer timer, int numHumans, int numAliens) throws AttachmentException, WeaponException, EnvironmentException {
    // Initialize lists
    listHuman = new RandList<>(new RandHuman(), numHumans).choose();
    listAlien = new RandList<>(new RandAlien(), numAliens).choose();
    listLifeForm.addAll(listAlien);
    listLifeForm.addAll(listHuman);

    addWeapons(numAliens + numHumans);

    // Place LifeForms in the environment
    int row = 0;
    int col = 0;
    for (int i = 0; i < listLifeForm.size(); i++) {
      env.addLifeForm(listLifeForm.get(i), row, col);
      listLifeForm.get(i).setLocation(row, col);
      listLifeForm.get(i).setDirection(new RandDirection().choose());
      AIContext ai = new AIContext(listLifeForm.get(i), env);
      //System.out.println(ai.getLifeForm());
      ais.add(ai);
      row += 4;
      if (row >= env.getNumRows()) { // Fixed bounds
        row = 0;
        col += 2;
      }
    }











    this.timer = timer;
    t =  new Gui2.Grid(env);

    while (true) {
      ais.forEach(a -> {
        a.updateTime(1);
        if (a.getCurrentState() == a.getDeadState()){
          ais.remove(a);
        }
      });
      //updateTime(1);
    }
  }

  @Override
  public void updateTime(int time) {
    //t.updateGui();
  }


  public static void main(String[] args) throws AttachmentException, WeaponException, EnvironmentException {


    // Simulator starts for AI vs AI play
    SimpleTimer timer = new SimpleTimer(1000);
    Simulator sim = new Simulator(env, timer, 15, 10);

    env.addObserver(t);

    // Start the timer
    timer.start();

  }

  // Random Generator Interfaces and Implementations
  interface Random<T> {
    T choose();
  }

  public class RandDirection implements Random<String>{
    List<String> directions = List.of("North", "South", "West", "East");
    @Override
    public String choose() {
      return new FromList<String>(directions).choose();
    }

  }


  class RandInt implements Random<Integer> {
    private int lo;
    private int hi;

    public RandInt(int lo, int hi) {
      this.lo = lo;
      this.hi = hi;
    }

    @Override
    public Integer choose() {
      return new java.util.Random().nextInt(hi - lo) + lo;
    }
  }

  class RandBool implements Random<Boolean> {
    @Override
    public Boolean choose() {
      return new RandInt(0, 2).choose() == 0;
    }
  }

  class RandList<A> implements Random<List<A>> {
    private Random<A> randomGenerator;
    private int size;

    public RandList(Random<A> randomGenerator, int size) {
      this.randomGenerator = randomGenerator;
      this.size = size;
    }

    @Override
    public List<A> choose() {
      List<A> result = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        result.add(randomGenerator.choose());
      }
      return result;
    }
  }

  public class FromList<A> implements Random<A> {
    private List<A> choices;

    public FromList(List<A> choices) {
      this.choices = choices;
    }

    @Override
    public A choose() {
      return choices.get(new RandInt(0, choices.size()).choose());
    }
  }


  // Random Generators for LifeForms
  class RandHuman implements Random<Human> {
    private List<String> names = List.of("Alice", "Bob", "Chad", "Denise");

    @Override
    public Human choose() {
      return new Human(new FromList<>(names).choose(),
              new RandInt(30, 50).choose(),
              new RandInt(0, 10).choose());
    }
  }

  class RandAlien implements Random<lifeform.Alien> {
    private List<String> names = List.of("E.T.", "Xenomorph", "Zoiberg", "Roger");

    @Override
    public Alien choose() {
      return new Alien(new FromList<>(names).choose(),
              (Integer) new RandInt(30, 50).choose(),
              (RecoveryBehavior) new RandRecovery().choose());
    }
  }

  class RandRecovery implements Random<recovery.RecoveryBehavior> {
    private List<RecoveryBehavior> recoveries = List.of(new RecoveryNone(), new RecoveryLinear(2), new RecoveryFractional(2));

    @Override
    public RecoveryBehavior choose() {
      return new FromList<>(recoveries).choose();
    }
  }


  private void addWeapons(int totLifeForms) throws AttachmentException {
    for (int i = 0; i < totLifeForms; i++) {
      boolean isAdded = false;
      Weapon w = new RandWeapon().choose();
      weapons.add(w);
      while (!isAdded) {
        isAdded = env.addWeapon(w, new RandInt(0, env.getNumRows() - 1).choose(), new RandInt(0, env.getNumCols() - 1).choose());
      }
    }
  }

  public class RandWeapon implements Random<Weapon> {
    List<Weapon> choices = List.of(new ChainGun(), new Pistol(), new PlasmaCannon());
    Weapon weapon = new FromList<Weapon>(choices).choose();
    List<Attachment> attachments = List.of(new Scope(weapon), new Stabilizer(weapon), new PowerBooster(weapon));
    public RandWeapon() throws AttachmentException {
    }
    @Override
    public Weapon choose() {
      for (int i = 0; i < new RandInt(0, 3).choose(); i++) {
        weapon = new FromList<Attachment>(attachments).choose();
        if (i == 0) {
          try {
            attachments = List.of(new Scope(weapon), new Stabilizer(weapon), new PowerBooster(weapon));
          } catch (AttachmentException e) {
            throw new RuntimeException(e);
          }
        }
      }
      return weapon;
    }
  }
}
