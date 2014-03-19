/**
 * Grundlage f�r alle CollisionShapes.
 * Implementiert den Typ
 */
public abstract class CollisionShape{
  /**
   * Implementiert die Kollision der Shape 
   */
  public abstract CollisionSolution collideWith(CollisionShape otherOne, PositionDiff pos);
  
  /**
   * Gibt den Typ der Shape zur�ck. Muss f�r alle CollisionShapes einzigartig sein.
   */
  public abstract int getType();
}
