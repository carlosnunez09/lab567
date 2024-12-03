package gameplay;

import environment.Environment;
import gui.Gui2;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;
import recovery.RecoveryNone;

import java.util.ArrayList;
import java.util.List;

public class Simulator implements TimerObserver {
  private static Environment env = Environment.getEnvironment(20, 20);
  private SimpleTimer timer;
  private List<Human> listHuman;
  private List<Alien> listAlien;
  private ArrayList<LifeForm> listLifeForm;

  public Simulator(Environment env, SimpleTimer timer, int numHumans, int numAliens) {
    // Initialize lists
    listHuman = new RandList<>(new RandHuman(), numHumans).choose();
    System.out.println(listHuman.get(1));
    env.addLifeForm(listHuman.get(1), 2, 2);
    listAlien = new RandList<>(new RandAlien(), numAliens).choose();

    // Place LifeForms in the environment
    int row = 0;
    int col = 0;
    for (int i = 0; i < listHuman.size(); i++) {
      //env.addLifeForm(listHuman.get(i), row, col);
      row += 3;
      if (row >= env.getNumRows()) { // Fixed bounds
        row = 0;
        col += 1;
      }
    }

    this.timer = timer;
  }

  @Override
  public void updateTime(int time) {
    // Timer update logic can be implemented here
  }

  public static void main(String[] args) {
    Gui2 gui = new Gui2();
    env.addObserver(gui);

    // Simulator starts for AI vs AI play
    SimpleTimer timer = new SimpleTimer(1000);
    Simulator sim = new Simulator(env, timer, 15, 10);

    // Start the timer
    timer.start();
  }

  // Random Generator Interfaces and Implementations
  interface Random<T> {
    T choose();
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

  class FromList<A> implements Random<A> {
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
}
