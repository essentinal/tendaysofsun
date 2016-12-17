package essentinal.tendaysofsun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import essentinal.tendaysofsun.interfaces.IRenderer;
import essentinal.tendaysofsun.sprites.Bill;
import essentinal.tendaysofsun.sprites.InverterRenderer;
import essentinal.tendaysofsun.sprites.SolarPanel;
import essentinal.tendaysofsun.sprites.SolarPanelRenderer;

@SuppressWarnings("serial")
public class UpgradePanel extends JPanel {
  private final Bill bill;
  private final Upgrades upgrades;

  private static final Color COLOR_PLUS = new Color(0.8f, 0.0f, 0.0f);

  private JLabel creditsLabel;
  private JLabel cellCountLabel;
  private JLabel inverterLevelLabel;
  private UpgradeCellCountButton cellCountButton;
  private UpgradeInverterButton upgradeInverterButton;

  public UpgradePanel() {
    bill = Bill.getInstance();
    upgrades = Upgrades.getInstance();

    init();
  }

  private void init() {
    setLayout(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.insets = new Insets(10, 10, 10, 10);
    constraints.fill = GridBagConstraints.NONE;
    constraints.weightx = 1d;
    constraints.weighty = 1d;

    JLabel label = new JLabel("Credits");
    add(label, constraints);

    constraints.gridx++;

    creditsLabel = new JLabel();
    add(creditsLabel, constraints);

    constraints.gridx = 0;
    constraints.gridy++;
    constraints.gridwidth = 2;

    final JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createTitledBorder("Upgrades"));
    panel.setLayout(new GridBagLayout());

    add(panel, constraints);

    constraints = new GridBagConstraints();

    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.insets = new Insets(10, 10, 10, 10);
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.weightx = 1d;
    constraints.weighty = 1d;

    label = new JLabel("Solar Panel Count");
    panel.add(label, constraints);

    constraints.gridx++;

    cellCountLabel = new JLabel();
    cellCountLabel.setSize(new Dimension(150, 20));
    panel.add(cellCountLabel, constraints);

    constraints.gridx++;
    constraints.weightx = 1d;
    constraints.weighty = 1d;

    cellCountButton = new UpgradeCellCountButton();
    panel.add(cellCountButton, constraints);

    constraints.weightx = 0d;
    constraints.weighty = 0d;
    constraints.gridx = 0;
    constraints.gridy++;

    label = new JLabel("Inverter Level");
    panel.add(label, constraints);

    constraints.gridx++;

    inverterLevelLabel = new JLabel();
    panel.add(inverterLevelLabel, constraints);

    constraints.gridx++;

    constraints.weightx = 1d;
    constraints.weighty = 1d;

    upgradeInverterButton = new UpgradeInverterButton();
    panel.add(upgradeInverterButton, constraints);

    constraints.gridx = 0;
    constraints.gridy++;
    constraints.gridwidth = 2;

    panel.add(Box.createHorizontalStrut(200), constraints);

    updateView();
  }

  private void updateView() {
    final int credits = bill.getCredits();
    creditsLabel.setText(String.valueOf(credits));

    cellCountButton.setEnabled(upgrades.getCellCount().isUpgradeable()
        && upgrades.getCellCount().nextLevel().price < credits);

    upgradeInverterButton.setEnabled(upgrades.getInverterPower().isUpgradeable()
        && upgrades.getInverterPower().nextLevel().price < credits);

    cellCountLabel.setText(String.format("%2d", upgrades.getCellCount().level));

    inverterLevelLabel
        .setText(String.format("%2d", upgrades.getInverterPower().level));

    repaint();
  }

  class UpgradeCellCountButton extends JButton implements ActionListener {
    private final IRenderer renderer = new SolarPanelRenderer();

    public UpgradeCellCountButton() {
      final Dimension d = new Dimension(SolarPanel.WIDTH + 20,
          SolarPanel.HEIGHT + 20);
      setPreferredSize(d);
      setSize(d);
      setMinimumSize(d);
      addActionListener(this);
    }

    @Override
    public void paint(final Graphics g) {
      super.paint(g);

      renderer.render((Graphics2D) g, 10, 10, SolarPanel.WIDTH,
          SolarPanel.HEIGHT, 0f);

      g.setColor(COLOR_PLUS);
      g.fillRect(16, SolarPanel.HEIGHT - 20 + 10, 4, 16);
      g.fillRect(10, SolarPanel.HEIGHT - 20 + 16, 16, 4);

      String s;
      if (upgrades.getCellCount().isUpgradeable()) {
        s = String.valueOf(upgrades.getCellCount().nextLevel().price);
      } else {
        s = "MAX";
      }

      g.setColor(Color.BLACK);
      final Rectangle2D rect = g.getFontMetrics().getStringBounds(s, g);
      g.drawString(s, SolarPanel.WIDTH + 10 - (int) rect.getWidth(),
          SolarPanel.HEIGHT + (int) rect.getHeight() / 3 * 2);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      bill.creditsChanged(-upgrades.upgradeCellCount());

      updateView();
    }
  }

  class UpgradeInverterButton extends JButton implements ActionListener {
    private final IRenderer renderer = new InverterRenderer();

    public UpgradeInverterButton() {
      final Dimension d = new Dimension(SolarPanel.WIDTH + 20,
          SolarPanel.HEIGHT + 20);
      setPreferredSize(d);
      setSize(d);
      setMinimumSize(d);
      addActionListener(this);
    }

    @Override
    public void paint(final Graphics g) {
      super.paint(g);

      renderer.render((Graphics2D) g, 10, 10, SolarPanel.WIDTH,
          SolarPanel.HEIGHT, 0f);

      g.setColor(COLOR_PLUS);
      g.fillRect(16, SolarPanel.HEIGHT - 20 + 10, 4, 16);
      g.fillRect(10, SolarPanel.HEIGHT - 20 + 16, 16, 4);

      String s;
      if (upgrades.getInverterPower().isUpgradeable()) {
        s = String.valueOf(upgrades.getInverterPower().nextLevel().price);
      } else {
        s = "MAX";
      }

      g.setColor(Color.BLACK);
      final Rectangle2D rect = g.getFontMetrics().getStringBounds(s, g);
      g.drawString(s, SolarPanel.WIDTH + 10 - (int) rect.getWidth(),
          SolarPanel.HEIGHT + (int) rect.getHeight() / 3 * 2);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
      bill.creditsChanged(-upgrades.upgradeInverterPower());

      updateView();
    }
  }
}
