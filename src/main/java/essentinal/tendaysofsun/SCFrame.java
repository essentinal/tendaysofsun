package essentinal.tendaysofsun;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import essentinal.tendaysofsun.interfaces.ILevelChanger;
import essentinal.tendaysofsun.sprites.Bill;

@SuppressWarnings("serial")
public class SCFrame extends JFrame implements ILevelChanger {
  private Level level;

  private int days = 0;

  public SCFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final Dimension d = new Dimension(SCPanel.PANEL_WIDTH,
        SCPanel.PANEL_HEIGHT);
    setSize(d);
    setResizable(false);
    setLocationRelativeTo(null);

    setFocusable(true);
    addFocusListener(new FocusListener() {

      @Override
      public void focusLost(final FocusEvent e) {
        level.stop();
      }

      @Override
      public void focusGained(final FocusEvent e) {
        level.start();
      }
    });

    nextLevel();
  }

  @Override
  protected void processWindowEvent(final WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING
        && getDefaultCloseOperation() == EXIT_ON_CLOSE) {

      final int result = JOptionPane.showConfirmDialog(this,
          "Do you really want to exit the game?", "Exit",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

      if (result == JOptionPane.YES_OPTION) {

      } else {
        return; // Abort closing event
      }
    }
    super.processWindowEvent(e);
  }

  @Override
  public void nextLevel() {
    if (level != null) {
      level.stop();
      remove(level);
    }

    setVisible(false);

    if (days == 0) {
      JOptionPane.showMessageDialog(this,
          "Welcome to Ten Days of Sun!\n\nCatch the sun rays and earn credits. "
              + "You can move the solar panel by clicking and dragging it.\n"
              + "You can also drag clouds away. Doing nothing costs you credits. "
              + "On start, you have " + Bill.getInstance().getCredits()
              + " credits.\n"
              + "When you have no credits, the game will end.\n\nClick OK to begin.",
          "Ten Days of Sun", JOptionPane.PLAIN_MESSAGE);
    } else if (days > 0 && days < 10) {
      JOptionPane.showMessageDialog(this, new UpgradePanel(),
          "Day " + days + " is over", JOptionPane.PLAIN_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(this,
          "<html>Congratulations! You survived Ten Days of Sun. <br />"
              + "You earned <b>" + Bill.getInstance().getEarnedCredits()
              + "</b> Credits.<br /><br />Click OK to exit the Game.</html>",
          "Game Over", JOptionPane.PLAIN_MESSAGE);
      dispose();
      return;
    }

    days++;

    level = new Level(Day.getDay(days), this);
    setTitle("Ten Days of Sun - Day " + days);

    level.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_N,
            InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK),
        "nextLevel");
    level.getActionMap().put("nextLevel", new AbstractAction() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        nextLevel();
      }
    });

    level.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(
            KeyStroke.getKeyStroke(KeyEvent.VK_M,
                InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK),
            "money");
    level.getActionMap().put("money", new AbstractAction() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        Bill.getInstance().creditsChanged(1000);
      }
    });

    add(level);
    setVisible(true);

    level.start();
  }

  @Override
  public void levelLost() {
    JOptionPane.showMessageDialog(this,
        "<html>You lost Ten Days of Sun on day " + days + ".<br />"
            + "You earned <b>" + Bill.getInstance().getEarnedCredits()
            + "</b> Credits.<br /><br />Click OK to exit the Game.</html>",
        "Game Over", JOptionPane.PLAIN_MESSAGE);
    dispose();
    return;
  }
}
