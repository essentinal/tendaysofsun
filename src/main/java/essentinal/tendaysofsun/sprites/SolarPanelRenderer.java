package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

import essentinal.tendaysofsun.interfaces.IRenderer;

public class SolarPanelRenderer implements IRenderer {
  private static final Color COLOR_STAND = new Color(0.9f, 0.9f, 0.9f);
  private static final Color COLOR_STAND2 = new Color(0.7f, 0.7f, 0.7f);

  private int width = -1;
  private int height = -1;

  private GradientPaint paint;
  private GradientPaint standPaint;

  @Override
  public void render(final Graphics2D g, final int x, final int y,
      final int width, final int height, final float rotation) {
    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      paint = new GradientPaint(new Point(0, 0), new Color(0.4f, 0.4f, 1.0f),
          new Point(0, SolarPanel.CELL_HEIGHT * 3 / 4),
          new Color(0.0f, 0.0f, 1.0f));

      standPaint = new GradientPaint(new Point(0, 0), COLOR_STAND,
          new Point(0, SolarPanel.HEIGHT * 3 / 4), COLOR_STAND2);
    }

    final Paint p = g.getPaint();
    g.setPaint(paint);

    g.translate(x, y);
    g.fillRect(1, 1, width - 1, SolarPanel.CELL_HEIGHT - 1);
    g.setPaint(standPaint);
    g.drawRect(1, 1, width - 2, SolarPanel.CELL_HEIGHT - 1);
    g.fillRect(1, SolarPanel.CELL_HEIGHT + 1, width - 1, 3);

    g.setColor(Color.DARK_GRAY);
    g.drawRect(0, SolarPanel.CELL_HEIGHT, width, 4);
    g.drawRect(height / 2 - 5, height - 8, width - height / 5 * 4, 8);
    g.drawRect(0, 0, width, 4 + SolarPanel.CELL_HEIGHT);
    g.drawRect(width / 2 - 3, height / 2 - 5, 6, height / 2 + 5);

    g.setPaint(standPaint);
    g.fillRect(width / 2 - 2, height / 2 - 4, 5, height / 2 + 4);
    g.fillRect(height / 2 - 4, height - 7, width - height / 5 * 4 - 1, 7);

    g.translate(-x, -y);

    g.setPaint(p);
  }
}
