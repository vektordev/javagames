import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * Renderer organisiert das Drumherum um die tatsächlich sichtbare Spielwelt.
 */
public class Renderer{
  /**
   * Standartkonstruktor.
   * Benötigt den zu rendernden GameState sowie die Auflösung.
   */
  public Renderer(GameState s, int xRes, int yRes){
    //rComps = new RenderComponent[10];
    gState = s;
    wnd = new JFrame("Game Render");
    wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    wnd.setSize(xRes,yRes);
    renderWnd = new RenderWindow(gState.cSys);
    renderWnd.setVisible(true);
    //renderWnd.setBackground(new Color(0,120,0,255));
    wnd.add(renderWnd);
    wnd.setUndecorated(true);
    wnd.setVisible(true);
  }
  
  /**
   * Rendert die Szene.
   */
  public void render(){
    renderWnd.cSys = gState.cSys;
    renderWnd.update();
  }
  
  //public RenderComponent[] rComps;
  protected final RenderWindow renderWnd;
  protected JFrame wnd;
  protected GameState gState;
  
}