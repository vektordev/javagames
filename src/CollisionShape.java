/**
 * Abstract parent class for any Collision Shape.
 */
public abstract class CollisionShape{
  /**
   * Resolves a collision and generates a CollisionSolution in the process.
   */
  public abstract CollisionSolution collideWith(CollisionShape otherOne, PositionDiff pos);
  
  /**
   * Returns a unique type ID for any given subclass of CollisionShape
   */
  public abstract int getType();
}
