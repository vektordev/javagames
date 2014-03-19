import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * JPanel-Implementierung, die paintComponent alle RenderableComponents des gegebene ComponentSystems rendert.
 */
public class RenderWindow extends JPanel{
  /**
  * Standart-Constructor.
  * Ben�tigt das zu rendernde ComponentSystem.
  */
  public RenderWindow(ComponentSystem compos){
    //rSys = sys;
    cSys = compos;
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }
  
  @Override
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    //g.setColor(Color.BLACK);
    //g.fillRect(100, 100 , 20, 20);
    for (int i = 0; i< cSys.rComps.length; i++) {
      if (cSys.rComps[i] != null) {
        renderOneComponent(cSys.rComps[i], g);
      } // end of if
    } // end of for*/
    for (int i = 0; i< cSys.rComps.length; i++) {
      if (cSys.rComps[i] != null) {
        renderOneComponentForeGround(cSys.rComps[i], g);
      } // end of if
    } // end of for
    //lazybone implementation of Z-Ordering
    
  }
  
  
  private void renderOneComponent(RenderableComponent cmp, Graphics g){
    if (!cmp.renderMe || cmp.type == 1) {
      return;
    } // end of if
    int[] pos = cmp.upPtr.getPosition();
    g.setColor(cmp.farbe);
    g.fillRect(pos[0], pos[1] , cmp.size, cmp.size);
  }
  
  private void renderOneComponentForeGround(RenderableComponent cmp, Graphics g){
    if (!cmp.renderMe || cmp.type != 1) {
      return;
    } // end of if
    int[] pos = cmp.upPtr.getPosition();
    g.setColor(cmp.farbe);
    g.fillRect(pos[0], pos[1] , cmp.size, cmp.size);
  }
  
  /**
  * Aktualisiert die Grafik.
  */
  public void update(){
    repaint();
  }
  
  protected ComponentSystem cSys;
  //Renderer rSys;
}