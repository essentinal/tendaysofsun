package essentinal.tendaysofsun;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import essentinal.tendaysofsun.interfaces.IRenderer;

@SuppressWarnings("serial")
public final class RendererComponent extends JComponent {
  private final IRenderer renderer;

  public RendererComponent(final IRenderer renderer) {
    this.renderer = renderer;
  }

  @Override
  protected void paintComponent(final Graphics g) {
    renderer.render((Graphics2D) g, 0, 0, getWidth(), getHeight(), 0f);
  }
}
