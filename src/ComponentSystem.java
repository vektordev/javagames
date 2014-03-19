import java.util.Vector;
/**
 * Speichert alle Components und organisiert die Erstellung Neuer. Bitte nur über diese Klasse neue Components erstellen.
 */
public class ComponentSystem{
  /**
  * Standart-Constructor
  */
  
  ComponentSystem(){
    rComps = new RenderableComponent[1205];
    pComps = new PhysicalComponent[1209];
    cComps = new ControllableComponent[2];
    sComps = new SnakeComponent[605];
  }
  
  /**
  * Standart-Quelle für RenderableComponents
  */
  public RenderableComponent getNewRendComp(Entity up, int type){
    //RenderableComponent ret = new RenderableComponent();
    for (int i = 0; i<rComps.length; i++) {
      if (rComps[i] == null) {
        //if (ret == null) {
        //  System.out.println("Error 1");
        //} // end of if
        rComps[i] = new RenderableComponent(i, up, type);
        //System.out.println("creating RComp at: " + i);
        return rComps[i];
      } // end of if
    } // end of for
    System.out.println("RComps Full");
    return null;
  }
  
  /**
  * Standart-Quelle für SnakeComponents.
  */
  public SnakeComponent getNewSComp(Entity up, SnakeComponent next){
    for (int i = 0; i<rComps.length; i++) {
      if (sComps[i] == null) {
        sComps[i] = new SnakeComponent(i, up, next);
        return sComps[i];
      } // end of if
    } // end of for
    System.out.println("SComps Full");
    return null;
  }
  
  /**
  * Standart-Quelle für PhysicalComponents.
  */
  public PhysicalComponent getNewPhysComp(Entity up, int x, int y, CollisionShape cShape, int type){
    for (int i = 0; i<pComps.length; i++) {
      if (pComps[i] == null) {
        pComps[i] = new PhysicalComponent(i, up, x, y, cShape, type);
        return pComps[i];
      } // end of if
    } // end of for
    System.out.println("PComps Full");
    return null;
  }
  
  /**
  * Standart-Quelle für ControllableComponents.
  */
  public ControllableComponent getNewContComp(Entity up){
    for (int i = 0; i<cComps.length; i++) {
      if (cComps[i] == null) {
        cComps[i] = new ControllableComponent(i, up);
        return cComps[i];
      } // end of if
    } // end of for
    System.out.println("CComps Full");
    return null;
  }
  
  protected SnakeComponent[] sComps;
  protected RenderableComponent[] rComps;
  protected PhysicalComponent[] pComps;
  protected ControllableComponent[] cComps;
}