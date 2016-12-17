package essentinal.tendaysofsun.math;

public final class Vector2f {
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

  public Vector2f setX(final float x) {
    this.x = x;
    return this;
  }

  public Vector2f setY(final float y) {
    this.y = y;
    return this;
  }

  public Vector2f addLocal(final Vector2f vec) {
    this.x += vec.x;
    this.y += vec.y;
    return this;
  }

  public Vector2f scale(final float scale) {
    return new Vector2f(x * scale, y * scale);
  }

  public Vector2f normalizeLocal() {
    if (x != 0f || y != 0f) {
      final float length = getLength();
      if (length != 1f) {
        return divideLocal(length);
      }
    }

    return this;
  }

  public float getLength() {
    return (float) Math.sqrt(x * x + y * y);
  }

  public Vector2f divideLocal(final float scalar) {
    x /= scalar;
    y /= scalar;
    return this;
  }

  @Override
  public boolean equals(final Object o) {
    if (o instanceof Vector2f) {
      final Vector2f that = (Vector2f) o;

      return that.x == this.x && that.y == this.y;
    }

    return false;
  }
}
