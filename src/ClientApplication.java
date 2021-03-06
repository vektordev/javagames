import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 * Creates an application from a Renderer, a GameState and a Timer.
 */
public class ClientApplication implements ActionListener{
  /**
  * Default-Ctor
  */
  public ClientApplication(){;
    //settings = new Settings(false, 5, 50);
    settings = new Settings();
    int xRes = 800;
    int yRes = 640;
    state = new GameState(xRes, yRes, settings);
    rnd = new Renderer(state, xRes, yRes);
    timer = new Timer(settings.getDeltaTimeMillis(), this);
    in = new InputSystem();
    rnd.renderWnd.addKeyListener(in);
    in.controlled1 = state.entities[0].cComp;
    if(settings.isMP())
    in.controlled2 = state.entities[1].cComp;
    run();
  }
  
  private void setUp(){
    rnd.wnd.dispose();
    rnd = null;
    settings.getNextSettings();
    state = new GameState(600, 400, settings);
    rnd = new Renderer(state, 600, 400);
    timer = new Timer(settings.getDeltaTimeMillis(), this);
    in = new InputSystem();
    rnd.renderWnd.addKeyListener(in);
    rnd.gState = state;
    rnd.renderWnd.cSys = state.cSys;
    in.controlled1 = state.entities[0].cComp;
    if(settings.isMP())
    in.controlled2 = state.entities[1].cComp;
    run();
  }
  
  @Override
  public void actionPerformed(ActionEvent e){
    //System.out.println("Timer!");
    if (state.isRunning) {
      in.applyInput();
      state.run();
      rnd.render();
    } else {
      timer.stop();
      setUp();
    } // end of if-else
  }
  
  /**
  * starts the application
  */
  public void run(){
    timer.start();
  }
  
  protected GameState state;
  protected Renderer rnd;
  protected Timer timer;
  protected InputSystem in;
  protected Settings settings;
  
  public static void main(String[]args){
    ClientApplication cApp = new ClientApplication();
    cApp.run();
    
  }
}
