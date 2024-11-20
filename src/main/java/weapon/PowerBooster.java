package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class PowerBooster extends Attachment {

  public PowerBooster(Weapon baseWeapon) throws AttachmentException {
    super(baseWeapon);
  }


  @Override
  public int fire(int distance) throws WeaponException {
    return (int) ((1 + ((double) getCurrentAmmo() / getMaxAmmo())) * base.fire(distance));
  }


  @Override
  public java.lang.String toString() {
    return base.toString() + " +PowerBooster";
  }
}
