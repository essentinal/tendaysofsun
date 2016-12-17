package essentinal.tendaysofsun;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameProperties {

  private static final Logger LOGGER = Logger
      .getLogger(GameProperties.class.getName());

  private Properties p;

  private GameProperties() {
    try {
      p = new Properties();
      p.load(GameProperties.class.getClassLoader()
          .getResourceAsStream("essentinal/tendaysofsun/game.properties"));
    } catch (final IOException e) {
      LOGGER.log(Level.WARNING, "Exception", e);
    }
  }

  public static GameProperties getInstance() {
    return Holder.INSTANCE;
  }

  public int getInt(final String key) {
    return Integer.valueOf(p.getProperty(key));
  }

  public float getFloat(final String key) {
    return Float.valueOf(p.getProperty(key));
  }

  /**
   * This is a holder class for the single instance. It allows a threadsafe lazy
   * initialization of the singleton.
   * 
   * @since 17.12.2016
   * @author Stephan Dreyer
   * 
   * @see <a href=
   *      "http://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom">Wikipedia</a>
   */
  private static class Holder {
    public static final GameProperties INSTANCE = new GameProperties();
  }
}
