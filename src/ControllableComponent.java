/**
 * Handles Input into Entities. Acts as access point for InputSystem.
 */
public class ControllableComponent extends Component{
  /**
	 * Default CTor
	 * Requires it's ID in the Component System and a pointer to the associated entity.
  */
  public ControllableComponent(int i, Entity up){
    super(i, up);
  }
  
  /**
   * Rotate left by 90°.
   */
  public void left(){
    direction--;
    while (direction <1) {
      direction += 4;
    }
    updateVelocity();
  }
  
  /**
   * Sets an absolute direction.
   * 1: right
   * 2: down
   * 3: left
   * 4: up
   */
  public void setDirection(int dir){
    if (dir > 0 && dir < 5) {
      direction = dir;
      updateVelocity();
    } // end of if
  }
  
  /**
   * Rotate right by 90°.
   */
  public void right(){
    direction++;
    while (direction > 4) {
      direction -=4;
    }
    updateVelocity();
  }
  
  protected void updateVelocity(){
    if ((direction % 2) ==1) {
      upPtr.pComp.veloc[1] = 0;
      if (direction == 1) {
        upPtr.pComp.veloc[0] = 20;
      } else {
        upPtr.pComp.veloc[0] = -20;
      } // end of if-else
    } else {
      upPtr.pComp.veloc[0] = 0;
      if (direction == 2) {
        upPtr.pComp.veloc[1] = 20;
      } else {
        upPtr.pComp.veloc[1] = -20;
      } // end of if-else
    } // end of if-else
  }
  
  protected int direction = 1;
}
