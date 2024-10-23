package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public class Scope extends Attachment {


  public Scope(Weapon base) throws AttachmentException {
    super(base);
  }


  @Override
  public int fire(int distance) throws WeaponException {
    if (base.getMaxRange() < distance && distance <= getMaxRange()) {
      return (base.fire(base.getMaxRange()) + 5);
    }

    return (int) ((1 + ((double)(getMaxRange() - distance) / getMaxRange())) * base.fire(distance));
  }

  @Override
  public int getMaxRange() {
    return base.getMaxRange() + 10;
  }


  @Override
  public java.lang.String toString() {
    return base.toString() + " +Scope";
  }

}
