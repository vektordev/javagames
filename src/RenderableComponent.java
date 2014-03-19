import java.awt.Color;
/**
 * Schnittstelle zwischen Entity und RenderWindow.
 * Enthält alle Informationen, die RenderWindow zum rendern braucht.
 * Bisher undefinierte types müssen erst in RenderWindow implementiert werden.
 */
public class RenderableComponent extends Component{
  /**
  * Standart-Constructor
  * Benötigt den Index unter dem es im ComponentSystem zu finden ist und einen Zeiger auf den zugeordneten Entity sowie den Typ des RenderableComponents.
  * Type = 1 -> schwarz
  * Type = 2 -> gelb
  * Nur von ComponentSystem aus aufrufen!
  */
  public RenderableComponent(int id, Entity up, int type){
    super(id, up);
    farbe = null;
    this.type = type;
    size = 20;
    renderMe = false;
    farbe = Color.BLACK;
  }
  
  protected int type;
  protected int size;
  protected boolean renderMe;
  protected Color farbe;
  //type:
  //1 = snake
  //2 = Pickup
}