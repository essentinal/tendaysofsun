package essentinal.tendaysofsun;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UTGameProperties {

  @Test
  public void getFloat() throws Exception {
    assertEquals("", 0.006d, GameProperties.getInstance().getFloat("sun_speed"),
        0.0001d);
  }

  @Test
  public void getInt() throws Exception {
    assertEquals("", 5,
        GameProperties.getInstance().getInt("costs_per_second"));
  }

  @Test(expected = NumberFormatException.class)
  public void getIntWrongType() throws Exception {
    GameProperties.getInstance().getInt("sun_speed");
  }

}
