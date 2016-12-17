package essentinal.tendaysofsun.sprites;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

import essentinal.tendaysofsun.interfaces.IRenderer;

public class RayRenderer implements IRenderer {
  private int width = -1;
  private int height = -1;

  private LinearGradientPaint paint;
  private Stroke stroke;

  public RayRenderer() {
  }

  @Override
  public void render(Graphics2D g, int x, int y, int width, int height,
      float angle) {
    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      // paint = new RadialGradientPaint(new Point(this.width / 2, 0), width,
      // new float[] { 0.0f, 0.3f, 0.5f }, new Color[] {
      // new Color(1.0f, 1.0f, 0.5f, 1.0f),
      // new Color(1.0f, 1.0f, 0.5f, 1.0f),
      // new Color(0.9f, 0.9f, 0.0f, 0.0f) });
      paint = new LinearGradientPaint(new Point(-this.width / 2, 0),
          new Point(this.width / 2, 0),
          new float[] { 0.0f, 0.4f, 0.5f, 0.6f, 1.0f },
          new Color[] { SunRenderer.COLOR_SUN3, SunRenderer.COLOR_SUN2,
              SunRenderer.COLOR_SUN1, SunRenderer.COLOR_SUN2,
              SunRenderer.COLOR_SUN3 });

      stroke = new BasicStroke(this.width, BasicStroke.CAP_ROUND,
          BasicStroke.JOIN_ROUND);
    }

    Paint oldPaint = g.getPaint();
    Stroke oldStroke = g.getStroke();
    g.setPaint(paint);

    int transX = Math.round(x - width / 2f);
    int transY = Math.round(y - height / 2f);

    g.translate(transX, transY);
    g.rotate(-angle);
    g.setStroke(stroke);
    g.drawLine(0, Math.round(-height / 2f), 0, height);
    g.rotate(angle);

    g.translate(-transX, -transY);

    g.setPaint(oldPaint);
    g.setStroke(oldStroke);
  }
}
