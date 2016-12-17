package essentinal.tendaysofsun.interfaces;

public interface IClickable extends ISprite {
  public boolean contains(int x, int y);

  public void dragged(int x, int y);
}
