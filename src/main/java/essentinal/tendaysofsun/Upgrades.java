package essentinal.tendaysofsun;

public class Upgrades {
  public enum CellCount {
    Level1(1, 0, 1), //
    Level2(2, 10000, 2), //
    Level3(3, 10000, 3), //
    Level4(4, 15000, 4), //
    Level5(5, 15000, 5), //
    Level6(6, 20000, 6), //
    Level7(7, 20000, 7), //
    Level8(8, 25000, 8), //
    Level9(9, 25000, 9), //
    Level10(10, 30000, 10), //
    Level11(11, 35000, 11), //
    Level12(12, 40000, 12);

    public final int level;
    public final int price;
    public final int count;

    CellCount(final int level, final int price, final int count) {
      this.level = level;
      this.price = price;
      this.count = count;
    }

    public CellCount nextLevel() {
      if (isUpgradeable()) {
        return CellCount.valueOf("Level" + (level + 1));
      }
      return null;
    }

    public boolean isUpgradeable() {
      return level < 12;
    }
  }

  public enum InverterPower {
    Level1(1, 0, 1f), //
    Level2(2, 5000, 1.1f), //
    Level3(3, 5000, 1.2f), //
    Level4(4, 5000, 1.4f), //
    Level5(5, 5000, 1.6f), //
    Level6(6, 10000, 1.8f), //
    Level7(7, 10000, 2.0f), //
    Level8(8, 15000, 2.2f), //
    Level9(9, 15000, 2.4f), //
    Level10(10, 20000, 2.6f), //
    Level11(11, 20000, 2.8f), //
    Level12(12, 25000, 3.0f);

    public final int level;
    public final int price;
    public final float factor;

    InverterPower(final int level, final int price, final float factor) {
      this.level = level;
      this.price = price;
      this.factor = factor;
    }

    public InverterPower nextLevel() {
      if (isUpgradeable()) {
        return InverterPower.valueOf("Level" + (level + 1));
      }
      return null;
    }

    public boolean isUpgradeable() {
      return level < 12;
    }
  }

  private static final Upgrades INSTANCE = new Upgrades();

  private CellCount cellCount = CellCount.Level1;
  private InverterPower inverterPower = InverterPower.Level1;

  public static Upgrades getInstance() {
    return INSTANCE;
  }

  /**
   * Private constructor to prevent instantiation.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  private Upgrades() {

  }

  public int getTotalTechPoints() {
    return cellCount.level + inverterPower.level;
  }

  public CellCount getCellCount() {
    return cellCount;
  }

  public InverterPower getInverterPower() {
    return inverterPower;
  }

  public int upgradeCellCount() {
    if (cellCount.isUpgradeable()) {
      cellCount = cellCount.nextLevel();
      return cellCount.price;
    }
    return 0;
  }

  public int upgradeInverterPower() {
    if (inverterPower.isUpgradeable()) {
      inverterPower = inverterPower.nextLevel();
      return inverterPower.price;
    }
    return 0;
  }

}
