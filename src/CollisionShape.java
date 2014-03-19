/**
 * Grundlage für alle CollisionShapes.
 * Implementiert den Typ
 */
public abstract class CollisionShape{
  /**
   * Implementiert die Kollision der Shape 
   */
  public abstract CollisionSolution collideWith(CollisionShape otherOne, PositionDiff pos);
  
  /**
   * Gibt den Typ der Shape zurück. Muss für alle CollisionShapes einzigartig sein.
   */
  public abstract int getType();
}
