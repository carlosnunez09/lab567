package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;

public abstract class Attachment implements Weapon {
  protected Weapon base;


  /**
   * Initialize Attachment.
   *
   * @param base Weapon to add attachment too.
   * @throws AttachmentException
   */
  public Attachment(Weapon base) throws AttachmentException {
    this.base = base;

    if (getNumAttachments() > 2) {
      throw new AttachmentException("To Many Attachments");
    }
  }


  public abstract int fire(int distance) throws WeaponException;

  @Override
  public int getBaseDamage() {
    return base.getBaseDamage();
  }

  @Override
  public int getCurrentAmmo() {
    return base.getCurrentAmmo();
  }

  @Override
  public int getMaxAmmo() {
    return base.getMaxAmmo();
  }

  @Override
  public int getMaxRange() {
    return base.getMaxRange();
  }

  @Override
  public int getNumAttachments() {
    return base.getNumAttachments() + 1;
  }

  @Override
  public int getRateOfFire() {
    return base.getRateOfFire();
  }

  @Override
  public int getShotsLeft() {
    return base.getShotsLeft();
  }

  @Override
  public void reload() {
    base.reload();
  }

  @Override
  public void updateTime(int time) {
    base.updateTime(time);
  }


}
