package essentinal.tendaysofsun.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

import essentinal.tendaysofsun.GameProperties;
import essentinal.tendaysofsun.SCPanel;
import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;

public class Darkness implements IUpdateable, IRenderer {
  private float lightness = 0;

  private float sunSpeed = GameProperties.getInstance().getFloat("sun_speed");

  @Override
  public void update(float time) {
    lightness = (lightness + (sunSpeed * time)) % (SCPanel.PANEL_WIDTH + 100);
  }

  @Override
  public void render(Graphics2D g, int x, int y, int width, int height,
      float rotation) {
    float lightnessRel = Math
        .abs(((lightness / SCPanel.PANEL_WIDTH) - 0.5f) * 1.4f);

    g.setColor(new Color(0, 0, 0, Math.max(lightnessRel, 0f)));
    g.fillRect(0, 0, width, height);
  }
}
