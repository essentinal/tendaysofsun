package essentinal.tendaysofsun;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Main class for starting the game.
 *
 * @since 17.12.2016
 * @author Stephan Dreyer
 */
public final class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  /**
   * Private constructor to prevent instantiation.
   *
   * @since 17.12.2016
   * @author Stephan Dreyer
   */
  private Main() {
  }

  public static void main(final String[] args) {
    // force default controls to be english
    Locale.setDefault(Locale.ENGLISH);

    SwingUtilities.invokeLater(() -> {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException
          | IllegalAccessException | UnsupportedLookAndFeelException e) {
        LOGGER.log(Level.WARNING, "Exception", e);
      }

      new SCFrame();
    });
  }
}
