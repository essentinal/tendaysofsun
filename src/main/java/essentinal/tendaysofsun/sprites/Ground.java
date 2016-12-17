package essentinal.tendaysofsun.sprites;

import java.awt.Graphics2D;

import essentinal.tendaysofsun.GameProperties;
import essentinal.tendaysofsun.interfaces.IRenderer;

public class Ground implements IRenderer {

  private final GroundRenderer groundRenderer = new GroundRenderer();
  private final int skyHeight;

  public Ground() {
    skyHeight = GameProperties.getInstance().getInt("ground_height");
  }

  @Override
  public void render(final Graphics2D g, final int x, final int y,
      final int width, final int height, final float rotation) {

    groundRenderer.render(g, 0, skyHeight, width, height - skyHeight, rotation);
  }
}
