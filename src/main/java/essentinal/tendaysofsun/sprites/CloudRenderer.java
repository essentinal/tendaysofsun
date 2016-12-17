package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

import essentinal.tendaysofsun.interfaces.IRenderer;

public class CloudRenderer implements IRenderer {
  private int width = -1;
  private int height = -1;

  private GradientPaint paint;

  public CloudRenderer() {

  }

  @Override
  public void render(Graphics2D g, int x, int y, int width, int height,
      float rotation) {
    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      paint = new GradientPaint(new Point(0, 0),
          new Color(0.8f, 0.8f, 1.0f, 1.0f), new Point(0, this.height),
          new Color(0.5f, 0.5f, 1.0f, 1.0f));

    }
    Paint p = g.getPaint();
    g.setPaint(paint);

    g.translate(x, y);
    g.fillOval(0, 0, 50, 50);
    g.fillOval(20, 20, 50, 50);
    g.fillOval(70, 0, 50, 50);
    g.fillOval(40, 0, 50, 50);
    g.fillOval(60, 20, 50, 50);
    // g.fillRect(0, 0, width, height);
    g.translate(-x, -y);

    g.setPaint(p);
  }
}
