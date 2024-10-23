package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

public interface Weapon extends TimerObserver {

  public int fire(int distance) throws WeaponException;

  public int getBaseDamage();

  public int getCurrentAmmo();

  public int getMaxRange();

  public int getNumAttachments();

  public int getRateOfFire();

  int getMaxAmmo();

  public int getShotsLeft();

  public void reload();

  public java.lang.String toString();

}
