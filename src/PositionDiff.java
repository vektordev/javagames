/**
 * Relative position of two CollisionShapes
 */
public class PositionDiff{
  /**
   * Default CTor
   */
  public PositionDiff(int x, int y){
    pos = new int[2];
    pos[0] = x;
    pos[1] = y;
  }
  
  /**
   * Returns the inverse PositionDiff
   */
  public PositionDiff invert(){
    return new PositionDiff(-pos[0], -pos[1]);
  }
  
  /**
   * Returns the position difference as int[].
   */
  public int[] getPos(){
    int[] ret = new int[2];
    ret[0] = pos[0];
    ret[1] = pos[1];
    return ret;
  }
  
  protected int[] pos;
}
