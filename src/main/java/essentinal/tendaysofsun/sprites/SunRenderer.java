package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RadialGradientPaint;

import essentinal.tendaysofsun.interfaces.IRenderer;

public class SunRenderer implements IRenderer {
  private int width = -1;
  private int height = -1;

  private RadialGradientPaint paint;

  public static final Color COLOR_SUN1 = new Color(1.0f, 1.0f, 0.5f, 1.0f);
  public static final Color COLOR_SUN2 = new Color(1.0f, 1.0f, 0.5f, 1.0f);
  public static final Color COLOR_SUN3 = new Color(0.9f, 0.9f, 0.0f, 0.0f);

  public SunRenderer() {

  }

  @Override
  public void render(Graphics2D g, int x, int y, int width, int height,
      float rotation) {
    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      paint = new RadialGradientPaint(
          new Point(this.width / 2, this.height / 2), this.width,
          new float[] { 0.0f, 0.3f, 0.5f },
          new Color[] { COLOR_SUN1, COLOR_SUN2, COLOR_SUN3 });
    }

    Paint p = g.getPaint();
    g.setPaint(paint);

    int transX = x - width / 2;
    int transY = y - height / 2;

    g.translate(transX, transY);
    g.fillOval(0, 0, width, height);
    g.translate(-transX, -transY);

    g.setPaint(p);
  }
}
