package essentinal.tendaysofsun.sprites;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import essentinal.tendaysofsun.Level;
import essentinal.tendaysofsun.SCPanel;
import essentinal.tendaysofsun.interfaces.IDestroyable;
import essentinal.tendaysofsun.interfaces.IIntersectable;
import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;
import essentinal.tendaysofsun.math.Vector2f;

public class Ray
    implements IUpdateable, IRenderer, IDestroyable, IIntersectable {
  private final RayRenderer renderer = new RayRenderer();
  private final Vector2f pos;

  private final Vector2f dirVec;

  private boolean destroy = false;
  private final static int HEIGHT = 20;
  private final static int WIDTH = 8;

  private final Rectangle rect = new Rectangle();

  private final ArrayList<IIntersectable> intersectables;

  public Ray(final float xPos, final float yPos, final Vector2f direction,
      final ArrayList<IIntersectable> intersectables) {
    this.pos = new Vector2f(xPos, yPos);
    this.dirVec = direction;
    this.intersectables = intersectables;

    updateRect();
  }

  @Override
  public void update(final float time) {
    if (pos.y + HEIGHT < SCPanel.GROUND_HEIGHT && !destroy) {
      pos.add(dirVec.scale(Level.currentDay.raySpeed * time));
      updateRect();

      for (final IIntersectable ii : intersectables) {
        if (ii.isIntersection(this)) {
          destroy = true;
          return;
        }
      }

    } else {
      destroy = true;
    }
  }

  private void updateRect() {
    rect.setBounds((int) pos.x - WIDTH, (int) pos.y, WIDTH, HEIGHT / 2);
  }

  @Override
  public void render(Graphics2D g, final int x, final int y, final int width,
      final int height, final float rotation) {
    final float angle = dirVec.getAngle();

    g = (Graphics2D) g.create(x, y, width, height);
    renderer.render(g, (int) pos.x, (int) pos.y, WIDTH, HEIGHT, angle);
  }

  @Override
  public boolean isReadyToDestroy() {
    return destroy;
  }

  @Override
  public Rectangle getRect() {
    return rect;
  }

  @Override
  public boolean isIntersection(final IIntersectable intersectable) {
    return intersectable.isIntersection(this);
  }
}
