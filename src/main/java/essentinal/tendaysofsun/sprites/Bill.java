package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;
import essentinal.tendaysofsun.interfaces.IncomeListener;

public class Bill implements IUpdateable, IRenderer, IncomeListener {
  private static final Color COLOR_TEXT = new Color(0.0f, 0.8f, 0.0f);
  private static final Color COLOR_TEXT_LESS = new Color(0.8f, 0.8f, 0.0f);
  private static final Color COLOR_TEXT_DANGER = new Color(1.0f, 0.0f, 0.0f);

  private int width = -1;
  private int height = -1;

  private GradientPaint paint;
  private static final Color COLOR_BG = new Color(0.2f, 0.2f, 0.2f);
  private static final Color COLOR_BG2 = new Color(0.1f, 0.1f, 0.1f);

  private int credits = 500;

  private int earnedCredits = 0;

  private static final Font FONT = new Font("arial", Font.BOLD, 22);

  public static final Bill INSTANCE = new Bill();

  private Bill() {

  }

  public static Bill getInstance() {
    return INSTANCE;
  }

  @Override
  public void render(Graphics2D g, int x, int y, int width, int height,
      float rotation) {

    if (this.width != width || this.height != height) {
      this.width = Math.max(width, 2);
      this.height = Math.max(height, 2);

      paint = new GradientPaint(new Point(0, height - 75), COLOR_BG2,
          new Point(0, height), COLOR_BG);

    }
    Paint p = g.getPaint();
    g.setPaint(paint);

    g.fillRect(40, height - 75, 180, 35);

    g.setColor(Color.BLACK);

    g.drawRect(40, height - 75, 180, 35);

    if (credits < 500) {
      g.setColor(COLOR_TEXT_DANGER);
    } else if (credits < 1000) {
      g.setColor(COLOR_TEXT_LESS);
    } else {
      g.setColor(COLOR_TEXT);
    }

    g.setFont(FONT);

    String s = String.valueOf(credits);

    Rectangle2D rect = g.getFontMetrics().getStringBounds(s, g);
    g.drawString(s, 200 - (int) rect.getWidth(), height - 50);

    g.setPaint(p);
  }

  @Override
  public void update(float time) {
    // bill_amount += time;
  }

  @Override
  public void creditsChanged(int amount) {
    credits += amount;
    if (amount > 0) {
      earnedCredits += amount;
    }
  }

  public int getCredits() {
    return credits;
  }

  public int getEarnedCredits() {
    return earnedCredits;
  }
}
