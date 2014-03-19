/**
 * Positionsunterschied zwischen zwei CollisionShapes
 */
public class PositionDiff{
  /**
   * Standart-Constructor
   */
  public PositionDiff(int x, int y){
    pos = new int[2];
    pos[0] = x;
    pos[1] = y;
  }
  
  /**
   * Gibt ein invertiertes PositionDiff zurück.
   */
  public PositionDiff invert(){
    return new PositionDiff(-pos[0], -pos[1]);
  }
  
  /**
   * Gibt die Position als int[2] zurück.
   */
  public int[] getPos(){
    int[] ret = new int[2];
    ret[0] = pos[0];
    ret[1] = pos[1];
    return ret;
  }
  
  protected int[] pos;
}