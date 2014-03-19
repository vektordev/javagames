import java.awt.Color;
/**
 * Interface between Entity and RenderWindow.
 * Enables RenderWindow to render an entity.
 */
public class RenderableComponent extends Component{
  /**
	 * Default CTor
	 * Requires it's ID in the Component System and a pointer to the associated entity as well as the object's type
   * Type = 1 -> foreground
   * Type = 2 -> background
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
