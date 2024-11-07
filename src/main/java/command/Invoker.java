package command;

import exceptions.WeaponException;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Invoker {


  private Command northCmd;
  private Command southCmd;
  private Command eastCmd;
  private Command westCmd;
  private Command attackCmd;
  private Command dropCmd;
  private Command get1Cmd;
  private Command get2Cmd;
  private Command moveCmd;
  private Command reloadCmd;

  public void setNorth(Command northCmd) { this.northCmd = northCmd; }
  public void setSouth(Command southCmd) { this.southCmd = southCmd; }
  public void setEast(Command eastCmd) { this.eastCmd = eastCmd; }
  public void setWest(Command westCmd) { this.westCmd = westCmd; }
  public void setAttack(Command attackCmd) { this.attackCmd = attackCmd; }
  public void setDrop(Command dropCmd) { this.dropCmd = dropCmd; }
  public void setGet1(Command get1Cmd) { this.get1Cmd = get1Cmd; }
  public void setGet2(Command get2Cmd) { this.get2Cmd = get2Cmd; }
  public void setMove(Command moveCmd) { this.moveCmd = moveCmd; }
  public void setReload(Command reloadCmd) { this.reloadCmd = reloadCmd; }


  public Command getNorthCmd() { return northCmd; }
  public Command getSouthCmd() { return southCmd; }
  public Command getEastCmd() { return eastCmd; }
  public Command getWestCmd() { return westCmd; }
  public Command getAttackCmd() { return attackCmd; }
  public Command getDropCmd() { return dropCmd; }
  public Command getGet1Cmd() { return get1Cmd; }
  public Command getGet2Cmd() { return get2Cmd; }
  public Command getMoveCmd() { return moveCmd; }
  public Command getReloadCmd() { return reloadCmd; }
}
