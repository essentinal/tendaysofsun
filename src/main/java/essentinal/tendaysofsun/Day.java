package essentinal.tendaysofsun;

public enum Day {
  DAY1(0.01f, 0.2f, 0.2f, 0.030f),
  DAY2(0.025f, 0.2f, 0.25f, 0.032f),
  DAY3(0.03f, 0.2f, 0.3f, 0.034f),
  DAY4(0.09f, 0.2f, 0.35f, 0.036f),
  DAY5(0.1f, 0.2f, 0.4f, 0.038f),
  DAY6(0.12f, 0.2f, 0.45f, 0.040f),
  DAY7(0.16f, 0.2f, 0.5f, 0.042f),
  DAY8(0.20f, 0.2f, 0.55f, 0.044f),
  DAY9(0.30f, 0.2f, 0.6f, 0.046f),
  DAY10(0.32f, 0.2f, 0.65f, 0.05f);

  public final float pCloud;
  public final float cloudSpeed;
  public final float raySpread;
  public final float raySpeed;

  private Day(final float pCloud, final float cloudSpeed, final float raySpread,
      final float raySpeed) {
    this.pCloud = pCloud;
    this.cloudSpeed = cloudSpeed;
    this.raySpeed = raySpeed;
    this.raySpread = raySpread;
  }

  public int getNumber() {
    for (int i = 0; i < 10; i++) {
      final Day d = Day.values()[i];
      if (d.equals(this)) {
        return i + 1;
      }
    }
    return -1;
  }

  public static Day getDay(final int number) {
    if (number > 0 && number <= 10) {
      return Day.values()[number - 1];
    }
    return null;
  }
}
