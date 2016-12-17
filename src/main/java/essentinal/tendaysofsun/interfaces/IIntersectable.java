package essentinal.tendaysofsun.interfaces;

import java.awt.Rectangle;

public interface IIntersectable {
  public boolean checkIntersection(IIntersectable intersectable);

  public Rectangle getRect();
}
