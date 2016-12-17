package essentinal.tendaysofsun.interfaces;

/**
 * Interface for controlling the level transition.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public interface ILevelChanger {

  /**
   * Starts the next level.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  void startNextLevel();

  /**
   * Cancels the current level.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  void levelLost();
}
