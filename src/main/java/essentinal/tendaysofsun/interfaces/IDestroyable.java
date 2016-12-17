package essentinal.tendaysofsun.interfaces;

/**
 * Interface for checking if a sprite can be destroyed.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public interface IDestroyable {

  /**
   * Checks if this can be destroyed.
   * 
   * @return <code>true</code> to destroy the sprite.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  boolean isReadyToDestroy();

}
