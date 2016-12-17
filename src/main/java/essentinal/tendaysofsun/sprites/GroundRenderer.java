package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

import essentinal.tendaysofsun.interfaces.IRenderer;

public class GroundRenderer implements IRenderer {
  private int width = -1;
  private int height = -1;

  private GradientPaint paint;

  public GroundRenderer() {

  }

  @Override
  public void render(Graphics2D g, int x, int y, int width, int height,
      float rotation) {
    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      paint = new GradientPaint(new Point(0, 0),
          new Color(1.0f, 0.9f, 0.5f, 1.0f), new Point(0, this.height),
          new Color(0.7f, 0.7f, 0.45f, 1.0f));

    }
    Paint p = g.getPaint();
    g.setPaint(paint);

    g.translate(x, y);
    g.fillRect(0, 0, width, height);
    g.translate(-x, -y);

    g.setPaint(p);
  }
}
