package weapon;

import exceptions.WeaponException;
import gameplay.TimerObserver;

public interface Weapon extends TimerObserver {

  int fire(int distance) throws WeaponException;

  int getBaseDamage();

  int getCurrentAmmo();

  int getMaxRange();

  int getNumAttachments();

  int getRateOfFire();

  int getMaxAmmo();

  int getShotsLeft();

  void reload();

  java.lang.String toString();

}
