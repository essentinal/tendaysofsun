package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

import essentinal.tendaysofsun.interfaces.IRenderer;

public class InverterRenderer implements IRenderer {
  private static final Color COLOR_BG = new Color(0.9f, 0.9f, 0.9f);
  private static final Color COLOR_BG2 = new Color(0.6f, 0.6f, 0.6f);

  private int width = -1;
  private int height = -1;

  private GradientPaint paint;

  @Override
  public void render(final Graphics2D g, final int x, final int y,
      final int width, final int height, final float rotation) {
    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      paint = new GradientPaint(new Point(0, 0), COLOR_BG,
          new Point(0, this.height / 2), COLOR_BG2);

    }
    final Paint p = g.getPaint();

    final int size = Math.min(width, height);
    final int diff = Math.max(width, height) - size;

    g.translate(x, y);
    g.setPaint(paint);
    // the background of the inverter
    g.fillRoundRect(diff / 2, 0, size, size, 5, 5);
    g.setColor(Color.BLACK);

    // the diagonal line
    g.drawLine((diff / 2) + size - 2, 2, diff / 2 + 2, size - 2);

    // the dc symbol
    g.drawLine(10, 10, 20, 10);

    // the ac symbol
    g.drawPolyline(new int[] { width - 20, width - 18, width - 12, width - 10 },
        new int[] { size - 10, size - 12, size - 8, size - 10 }, 4);

    // the outline of the inverter
    g.drawRoundRect(diff / 2, 0, size, size, 5, 5);
    g.translate(-x, -y);

    g.setPaint(p);
  }
}
