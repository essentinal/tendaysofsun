package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

import essentinal.tendaysofsun.interfaces.IRenderer;

public class SkyRenderer implements IRenderer {

  public static final Color COLOR_SKY1 = new Color(0.0f, 0.0f, 1.0f, 1.0f);
  public static final Color COLOR_SKY2 = new Color(0.7f, 0.7f, 1.0f, 1.0f);

  private int width = -1;
  private int height = -1;

  private GradientPaint paint;

  @Override
  public void render(final Graphics2D g, final int x, final int y,
      final int width, final int height, final float rotation) {
    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      paint = new GradientPaint(new Point(0, 0), COLOR_SKY1,
          new Point(0, this.height), COLOR_SKY2);

    }
    final Paint p = g.getPaint();
    g.setPaint(paint);

    g.translate(x, y);
    g.fillRect(0, 0, width, height);
    g.translate(-x, -y);

    g.setPaint(p);
  }
}
