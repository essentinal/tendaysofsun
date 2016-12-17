package essentinal.tendaysofsun.interfaces;

/**
 * Interface for an object that can be updated / animated.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public interface IUpdateable {

  /**
   * Triggers an update.
   * 
   * @param time
   *          The milliseconds since the last update.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  void update(float time);

}
