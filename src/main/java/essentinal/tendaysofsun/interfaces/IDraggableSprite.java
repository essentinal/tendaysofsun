package essentinal.tendaysofsun.interfaces;

/**
 * Interface for a sprite that can be dragged.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public interface IDraggableSprite extends ISprite {

  /**
   * Determines if the sprite contains the point at x, y.
   * 
   * @param x
   *          The x coordinate to check.
   * @param y
   *          The y coordinate to check.
   * @return <code>true</code> if the sprite contains (x,y).
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  boolean contains(int x, int y);

  /**
   * Notifies that the sprite was dragged.
   * 
   * @param x
   *          The x delta.
   * @param y
   *          The y delta.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  void dragged(int x, int y);
}
