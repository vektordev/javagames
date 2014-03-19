/**
 * Dot-Shaped collision object.
 */
public class Dot extends CollisionShape{
  /**
   * Default-CTor
   */
  public Dot(){
  }
  
  /**
   * @inheritDoc
   */
  public int getType(){
    return 1;
  }
  
  /**
   * @inheritDoc
   */
  public CollisionSolution collideWith( CollisionShape otherOne, PositionDiff pos){
    if (otherOne.getType() > this.getType()) {
      return otherOne.collideWith(this, pos.invert());
    } // end of if
    CollisionSolution ret = new CollisionSolution();
    if (otherOne.getType() == 1) {
      ret.setCollides((pos.pos[0] == 0) && (pos.pos[1] == 0));
    } // end of if
    return ret;
  }
}
