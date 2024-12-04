package state;

import command.Acquire1Cmd;
import command.MoveCmd;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import gameplay.Random;
import gameplay.Simulator;

import java.util.List;

public class NoWeaponState extends ActionState {
  AIContext ai;

  public NoWeaponState(AIContext context) {

    super(context);
    ai = context;
  }

  public void executeAction() throws WeaponException, EnvironmentException {
    if (context.getCurrentState() != context.dead) {
      if (ai.getEnvironment().getWeapons(ai.getLifeForm().getRow(), ai.getLifeForm().getCol())[0] != null) {
        Acquire1Cmd weap1 = new Acquire1Cmd(context.getEnvironment());
        weap1.execute(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
        context.setCurrentState(context.getHasWeaponState());
      } else {
        ai.getLifeForm().setDirection(new RandDirection().choose());
        MoveCmd move = new MoveCmd(ai.getEnvironment());
        move.execute(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
      }
    }
    ai.getEnvironment().notifyObservers(ai.getLifeForm());
  }


  public class RandDirection implements Random<String> {
    List<String> directions = List.of("North", "South", "West", "East");
    @Override
    public String choose() {
      return new FromList<String>(directions).choose();
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
}
