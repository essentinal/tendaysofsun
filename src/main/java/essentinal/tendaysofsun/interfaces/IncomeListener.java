package essentinal.tendaysofsun.interfaces;

/**
 * Listener for changes to the income.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
@FunctionalInterface
public interface IncomeListener {

  /**
   * Notified when the current credits have changed.
   * 
   * @param delta
   *          The delta, could be positive or negative.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  void creditsChanged(int delta);
}
