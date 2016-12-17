package essentinal.tendaysofsun;

public class Upgrades {
  public enum CellCount {
    LEVEL_1(0, 1), //
    LEVEL_2(10000, 2), //
    LEVEL_3(10000, 3), //
    LEVEL_4(15000, 4), //
    LEVEL_5(15000, 5), //
    LEVEL_6(20000, 6), //
    LEVEL_7(20000, 7), //
    LEVEL_8(25000, 8), //
    LEVEL_9(25000, 9), //
    LEVEL_10(30000, 10), //
    LEVEL_11(35000, 11), //
    LEVEL_12(40000, 12);

    public final int price;
    public final int count;

    CellCount(final int price, final int count) {
      this.price = price;
      this.count = count;
    }

    public CellCount nextLevel() {
      if (isUpgradeable()) {
        return CellCount.values()[ordinal() + 1];
      }
      return null;
    }

    public boolean isUpgradeable() {
      return ordinal() < LEVEL_12.ordinal();
    }

    public int getLevel() {
      return ordinal() + 1;
    }
  }

  public enum InverterPower {
    LEVEL_1(0, 1f), //
    LEVEL_2(5000, 1.1f), //
    LEVEL_3(5000, 1.2f), //
    LEVEL_4(5000, 1.4f), //
    LEVEL_5(5000, 1.6f), //
    LEVEL_6(10000, 1.8f), //
    LEVEL_7(10000, 2.0f), //
    LEVEL_8(15000, 2.2f), //
    LEVEL_9(15000, 2.4f), //
    LEVEL_10(20000, 2.6f), //
    LEVEL_11(20000, 2.8f), //
    LEVEL_12(25000, 3.0f);

    public final int price;
    public final float factor;

    InverterPower(final int price, final float factor) {
      this.price = price;
      this.factor = factor;
    }

    public InverterPower nextLevel() {
      if (isUpgradeable()) {
        return InverterPower.values()[ordinal() + 1];
      }
      return null;
    }

    public int getLevel() {
      return ordinal() + 1;
    }

    public boolean isUpgradeable() {
      return ordinal() < LEVEL_12.ordinal();
    }
  }

  private static final Upgrades INSTANCE = new Upgrades();

  private CellCount cellCount = CellCount.LEVEL_1;
  private InverterPower inverterPower = InverterPower.LEVEL_1;

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
    return cellCount.getLevel() + inverterPower.getLevel();
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
