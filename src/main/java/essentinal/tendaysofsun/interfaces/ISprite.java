package essentinal.tendaysofsun.interfaces;

import essentinal.tendaysofsun.math.Vector2f;

/**
 * Interface for a sprite that has a position.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public interface ISprite {

  /**
   * Getter for the position.
   * 
   * @return The position vector.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  Vector2f getPosition();
}
