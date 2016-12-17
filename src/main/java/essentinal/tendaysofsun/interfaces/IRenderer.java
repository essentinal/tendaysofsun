package essentinal.tendaysofsun.interfaces;

import java.awt.Graphics2D;

public interface IRenderer {
  public void render(Graphics2D g, int x, int y, int width, int height,
      float rotation);

}
