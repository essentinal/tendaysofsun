package essentinal.tendaysofsun.math;

public class Vector2f {
  public float x;
  public float y;

  public Vector2f(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public float getAngle() {
    return (float) Math.atan2(x, y);
  }

  public void add(Vector2f vec) {
    this.x += vec.x;
    this.y += vec.y;
  }

  public Vector2f scale(float scale) {
    return new Vector2f(x * scale, y * scale);
  }

  public Vector2f normalize() {
    float length = length();
    if (length != 0) {
      return divide(length);
    }

    return divide(1);
  }

  public float length() {
    return (float) Math.sqrt(x * x + y * y);
  }

  public Vector2f divide(float scalar) {
    x /= scalar;
    y /= scalar;
    return this;
  }
}
