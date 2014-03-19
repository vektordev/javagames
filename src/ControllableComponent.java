/**
 * Kontroll-Schnittstelle zwischen InputSystem und Entity
 */
public class ControllableComponent extends Component{
  /**
  * Standart-Constructor
  * Benötigt den Index unter dem es im ComponentSystem zu finden ist und einen Zeiger auf den zugeordneten Entity.
  * Nur von ComponentSystem aus aufrufen!
  */
  public ControllableComponent(int i, Entity up){
    super(i, up);
  }
  
  /**
   * Dreht den Kopf um 90° nach links
   */
  public void left(){
    direction--;
    while (direction <1) {
      direction += 4;
    }
    updateVelocity();
  }
  
  /**
   * Setzt die Richtung. (absolut, nicht relativ)
   * 1: rechts
   * 2: unten
   * 3: links
   * 4: oben
   */
  public void setDirection(int dir){
    if (dir > 0 && dir < 5) {
      direction = dir;
      updateVelocity();
    } // end of if
  }
  
  /**
   * Dreht den Kopf um 90° nach rechts.
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