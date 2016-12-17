package essentinal.tendaysofsun.math;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

public class UTVector2f {

  @Test
  public void getX() throws Exception {
    assertEquals("X must be as expected", 2f, new Vector2f(2f, 0f).getX(), 0f);
  }

  @Test
  public void getY() throws Exception {
    assertEquals("Y must be as expected", 2f, new Vector2f(0f, 2f).getY(), 0f);
  }

  @Test
  public void setX() throws Exception {
    assertEquals("X must be as expected", 2f,
        new Vector2f(0f, 0f).setX(2f).getX(), 0f);
  }

  @Test
  public void setXIsLocal() throws Exception {
    final Vector2f vector = new Vector2f(0f, 0f);
    assertSame("Vector must remain the same", vector, vector.setX(2f));
  }

  @Test
  public void setY() throws Exception {
    assertEquals("Y must be as expected", 2f,
        new Vector2f(0f, 0f).setY(2f).getY(), 0f);
  }

  @Test
  public void setYIsLocal() throws Exception {
    final Vector2f vector = new Vector2f(0f, 0f);
    assertSame("Vector must remain the same", vector, vector.setX(2f));
  }

  @Test
  public void addLocal() throws Exception {
    final Vector2f result = new Vector2f(0f, 0f).addLocal(new Vector2f(2f, 0f));
    assertEquals("X must be as expected", 2f, result.getX(), 0f);
    assertEquals("Y must be as expected", 0f, result.getY(), 0f);
  }

  @Test
  public void getAngleNone() throws Exception {
    final Vector2f vector = new Vector2f(0f, 0f);
    assertEquals("Vector must have no angle", 0f, vector.getAngle(), 0f);
  }

  @Test
  public void getAngleX() throws Exception {
    final Vector2f vector = new Vector2f(1f, 0f);
    assertEquals("Vector angle must be 90 degrees", Math.toRadians(90),
        vector.getAngle(), 0.0001f);
  }

  @Test
  public void getAngleXNeg() throws Exception {
    final Vector2f vector = new Vector2f(-1f, 0f);
    assertEquals("Vector angle must be -90 degrees", Math.toRadians(-90),
        vector.getAngle(), 0.0001f);
  }

  @Test
  public void getAngleY() throws Exception {
    final Vector2f vector = new Vector2f(0f, 1f);
    assertEquals("Vector angle must be 0 degrees", Math.toRadians(0),
        vector.getAngle(), 0.0001f);
  }

  @Test
  public void getAngleYNeg() throws Exception {
    final Vector2f vector = new Vector2f(0f, -1f);
    assertEquals("Vector angle must be 180 degrees", Math.toRadians(180),
        vector.getAngle(), 0.0001f);
  }

  @Test
  public void scaleNone() throws Exception {
    final Vector2f vector = new Vector2f(0f, 0f);
    assertEquals("Scaled vector must be as expected", 0f,
        vector.scale(2f).getX(), 0.0001f);
    assertEquals("Scaled vector must be as expected", 0f,
        vector.scale(2f).getY(), 0.0001f);
  }

  @Test
  public void scaleX() throws Exception {
    final Vector2f vector = new Vector2f(1f, 0f);
    assertEquals("Scaled vector must be as expected", 2f,
        vector.scale(2f).getX(), 0.0001f);
    assertEquals("Scaled vector must be as expected", 0f,
        vector.scale(2f).getY(), 0.0001f);
  }

  @Test
  public void scaleIsNotLocal() throws Exception {
    final Vector2f vector = new Vector2f(1f, 0f);
    final Vector2f scaled = vector.scale(2f);
    assertNotEquals("Vector is not locally modified", vector, scaled);
  }

  @Test
  public void getLengthNone() throws Exception {
    final Vector2f vector = new Vector2f(0f, 0f);
    assertEquals("Vector must have no length", 0f, vector.getLength(), 0f);
  }

  @Test
  public void getLengthX() throws Exception {
    final Vector2f vector = new Vector2f(1f, 0f);
    assertEquals("Vector length must be 1", 1, vector.getLength(), 0.0001f);
  }

  @Test
  public void getLengthXY() throws Exception {
    final Vector2f vector = new Vector2f(1f, 1f);
    assertEquals("Vector length must be 1", 1.4142, vector.getLength(),
        0.0001f);
  }

  @Test
  public void getLengthXNeg() throws Exception {
    final Vector2f vector = new Vector2f(-1f, 0f);
    assertEquals("Vector length must be 1", 1, vector.getLength(), 0.0001f);
  }

  @Test
  public void getLengthY() throws Exception {
    final Vector2f vector = new Vector2f(0f, 1f);
    assertEquals("Vector length must be 1", 1, vector.getLength(), 0.0001f);
  }

  @Test
  public void getLengthYNeg() throws Exception {
    final Vector2f vector = new Vector2f(0f, -1f);
    assertEquals("Vector length must be 1", 1, vector.getLength(), 0.0001f);
  }

  @Test
  public void normalizeLocalX0Y0() throws Exception {
    final Vector2f result = new Vector2f(0f, 0f).normalizeLocal();
    assertEquals("X must be as expected", 0f, result.getX(), 0f);
    assertEquals("Y must be as expected", 0f, result.getY(), 0f);
    assertEquals("Vector length must be 0", 0f, result.getLength(), 0f);
  }

  @Test
  public void normalizeLocalX1Y0() throws Exception {
    final Vector2f result = new Vector2f(1f, 0f).normalizeLocal();
    assertEquals("X must be as expected", 1f, result.getX(), 0f);
    assertEquals("Y must be as expected", 0f, result.getY(), 0f);
    assertEquals("Vector length must be 1", 1f, result.getLength(), 0.0001f);
  }

  @Test
  public void normalizeLocalX0Y1() throws Exception {
    final Vector2f result = new Vector2f(0f, 1f).normalizeLocal();
    assertEquals("X must be as expected", 0f, result.getX(), 0f);
    assertEquals("Y must be as expected", 1f, result.getY(), 0f);
    assertEquals("Vector length must be 1", 1f, result.getLength(), 0.0001f);
  }

  @Test
  public void normalizeLocalX1Y1() throws Exception {
    final Vector2f result = new Vector2f(1f, 1f).normalizeLocal();
    assertEquals("X must be as expected", 0.7071f, result.getX(), 0.0001f);
    assertEquals("Y must be as expected", 0.7071f, result.getY(), 0.0001f);
    assertEquals("Vector length must be 1", 1f, result.getLength(), 0.0001f);
  }

  @Test
  public void equalsOtherClass() throws Exception {
    assertNotEquals("Vector can not be equal to object", new Vector2f(0f, 0f),
        new Object());
  }

  @Test
  public void equalsDifferentVectors() throws Exception {
    assertNotEquals("Vectors can not be equal", new Vector2f(1f, 0f),
        new Vector2f(0f, 1f));
  }

  @Test
  public void equalsDifferentVectors2() throws Exception {
    assertNotEquals("Vectors can not be equal", new Vector2f(1f, 0f),
        new Vector2f(1f, 1f));
  }

  @Test
  public void equalsSameVectors() throws Exception {
    assertEquals("Vectors must be equal", new Vector2f(1f, 0f),
        new Vector2f(1f, 0f));
  }
}
