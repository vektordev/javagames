/**
 * Axis-aligned line that splits the worls into a inaccesible and an accesible part.
 */
public class AABorderShape extends CollisionShape{
  /**
  * Default-Ctor.
  * Parameter: int as axis direction
  * 1: cuts off left of this entity's position
  * 2: cuts off below this entity's position
  * 3: cuts off right of this entity's position
  * 4: cuts off above this entity's position
  */
  public AABorderShape(int axis){
    this.axis = axis;
  }
  
  /**
  * @inheritDoc
  */
  public int getType(){
    //should be static for the most part, but whatever. This function is basically just type info.
    return 2;
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
      if (axis == 1 && pos.pos[0] >= 0) {
        ret.setCollides(true);
      } // end of if
      else if (axis == 2 && pos.pos[1] >= 0) {
        ret.setCollides(true);
      } // end of if
      else if (axis == 3 && pos.pos[0] <= 0) {
        ret.setCollides(true);
      } // end of if
      else if (axis == 4 && pos.pos[1] <= 0) {
        ret.setCollides(true);
      } // end of if
    } // end of if
    return ret;
  }
  
  protected int axis;
  //1 = +x
  //2 = +y
  //3 = -x
  //4 = -y
}
