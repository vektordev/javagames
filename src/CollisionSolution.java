/**
 * Container für alle Daten die bei der Erkennung einer Kollision erarbeitet werden.
 */
public class CollisionSolution{
  /**
  * Standart-Constructor für leere CollisionSolution
  */
  public CollisionSolution(){
    collides = false;
  }
  
  /**
   * Gibt zurück, ob die CollisionShapes kollidieren.
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