package essentinal.tendaysofsun;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import essentinal.tendaysofsun.interfaces.IDraggableSprite;
import essentinal.tendaysofsun.interfaces.IDestroyable;
import essentinal.tendaysofsun.interfaces.IIntersectable;
import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.interfaces.IUpdateable;

@SuppressWarnings("serial")
public abstract class SCPanel extends JPanel {
  protected ArrayList<IUpdateable> updateables = new ArrayList<IUpdateable>();
  protected ArrayList<IRenderer> bgRenderers = new ArrayList<IRenderer>();
  protected ArrayList<IRenderer> objectRenderers = new ArrayList<IRenderer>();
  protected ArrayList<IRenderer> overlayRenderers = new ArrayList<IRenderer>();
  protected ArrayList<IRenderer> hudRenderers = new ArrayList<IRenderer>();
  protected ArrayList<IDraggableSprite> clickables = new ArrayList<IDraggableSprite>();
  protected ArrayList<IDestroyable> destroyables = new ArrayList<IDestroyable>();
  protected ArrayList<IIntersectable> intersectables = new ArrayList<IIntersectable>();

  public static final int PANEL_WIDTH = GameProperties.getInstance()
      .getInt("panel_width");
  public static final int PANEL_HEIGHT = GameProperties.getInstance()
      .getInt("panel_height");
  public static final int SUN_HEIGHT = GameProperties.getInstance()
      .getInt("sun_height");
  public static final int GROUND_HEIGHT = GameProperties.getInstance()
      .getInt("ground_height");
  public static final Color COLOR_STOPPED = new Color(.7f, .7f, .7f, .5f);

  protected Random random = new Random();

  private boolean stopped = true;

  protected final Timer rayTimer;
  protected final Timer updateTimer;
  protected Day day;

  public SCPanel(final Day day) {
    this.day = day;
    setDoubleBuffered(false);

    // setCursor(getToolkit().createCustomCursor(
    // new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0),
    // "null"));

    final SCMouseListener l = new SCMouseListener();

    addMouseListener(l);
    addMouseMotionListener(l);

    updateTimer = new Timer(30, new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        update();

        repaint();
      }
    });
    updateTimer.setRepeats(true);

    rayTimer = new Timer(300, new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        createRay();

        createCloud();

        checkDestroyables();

        checkBill();

        checkSunPos();
      }
    });
    rayTimer.setRepeats(true);
  }

  protected abstract void createRay();

  protected abstract void createCloud();

  protected void update() {
    for (final IUpdateable updateable : updateables) {
      updateable.update(updateTimer.getDelay());
    }
  }

  protected void checkDestroyables() {
    final Iterator<IDestroyable> it = destroyables.iterator();

    while (it.hasNext()) {
      final IDestroyable dest = it.next();
      if (dest.isReadyToDestroy()) {
        it.remove();

        updateables.remove(dest);
        bgRenderers.remove(dest);
        objectRenderers.remove(dest);
        overlayRenderers.remove(dest);
        hudRenderers.remove(dest);
        clickables.remove(dest);
      }
    }
  }

  protected abstract void checkSunPos();

  protected abstract void checkBill();

  private void render(final Graphics2D g) {

    // render all layers
    for (final IRenderer renderer : bgRenderers) {
      renderer.render(g, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
    }
    for (final IRenderer renderer : objectRenderers) {
      renderer.render(g, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
    }
    for (final IRenderer renderer : overlayRenderers) {
      renderer.render(g, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
    }
    for (final IRenderer renderer : hudRenderers) {
      renderer.render(g, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, 0);
    }

  }

  @Override
  protected void paintComponent(final Graphics graphics) {
    final Graphics2D g = (Graphics2D) graphics;
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

    render(g);

    if (stopped) {
      g.setColor(COLOR_STOPPED);
      g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
      g.setColor(Color.DARK_GRAY);
      g.setFont(getFont().deriveFont(Font.ITALIC, 30));
      final Rectangle2D rect = g.getFontMetrics().getStringBounds("paused", g);
      g.drawString("paused", (int) (PANEL_WIDTH - rect.getWidth()) / 2,
          (int) (PANEL_HEIGHT - rect.getHeight()) / 2);
    }

  }

  public void start() {
    if (stopped) {
      System.out.println("start/resume game");
      stopped = false;
      updateTimer.start();
      rayTimer.start();
    }
  }

  public void stop() {
    if (!stopped) {
      System.out.println("stop game");
      stopped = true;
      updateTimer.stop();
      rayTimer.stop();
      repaint();
    }
  }

  class SCMouseListener extends MouseAdapter {
    private IDraggableSprite clickable;

    private int offX;
    private int offY;

    @Override
    public void mouseReleased(final MouseEvent e) {
      clickable = null;
      setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void mousePressed(final MouseEvent e) {
      for (final IDraggableSprite c : clickables) {
        if (c.contains(e.getX(), e.getY())) {
          setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
          clickable = c;
          offX = (int) (e.getX() - c.getPosition().x);
          offY = (int) (e.getY() - c.getPosition().y);
          return;
        }
      }
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
      if (clickable != null) {
        clickable.dragged(e.getX() - offX, e.getY() - offY);
      }
    }
  }
}
