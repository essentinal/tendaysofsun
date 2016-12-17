package essentinal.tendaysofsun.sprites;

import java.awt.Graphics2D;

import essentinal.tendaysofsun.GameProperties;
import essentinal.tendaysofsun.interfaces.IRenderer;

public class Sky implements IRenderer {
  private final SkyRenderer skyRenderer = new SkyRenderer();

  private final int skyHeight;

  public Sky() {
    skyHeight = GameProperties.getInstance().getInt("ground_height");
  }

  @Override
  public void render(final Graphics2D g, final int x, final int y,
      final int width, final int height, final float rotation) {

    skyRenderer.render(g, 0, 0, width, skyHeight, rotation);
  }
}
