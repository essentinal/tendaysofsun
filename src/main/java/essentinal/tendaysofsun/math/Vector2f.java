package essentinal.tendaysofsun.math;

public class Vector2f {
  private float x;
  private float y;

  public Vector2f(final float x, final float y) {
    this.x = x;
    this.y = y;
  }

  public float getAngle() {
    return (float) Math.atan2(x, y);
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }

  public void setX(final float x) {
    this.x = x;
  }

  public void add(final Vector2f vec) {
    this.x += vec.x;
    this.y += vec.y;
  }

  public Vector2f scale(final float scale) {
    return new Vector2f(x * scale, y * scale);
  }

  public Vector2f normalize() {
    final float length = length();
    if (length != 0) {
      return divide(length);
    }

    return divide(1);
  }

  public float length() {
    return (float) Math.sqrt(x * x + y * y);
  }

  public Vector2f divide(final float scalar) {
    x /= scalar;
    y /= scalar;
    return this;
  }
}
