package gamelogic.physics;
/**
 * Container for the result of a collision.
 */
public class CollisionSolution{
  /**
  * Standart-Constructor for empty CollisionSolution
  */
  public CollisionSolution(){
    collides = false;
  }
  
  /**
   * Returns whether the shapes collide.
   */
  public boolean isColliding(){
    return collides;
  }
  
  /**
	 * Sets whether the shapes collide.
   */
  public void setCollides(boolean in){
    collides = in;
  }
  
  private boolean collides;
}
