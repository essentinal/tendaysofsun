package essentinal.tendaysofsun.sprites;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import essentinal.tendaysofsun.GameProperties;
import essentinal.tendaysofsun.SCPanel;
import essentinal.tendaysofsun.Upgrades;
import essentinal.tendaysofsun.interfaces.IDraggableSprite;
import essentinal.tendaysofsun.interfaces.IIntersectable;
import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;
import essentinal.tendaysofsun.interfaces.IncomeListener;
import essentinal.tendaysofsun.math.Vector2f;

public class SolarPanel
    implements IRenderer, IDraggableSprite, IIntersectable, IUpdateable {
  private final Vector2f pos;
  private final SolarPanelRenderer renderer = new SolarPanelRenderer();

  public static final int WIDTH = 64;
  public static final int HEIGHT = 50;

  public static final int CELL_HEIGHT = 16;

  private static final int income = GameProperties.getInstance()
      .getInt("ray_income");
  private static final int costs_per_second = GameProperties.getInstance()
      .getInt("costs_per_second");

  private IncomeListener listener;

  private float time = 0;

  private final Rectangle rect = new Rectangle();

  public SolarPanel(final int posX, final int posY) {
    this.pos = new Vector2f(posX, posY);

    updateRect();
  }

  public void setIncomeListener(final IncomeListener listener) {
    this.listener = listener;
  }

  public SolarPanel() {
    this((SCPanel.PANEL_WIDTH - getTotalWidth()) / 2,
        SCPanel.GROUND_HEIGHT - HEIGHT);
  }

  @Override
  public void render(final Graphics2D g, final int x, final int y, final int w,
      final int h, final float rotation) {
    for (int i = 0; i < Upgrades.getInstance().getCellCount().count; i++) {
      renderer.render(g, (int) pos.x + (i * WIDTH), (int) pos.y, WIDTH - 1,
          HEIGHT, rotation);
    }
  }

  @Override
  public void update(final float time) {
    this.time += time;

    if (this.time > 1000) {
      this.time = 0;
      listener.creditsChanged(
          -costs_per_second * Upgrades.getInstance().getTotalTechPoints());
    }
  }

  private static int getTotalWidth() {
    return WIDTH * Upgrades.getInstance().getCellCount().count;
  }

  private void updateRect() {
    rect.setBounds((int) pos.x, (int) pos.y, getTotalWidth(),
        SolarPanel.CELL_HEIGHT / 2);
  }

  @Override
  public boolean contains(final int x, final int y) {
    return new Rectangle((int) pos.x, (int) pos.y, getTotalWidth(), HEIGHT)
        .contains(x, y);
  }

  @Override
  public void dragged(final int x, final int y) {
    // this prevents the user from moving the panel too fast
    // int diff = (int) Math.min(Math.max(x - pos.x, -50), 50);
    // x = (int) (pos.x + diff);

    pos.x = Math.max(Math.min(x, SCPanel.PANEL_WIDTH - getTotalWidth()), 0);

    updateRect();
  }

  @Override
  public Vector2f getPosition() {
    return pos;
  }

  @Override
  public Rectangle getRect() {
    return rect;
  }

  private int getIncome() {
    return (int) (income * Upgrades.getInstance().getInverterPower().factor);
  }

  @Override
  public boolean isIntersection(final IIntersectable intersectable) {
    final Rectangle r1 = getRect();
    final Rectangle r2 = intersectable.getRect();

    if (
    /* r1.y > r2.y && */
    r1.intersects(r2)) {
      listener.creditsChanged(getIncome());

      return true;
    }

    return false;
  }
}
