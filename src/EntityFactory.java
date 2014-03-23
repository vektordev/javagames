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
    int colorr = (int)(Math.random() * 50);
    int colorg = (int)(Math.random() * 50);
    int colorb = (int)(Math.random() * 50);
    ret.rComp.farbe = new Color(100+colorr, 200+colorg, colorb, 255);
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
    int colorr = (int)(Math.random()* 50);
    int colorg = (int)(Math.random()* 50);
    int colorb = (int)(Math.random()* 50);
    ret.rComp.farbe = new Color(colorr, colorg, colorb, 255);
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
    int colorr = (int)(Math.random()* 20);
    int colorg = (int)(Math.random()* 20);
    int colorb = (int)(Math.random()* 20);
    ret.rComp.farbe = new Color(230+colorr, 170+colorg, colorb, 255);
    return ret;
  }
  
  /**
  * Appends a snake segment to a given tail of a Snake. Coloring depends on SP/MP mode.
  */
  public Entity createSnakeTail(Entity tail, boolean multi){
    Entity ret = new Entity(cEnts);
    ret.pComp = cSys.getNewPhysComp(ret, tail.pComp.xPos, tail.pComp.yPos, new Dot(), 2);
    ret.sComp = cSys.getNewSComp(ret, tail.sComp);
    SnakeComponent first = ret.sComp.getFirst();
    int firstID = first.upPtr.id;
    first.last = ret.sComp;
    ret.rComp = cSys.getNewRendComp(ret, 1);
    int colorr = (int)(Math.random()* 100) - 50;
    int colorg = (int)(Math.random()* 100) - 50;
    int colorb = (int)(Math.random()* 100) - 50;
    if(multi){//in multiplayer, colors are assigned differently.
      if (firstID == 0) {
        colorr*= 3;
      } else{
        colorb*= 3;
      }
    } else {
      colorr += ret.sComp.next.upPtr.rComp.farbe.getRed();
      colorg += ret.sComp.next.upPtr.rComp.farbe.getGreen();
      colorb += ret.sComp.next.upPtr.rComp.farbe.getBlue();
    } // end of if-else
    
    if (colorr > 255) {
      colorr = 255;
    } // end of if
    if (colorr < 0) {
      colorr = 0;
    } // end of if
    
    if (colorg > 255) {
      colorg = 255;
    } // end of if
    if (colorg < 0) {
      colorg = 0;
    } // end of if
    
    if (colorb > 255) {
      colorb = 255;
    } // end of if
    if (colorb < 0) {
      colorb = 0;
    } // end of if
    
	  ret.rComp.farbe = new Color(colorr, colorg, colorb, 255);
    cEnts++;
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
