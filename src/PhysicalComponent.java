public class PhysicalComponent extends Component{
  /**
  * Standart-Constructor
  * Benötigt den Index unter dem es im ComponentSystem zu finden ist und einen Zeiger auf den zugeordneten Entity sowie Koordinaten, Form und Typ des Objekts.
  * Type = 0 -> Geist
  * Type = 1 -> Schlangenkopf
  * Type = 2 -> Schlangenrumpf
  * Type = 3 -> Wand
  * Type = 4 -> Block
  * Type = 5 -> PickUp
  * Nur von ComponentSystem aus aufrufen!
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