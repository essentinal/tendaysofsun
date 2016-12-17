package essentinal.tendaysofsun.interfaces;

import java.awt.Rectangle;

/**
 * Interface for checking if sprites intersect each other.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public interface IIntersectable {

  /**
   * Checks if this intersectable intersects the other one.
   * 
   * @param intersectable
   *          The other intersectable.
   * @return <code>true</code> if an intersection has been detected.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  boolean isIntersection(IIntersectable intersectable);

  /**
   * Getter for the rectangle.
   * 
   * @return The rect.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  Rectangle getRect();
}
