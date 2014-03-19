/**
 * An den Achsen ausgerichtete Gerade, die die Spielwelt in einen nicht passierbaren Teil und einen passierbaren Teil teilt.
 */
public class AABorderShape extends CollisionShape{
  /**
  * Standart-Constructor.
  * Benötigt eine Achse.
  * 1: Schneidet alles links des Entitys ab
  * 1: Schneidet alles unterhalb des Entitys ab 
  * 1: Schneidet alles rechts des Entitys ab 
  * 1: Schneidet alles oberhalb des Entitys ab
  */
  public AABorderShape(int axis){
    this.axis = axis;
  }
  
  /**
  * @inheritDoc
  */
  public int getType(){
    //Java erlaubt bei Overrides keine static functions... aber egal -.-
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