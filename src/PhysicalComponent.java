/**
* Implements Physical interaction of objects.
*/
public class PhysicalComponent extends Component{
  /**
	 * Default CTor
	 * Requires it's ID in the Component System and a pointer to the associated
   * entity as well as position coordinates, shape and type of the object.
   * Type = 0 -> ghost
   * Type = 1 -> Snake head
   * Type = 2 -> Snake body
   * Type = 3 -> wall
   * Type = 4 -> block
   * Type = 5 -> collectible
   */
  public PhysicalComponent(int id, Entity up, int x, int y, CollisionShape cShape, int type){
    super(id, up);
    xPos = x;
    yPos = y;
    veloc = new int[2];
    veloc[0] = 0;
    veloc[1] = 0;
    shape = cShape;
    this.type = type;
  }
  
  public int getXPos(){
    return xPos;
  }
  
  public int getYPos(){
    return yPos;
  }
  
  protected int xPos;
  protected int yPos;
  
  protected CollisionShape shape;
  
  protected int[] veloc;
  protected int type;
  //1 = snakeHead
  //2 = snakeTail
  //3 = Wall
  //4 = block
  //5 = pickup
}
