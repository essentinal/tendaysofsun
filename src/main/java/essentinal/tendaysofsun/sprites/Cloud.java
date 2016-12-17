package essentinal.tendaysofsun.sprites;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import essentinal.tendaysofsun.Level;
import essentinal.tendaysofsun.SCPanel;
import essentinal.tendaysofsun.interfaces.IDestroyable;
import essentinal.tendaysofsun.interfaces.IDraggableSprite;
import essentinal.tendaysofsun.interfaces.IIntersectable;
import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;
import essentinal.tendaysofsun.math.Vector2f;

public class Cloud implements IRenderer, IDraggableSprite, IUpdateable,
    IDestroyable, IIntersectable {
  private final Vector2f pos;
  private final CloudRenderer renderer = new CloudRenderer();

  public static final int WIDTH = 120;
  public static final int HEIGHT = 70;

  private boolean destroy = false;

  private final Vector2f dirVec;

  private final Rectangle rect = new Rectangle();

  public Cloud(final int posX, final int posY, final float yDir) {
    this.pos = new Vector2f(posX, posY);
    dirVec = new Vector2f(yDir, 0f);

    updateRect();
  }

  public Cloud() {
    this((SCPanel.PANEL_WIDTH + WIDTH) / 2, SCPanel.GROUND_HEIGHT - HEIGHT,
        0.15f);
  }

  @Override
  public void update(final float time) {
    if (pos.getX() <= SCPanel.PANEL_WIDTH + WIDTH && pos.getX() >= -WIDTH) {
      pos.add(dirVec.scale(Level.currentDay.cloudSpeed * time));

      updateRect();
    } else {
      destroy = true;
    }
  }

  @Override
  public void render(final Graphics2D g, final int x, final int y, final int w,
      final int h, final float rotation) {
    renderer.render(g, (int) pos.getX(), (int) pos.getY(), WIDTH, HEIGHT,
        rotation);
  }

  @Override
  public boolean contains(final int x, final int y) {
    return new Rectangle((int) pos.getX(), (int) pos.getY(), WIDTH, HEIGHT)
        .contains(x, y);
  }

  @Override
  public void dragged(final int x, final int y) {
    final float newX = Math.max(Math.min(x, SCPanel.PANEL_WIDTH), -WIDTH);
    dirVec.setX((newX - pos.getX()) / 5f);
    pos.setX(newX);

    updateRect();
  }

  @Override
  public boolean isReadyToDestroy() {
    return destroy;
  }

  @Override
  public Vector2f getPosition() {
    return pos;
  }

  private void updateRect() {
    rect.setBounds((int) pos.getX(), (int) pos.getY(), WIDTH, HEIGHT);
  }

  @Override
  public Rectangle getRect() {
    return rect;
  }

  @Override
  public boolean isIntersection(final IIntersectable intersectable) {
    final Rectangle r1 = getRect();
    final Rectangle r2 = intersectable.getRect();

    if (
    /* r1.y > r2.y && */
    r1.intersects(r2)) {

      return true;
    }

    return false;
  }
}
