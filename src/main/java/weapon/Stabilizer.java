package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Stabilizer extends Attachment {

  public Stabilizer(Weapon baseWeapon) throws AttachmentException {
    super(baseWeapon);
  }

  @Override
  public int fire(int distance) throws WeaponException {
    int damage = (int) (base.fire(distance) * 1.25);

    if (getCurrentAmmo() <= 0) {
      reload();
    }

    return damage;
  }



  @Override
  public java.lang.String toString() {
    return base.toString() + " +Stabilizer";
  }
}
