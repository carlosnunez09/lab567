package lifeform;

public class MockLifeForm extends LifeForm {

  public MockLifeForm(String name, int points) {
    this(name, points, 0);
  }

  public MockLifeForm(String name, int points, int attackDmg) {
    super(name, points, attackDmg);
  }

}
