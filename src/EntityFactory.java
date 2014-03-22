import java.awt.Color;

/**
 * Entity Factory. Order your Entities here.
 */
public class EntityFactory{
  /**
  * Default CTor.
	* Requires a ComponentSystem from which to get Components.
  */
  public EntityFactory(ComponentSystem s){
    cSys = s;
    cEnts = 0;
  }
  
  /**
  * Creates a grass tile of random color at a given position.
  */
  public Entity createGrassTile(int x, int y){
    Entity ret = new Entity(cEnts);
    cEnts ++;
    ret.rComp = cSys.getNewRendComp(ret, 2);
    ret.pComp = cSys.getNewPhysComp(ret, x, y, new Dot(), 0);
    int farber = (int)(Math.random() * 50);
    int farbeg = (int)(Math.random() * 50);
    int farbeb = (int)(Math.random() * 50);
    ret.rComp.farbe = new Color(100+farber, 200+farbeg, farbeb, 255);
    ret.rComp.renderMe = true;
    return ret;
  }
  
  /**
  * Given a position and direction (1 -> right, 4 -> up), this function creates a Snake head.
  */
  public Entity createEntity(int x, int y, int direction){
    Entity ret = new Entity(cEnts);
    cEnts ++;
    ret.rComp = cSys.getNewRendComp(ret, 1);
    ret.pComp = cSys.getNewPhysComp(ret, x, y, new Dot(), 1);
    ret.sComp = cSys.getNewSComp(ret, null);
    ret.sComp.last = ret.sComp;
    ret.cComp = cSys.getNewContComp(ret);
    ret.cComp.direction = direction;
    if (ret.id == 0) {
      ret.rComp.farbe = Color.RED;
    } else {
      ret.rComp.farbe = Color.BLUE;
    }
    ret.rComp.renderMe = true;
    return ret;
  }

	/**
  * Given a position, this function creates a block.
  */
  public Entity createBlock(int x, int y){
    Entity ret = new Entity(cEnts);
    cEnts ++;
    ret.rComp = cSys.getNewRendComp(ret, 1);
    ret.pComp = cSys.getNewPhysComp(ret, x, y, new Dot(), 4);
    int farber = (int)(Math.random()* 50);
    int farbeg = (int)(Math.random()* 50);
    int farbeb = (int)(Math.random()* 50);
    ret.rComp.farbe = new Color(farber, farbeg, farbeb, 255);
    ret.rComp.renderMe = true;
    return ret;
  }
  
  /**
  * Creates a pickup at a given position.
  */
  public Entity createPickup(int x, int y){
    Entity ret = new Entity(cEnts);
    cEnts++;
    ret.rComp = cSys.getNewRendComp(ret, 2);
    ret.pComp = cSys.getNewPhysComp(ret, x, y, new Dot(), 5);
    ret.rComp.renderMe = true;
    int farber = (int)(Math.random()* 20);
    int farbeg = (int)(Math.random()* 20);
    int farbeb = (int)(Math.random()* 20);
    ret.rComp.farbe = new Color(230+farber, 170+farbeg, farbeb, 255);
    return ret;
  }
  
  /**
  * Appends a snake segment to a given tail of a Snake. Coloring depends on SP/MP mode.
  */
  public Entity createSnakeTail(Entity tail, boolean multi){
    Entity ret = new Entity(cEnts);
    cEnts++;
    ret.pComp = cSys.getNewPhysComp(ret, tail.pComp.xPos, tail.pComp.yPos, new Dot(), 2);
    ret.sComp = cSys.getNewSComp(ret, tail.sComp);
    SnakeComponent first = ret.sComp.getFirst();
    int firstID = first.upPtr.id;
    first.last = ret.sComp;
    ret.rComp = cSys.getNewRendComp(ret, 1);
    int farber = (int)(Math.random()* 100) - 50;
    int farbeg = (int)(Math.random()* 100) - 50;
    int farbeb = (int)(Math.random()* 100) - 50;
    if(multi){//in multiplayer, colors are assigned differently.
      if (firstID == 0) {
        farber*= 3;
      } else{
        farbeb*= 3;
      }
    } else {
      farber += ret.sComp.next.upPtr.rComp.farbe.getRed();
      farbeg += ret.sComp.next.upPtr.rComp.farbe.getGreen();
      farbeb += ret.sComp.next.upPtr.rComp.farbe.getBlue();
    } // end of if-else
    
    if (farber > 255) {
      farber = 255;
    } // end of if
    if (farber < 0) {
      farber = 0;
    } // end of if
    
    if (farbeg > 255) {
      farbeg = 255;
    } // end of if
    if (farbeg < 0) {
      farbeg = 0;
    } // end of if
    
    if (farbeb > 255) {
      farbeb = 255;
    } // end of if
    if (farbeb < 0) {
      farbeb = 0;
    } // end of if
    
    ret.rComp.farbe = new Color(farber, farbeg, farbeb, 255);
    return ret;
  }
  
  /**
  * Creates a World Border in the given direction. (1 -> right, 4 -> up)
  */
  public Entity createWorldBorder(int direction){
    Entity ret = new Entity(cEnts);
    cEnts ++;
    ret.pComp = cSys.getNewPhysComp(ret, -1, -1, new AABorderShape(direction), 3);
    if (direction == 1) {
      ret.pComp.xPos = 101;//right border
    } // end of if
    if (direction == 2) {
      ret.pComp.yPos = 101;//bottom border
    } // end of if
    return ret;
  }
  
  protected ComponentSystem cSys;
  private int cEnts;
}
