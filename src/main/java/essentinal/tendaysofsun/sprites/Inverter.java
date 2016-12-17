package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import essentinal.tendaysofsun.SCPanel;
import essentinal.tendaysofsun.Upgrades;
import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;

public class Inverter implements IRenderer, IUpdateable {
  private static final IRenderer renderer = new InverterRenderer();

  public static final int WIDTH = 64;
  public static final int HEIGHT = 50;

  private float time = 0;

  private boolean blink = false;

  @Override
  public void render(Graphics2D g, int x, int y, int width, int height,
      float rotation) {

    // inverter input lines
    for (int i = 0; i < Upgrades.getInstance().getInverterPower().level; i++) {
      int j = 12 - Upgrades.getInstance().getInverterPower().level + i;

      g.fillRect(width - WIDTH * 3 / 2, SCPanel.GROUND_HEIGHT + (j + 1) * 4, 10,
          2);
    }

    // horizontal line
    g.fillRect(x + 25, SCPanel.GROUND_HEIGHT - 4, width - 50, 2);

    // lower line (bus)
    g.fillRect(width - WIDTH * 3 / 2, SCPanel.GROUND_HEIGHT - 4, 2, WIDTH - 12);

    // horizontal line (inverter output)
    g.fillRect(width - WIDTH * 2 / 3, SCPanel.GROUND_HEIGHT + HEIGHT / 2, 50,
        2);

    // inverter
    renderer.render(g, width - WIDTH * 3 / 2, SCPanel.GROUND_HEIGHT + 2, WIDTH,
        HEIGHT, 0f);

    if (blink) {
      g.setColor(Color.YELLOW);
    }

    g.fillRect(width - WIDTH / 3 * 2, SCPanel.GROUND_HEIGHT + HEIGHT / 2, 3, 2);

  }

  @Override
  public void update(float time) {
    this.time += time;

    if (this.time > 500) {
      blink = !blink;
      this.time = 0;
    }
  }
}
