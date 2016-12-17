package essentinal.tendaysofsun;

import essentinal.tendaysofsun.interfaces.ILevelChanger;
import essentinal.tendaysofsun.math.Vector2f;
import essentinal.tendaysofsun.sprites.Bill;
import essentinal.tendaysofsun.sprites.Cloud;
import essentinal.tendaysofsun.sprites.Darkness;
import essentinal.tendaysofsun.sprites.Ground;
import essentinal.tendaysofsun.sprites.Inverter;
import essentinal.tendaysofsun.sprites.Ray;
import essentinal.tendaysofsun.sprites.Sky;
import essentinal.tendaysofsun.sprites.SolarPanel;
import essentinal.tendaysofsun.sprites.Sun;

@SuppressWarnings("serial")
public class Level extends SCPanel {
  private final Sun sun;
  private final Sky sky;
  private final Ground ground;
  private final Inverter inverter;
  private final Darkness darkness;
  private final SolarPanel panel;

  private final Bill bill;

  public static Day currentDay;

  private final ILevelChanger levelChanger;

  public Level(final Day day, final ILevelChanger levelChanger) {
    super(day);
    currentDay = day;
    this.levelChanger = levelChanger;

    sky = new Sky();
    bgRenderers.add(sky);

    ground = new Ground();
    bgRenderers.add(ground);

    sun = new Sun(0, SUN_HEIGHT);
    objectRenderers.add(sun);
    updateables.add(sun);

    darkness = new Darkness();
    overlayRenderers.add(darkness);
    updateables.add(darkness);

    bill = Bill.getInstance();
    hudRenderers.add(bill);
    updateables.add(bill);

    panel = new SolarPanel();
    panel.setIncomeListener(bill);
    objectRenderers.add(0, panel);
    clickables.add(panel);
    updateables.add(panel);
    intersectables.add(panel);

    inverter = new Inverter();
    objectRenderers.add(0, inverter);
    updateables.add(inverter);
  }

  @Override
  protected void checkBill() {
    if (bill.getCredits() <= 0) {
      levelChanger.levelLost();
    }
  }

  @Override
  protected void createRay() {
    if (random.nextBoolean()) {

      final Ray ray = new Ray(sun.getPosition().getX(),
          sun.getPosition().getY(),
          new Vector2f((random.nextFloat() - .5f) * day.raySpread,
              Math.abs(random.nextFloat() - .1f * day.raySpread)).normalize(),
          intersectables);
      objectRenderers.add(1, ray);
      updateables.add(ray);
      destroyables.add(ray);
    }
  }

  @Override
  protected void createCloud() {
    if (random.nextFloat() < day.pCloud) {
      final float f = (random.nextFloat() - 0.5f) / 5f;
      final int xPos = f > 0f ? -Cloud.WIDTH : PANEL_WIDTH + Cloud.WIDTH / 2;

      final Cloud cloud = new Cloud(xPos, 150 + random.nextInt(200), f);
      objectRenderers.add(cloud);
      clickables.add(cloud);
      updateables.add(cloud);
      destroyables.add(cloud);
      intersectables.add(cloud);
    }
  }

  @Override
  protected void checkSunPos() {
    if (sun.getPosition().getX() > SCPanel.PANEL_WIDTH + 100) {
      levelChanger.startNextLevel();
    }
  }
}
