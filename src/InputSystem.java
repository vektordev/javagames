import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.WindowConstants;

/**
 * Key listener based input handling 
 */
public class InputSystem implements KeyListener{
  /**
   * Default CTor
   */
  public InputSystem(){;
    controlled1 = null;
    controlled2 = null;
  }
  
  /**
  * executed buffered input and empties the buffer.
  */
  public void applyInput(){
    if (c1NextCommand != 0 && controlled1 != null)
    controlled1.setDirection(c1NextCommand);
    if (c2NextCommand != 0 && controlled2 != null)
    controlled2.setDirection(c2NextCommand);
    
    /*if (c1NextCommand == 1) {
    controlled1.left();
    } else {
    if (c1NextCommand == 2) {
    controlled1.right();
    } // end of if
    }
    if (c2NextCommand == 1) {
    controlled2.left();
    } else {
    if (c2NextCommand == 2) {
    controlled2.right();
    } // end of if
    }//*/
    c1NextCommand = 0;
    c2NextCommand = 0;
  }  
  
  /**
  * Handles key events and writes them to a buffer.
  */
  public void keyPressed(KeyEvent e){
    int code = e.getKeyCode();
    switch (code) {
      case  KeyEvent.VK_A: 
      c1NextCommand = 3;//left
      break;
      case  KeyEvent.VK_D: 
      c1NextCommand = 1;//right
      break;
      case  KeyEvent.VK_W: 
      c1NextCommand = 4;//up
      break;
      case  KeyEvent.VK_S: 
      c1NextCommand = 2;//down
      break;
      case  KeyEvent.VK_LEFT: 
      c2NextCommand = 3;//left
      break;
      case  KeyEvent.VK_RIGHT: 
      c2NextCommand = 1;//right
      break;
      case  KeyEvent.VK_UP: 
      c2NextCommand = 4;//up
      break;
      case  KeyEvent.VK_DOWN: 
      c2NextCommand = 2;//down
      break;
      case KeyEvent.VK_ESCAPE:
      System.exit(WindowConstants.EXIT_ON_CLOSE);
      default: 
      
    }
  }
  
  /**
  * do nothing
  */
  public void keyReleased(KeyEvent e){
    //nil
  }
  
  /**
  * do nothing
  */
  public void keyTyped(KeyEvent e){
    //nil
  }
  int c1NextCommand;
  int c2NextCommand;
  ControllableComponent controlled1;
  ControllableComponent controlled2;
}
