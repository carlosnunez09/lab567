package command;

import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;

public class InvokerBuilder {

  private Invoker invoker;
  private Environment environment;

  public InvokerBuilder(Environment environment) {
    this.environment = environment;
    invoker = new Invoker();
  }

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