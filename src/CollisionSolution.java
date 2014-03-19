/**
 * Container f�r alle Daten die bei der Erkennung einer Kollision erarbeitet werden.
 */
public class CollisionSolution{
  /**
  * Standart-Constructor f�r leere CollisionSolution
  */
  public CollisionSolution(){
    collides = false;
  }
  
  /**
   * Gibt zur�ck, ob die CollisionShapes kollidieren.
   */
  public boolean isColliding(){
    return collides;
  }
  
  /**
   * Setzt die Kollision auf den Parameterwert.
   */
  public void setCollides(boolean in){
    collides = in;
  }
  
  private boolean collides;
}