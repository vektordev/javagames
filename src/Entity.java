/**
 * An Entity in the game world.
 */
public class Entity{
  /**
  * Default-CTor for empty Entity
  */
  Entity(int id){
    this.id = id;
    pComp = null;
    cComp = null;
    rComp = null;
    sComp = null;
    killThisThing = false;
  }
  
  protected void run(){
    pComp.xPos += pComp.veloc[0];
    pComp.yPos += pComp.veloc[1];
    if (sComp != null) {
      if (sComp.getFirst().last == null) {
        killThisThing = true;
      } // end of if
      if (sComp.getFirst().upPtr == null || sComp.getFirst().upPtr.killThisThing) {
        killThisThing = true;
      } // end of if
    } // end of if
  }
  
  /**
  * Returns the position as an int[2]. [0] is x, [1] is y.
  */
  public int[] getPosition(){
    int[] ret = new int[2];
    ret[0] = pComp.xPos;
    ret[1] = pComp.yPos;
    return ret;
  }
  
  protected void bufferNextPos(){
    //System.out.println("tryinna buffer");
    if (sComp != null && sComp.next != null) {
      //System.out.println("erryday im buffering");
      Entity nextEntity =  sComp.next.upPtr;
      nextEntity.getPosition();
      int[] pos = nextEntity.getPosition();
      sComp.buffNextXPos = pos[0];
      sComp.buffNextYPos = pos[1];
    } // end of if
  }
  
  protected void moveToBuffPos(){
    //System.out.println("tryin to move");
    if (sComp != null && sComp.next != null) {
      //System.out.println("moving");
      pComp.xPos = sComp.buffNextXPos;
      pComp.yPos = sComp.buffNextYPos;
    } // end of if
  }
  
  protected void performOnCollision(CollisionSolution sol, Entity otherOne){
    int otherType = otherOne.pComp.type;
    if (pComp.type == 1) {//SnakeHead
      if (otherType == 1 || otherType == 2 || otherType == 3 || otherType == 4) {//Snakehead, Snake, Wall or Block
        destroy();
      } // end of if
      if (otherType == 5) {//PickUp
        this.sComp.last.elementsToGrow++;
      } // end of if
    } else {
      if(pComp.type == 5) {//PickUp
        destroy();
      } else {
        if (pComp.type == 2) {//snakeTail
          if (otherType == 3 || otherType == 4) {//Wall/Block
            destroy();
          } // end of if
        } // end of if
        
      } // end of if-else
    }
  }
  
  protected void destroy(){
    killThisThing = true;
  }
  
  protected RenderableComponent rComp;
  protected PhysicalComponent pComp;
  protected ControllableComponent cComp;
  protected SnakeComponent sComp;
  protected int id;
  protected boolean killThisThing;
}
