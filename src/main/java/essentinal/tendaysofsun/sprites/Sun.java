package essentinal.tendaysofsun.sprites;

import java.awt.Graphics2D;

import essentinal.tendaysofsun.GameProperties;
import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;
import essentinal.tendaysofsun.math.Vector2f;

public class Sun implements IUpdateable, IRenderer {
  private final SunRenderer sunRenderer = new SunRenderer();
  private final Vector2f pos;

  private final float sunSpeed = GameProperties.getInstance()
      .getFloat("sun_speed");

  private final Vector2f dirVec = new Vector2f(1f, 0f);

  public Sun(final int posX, final int posY) {
    this.pos = new Vector2f(posX, posY);
  }

  @Override
  public void update(final float time) {
    pos.addLocal(dirVec.scale(sunSpeed * time));
  }

  @Override
  public void render(final Graphics2D g, final int x, final int y,
      final int width, final int height, final float rotation) {
    final int radius = Math.min(width, height) / 5;
    sunRenderer.render(g, (int) pos.getX(), (int) pos.getY(), radius, radius,
        rotation);
  }

  public Vector2f getPosition() {
    return pos;
  }
}
