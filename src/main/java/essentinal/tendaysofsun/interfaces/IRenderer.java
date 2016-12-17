package essentinal.tendaysofsun.interfaces;

import java.awt.Graphics2D;

/**
 * Interface for a renderer. Implementations provide code that creates a
 * graphical representation of the game.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public interface IRenderer {
  /**
   * Renders the component.
   * 
   * @param g
   *          The graphics context.
   * @param x
   *          The x coordinate.
   * @param y
   *          The y coordinate.
   * @param width
   *          The available with.
   * @param height
   *          The available height.
   * @param rotation
   *          The desired rotation.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  void render(Graphics2D g, int x, int y, int width, int height,
      float rotation);

}
