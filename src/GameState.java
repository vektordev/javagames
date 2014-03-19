/**
 * Enthält die gesamte Spiel-Logik und alle verbundenen Daten ohne Rendering.
 */
public class GameState{
  /**
  * Standart-Constructor.
  * Benötigt die geforderte Größe der Spielwelt in pixeln. 20 pixel = 1 Kachel.
  */
  public GameState(int xRes, int yRes, Settings settings){;
    isRunning = true;
    cSys = new ComponentSystem();
    eFac = new EntityFactory(cSys);
    cDis = new CollisionDispatcher();
    xSize = xRes;
    ySize = yRes;
    playerLength = new int[2];
    entities = new Entity[1209];
    entities[0] = eFac.createEntity(0,0,1);
    entities[0].cComp.updateVelocity();
    numPlayers = 1;
    if (settings.isMP()) {
      entities[1] = eFac.createEntity(xRes-20,yRes-20,3);
      entities[1].cComp.updateVelocity();
      numPlayers++;
    } // end of if
    entities[2] = eFac.createWorldBorder(1);
    entities[2].pComp.xPos = xRes;
    entities[2].pComp.yPos = yRes;
    entities[3] = eFac.createWorldBorder(2);
    entities[3].pComp.yPos = yRes;
    entities[3].pComp.xPos = xRes;
    entities[4] = eFac.createWorldBorder(3);
    entities[5] = eFac.createWorldBorder(4);
    createBackground(xRes, yRes);
    spawnPickup(settings.getNoOfPickups());
  }
  
  private void createBackground(int x, int y){
    for (int i = 0; i < (x); i+= 20) {
      for (int j = 0; j< (y); j+= 20) {
        //System.out.println("Pos: " + i + " " + j);
        entities[getFreeEntitiesSpace()] = eFac.createGrassTile(i, j);
      } // end of for
    } // end of for
  }
  
  /**
  * Führt die Simulation um einen Schritt fort.
  */
  public void run(){
    //System.out.println(playerLength[0]);
    //System.out.println(playerLength[1]);
    //System.out.println("tick");
    cDis.dispatchCollisions(cSys.pComps);
    for (int i = 0; i < entities.length; i++) {
      
      if(entities[i] != null){
        if (entities[i].sComp != null && entities[i].sComp.elementsToGrow != 0) {
          entities[getFreeEntitiesSpace()] = eFac.createSnakeTail(entities[i], isMultiplayer());
          playerLength[entities[i].sComp.getFirst().upPtr.id]++;
          entities[i].sComp.elementsToGrow--;
        } // end of if//*/
        entities[i].bufferNextPos();
      }
    } // end of for
    for (int i = 0; i < entities.length; i++) {
      if(entities[i] != null){
        
        entities[i].run();
        /*if (entities[i].sComp != null && entities[i].sComp.elementsToGrow != 0) {
        entities[getFreeEntitiesSpace()] = eFac.createSnakeTail(entities[i]);
        entities[i].sComp.elementsToGrow--;
        } // end of if//*/
        //System.out.println("Entities: " + i);
      }
    } // end of for
    for (int i = 0; i < entities.length; i++) {
      if(entities[i] != null){
        entities[i].moveToBuffPos();
        if (entities[i].rComp != null) {
          entities[i].rComp.renderMe = true;
        } // end of if
      }
      
    } // end of for
    for (int i = 0; i < entities.length; i++) {
      
      if(entities[i] != null && entities[i].killThisThing){
        if (entities[i].pComp.type == 5) {
          spawnPickup();
        } else{
          //assuming that this is a Snake
          isRunning = false;
        }
        deleteComponents(entities[i]);
        entities[i] = null;
      }
    } // end of for
    if ((entities[0] == null || entities[0].cComp == null) && (entities[1] == null || entities[1].cComp == null)) {
      isRunning = false;
    } // end of if
    if (!isRunning) {
      try { 
        printScore();
        Thread.sleep(1500);
      } catch(Exception e) {
        System.out.println("Houston!!!");
      } finally {
      } // end of try
    } // end of if
  }
  
  private void printScore(){
    System.out.println();
    System.out.println();
    System.out.println();
    if (numPlayers > 1) {
      System.out.println("" + numPlayers + " Spieler");
      boolean[] alive = new boolean[numPlayers];
      for (int i = 0; i < alive.length; i++) {
        alive[i] = false;
        if (entities[i] != null && entities[i].sComp !=  null && entities[i].sComp.last != null) {
          alive[i] = true;
        } // end of if
        System.out.println("Spieler " + (i+1) + " hat die Laenge " + playerLength[i] + " erreicht und hat " + ((alive[i])? "" : "nicht ") + "ueberlebt.");
      } // end of for
      if (alive[0]) {
        System.out.println("Spieler 1 hat gewonnen");
      } else {
        if (alive[1]) {
          System.out.println("Spieler 2 hat gewonnen");
        } else {
          if (playerLength[0] > playerLength[1]) {
            System.out.println("Spieler 1 hat gewonnen");
          } else{
            if (playerLength[1] > playerLength[0]) {
              System.out.println("Spieler 2 hat gewonnen");
            } else {
              System.out.println("Unentschieden");
              } // end of if-else
            }
          } // end of if-else
        } // end of if-else
    } else {
      System.out.println("Der Spieler hat die Laenge " + playerLength[0] + " erreicht.");
    } // end of if-else
    System.out.println();
    System.out.println();
    System.out.println();
  }
  
  private void spawnPickup(int n){
    for (int i = 0; i<n; i++) {
      spawnPickup();
    } // end of for
  }
  
  private void spawnPickup(){
    //TODO: Find Free Position
    //System.out.println("creating shit n stuff");
    int xCoord = ((int)(Math.random() * ((int)(xSize/20)))) * 20;
    int yCoord = ((int)(Math.random() * ((int)(ySize/20)))) * 20;
    int index = -1;
    for (int i = 0; i< entities.length && (index == -1); i++) {
      //System.out.println("try");
      if (entities[i] == null) {
        entities[i] = eFac.createPickup(xCoord, yCoord);
        index = i;
        //System.out.println("win");
      } // end of if
    } // end of for
    if (index == -1) {
      System.out.println("Houston, standby, we may have had a Problem");
    } // end of if
    while (cDis.checkCollisionsNoDispatch(entities[index].pComp.index, cSys.pComps)) { 
      entities[index].pComp.xPos = ((int)(Math.random() * ((int)(xSize/20)))) * 20;
      entities[index].pComp.yPos = ((int)(Math.random() * ((int)(ySize/20)))) * 20;
      //System.out.println("Retrying positioning");
    } // end of while
  }
  
  private int getFreeEntitiesSpace(){
    for (int i = 0; i< entities.length; i++) {
      if(entities[i] == null){
        return i;
      }
    } // end of for
    System.out.println("Entities is full");
    return 0;
  }
  
  private void deleteComponents(Entity e){
    if (e.rComp != null) {
      cSys.rComps[e.rComp.index] = null;
    } // end of if
    if (e.pComp != null) {
      cSys.pComps[e.pComp.index] = null;
    } // end of if
    if (e.cComp != null) {
      cSys.cComps[e.cComp.index] = null;
    } // end of if
  }
  
  protected boolean isMultiplayer(){
    return (numPlayers > 1);
  }
  
  protected int numPlayers;
  protected boolean isRunning;
  protected int xSize;
  protected int ySize;
  protected int[] playerLength;
  protected EntityFactory eFac;
  protected ComponentSystem cSys;
  protected CollisionDispatcher cDis;
  protected Entity[] entities;
}
