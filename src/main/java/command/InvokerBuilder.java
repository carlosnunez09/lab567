package command;

import environment.Environment;

public class InvokerBuilder {

  private final Invoker invoker;
  private final Environment environment;

  public InvokerBuilder(Environment environment) {
    this.environment = environment;
    invoker = new Invoker();
  }

  /**
   * Creates an Enviroment with provided rows and cols of the Cell class
   */

  public Invoker loadCommands() {
    Invoker invoker = new Invoker();

    // Direction commands
    invoker.setNorth(new TurnNorthCmd(environment));
    invoker.setSouth(new TurnSouthCmd(environment));
    invoker.setEast(new TurnEastCmd(environment));
    invoker.setWest(new TurnWestCmd(environment));

    // Action commands
    invoker.setAttack(new AttackCmd(environment));
    invoker.setDrop(new DropCmd(environment));
    invoker.setGet1(new Acquire1Cmd(environment));
    invoker.setGet2(new Acquire2Cmd(environment));
    invoker.setMove(new MoveCmd(environment));
    invoker.setReload(new ReloadCmd(environment));

    return invoker;
  }
}