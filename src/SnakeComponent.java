/**
 * Coordinates the Snake's element's movement.
 */
public class SnakeComponent extends Component{
  
  /**
	 * Default CTor
	 * Requires it's ID in the Component System and a pointer to the associated
	 * entity as well as a pointer to the Snake's next element.
   */
  public SnakeComponent(int i, Entity up, SnakeComponent next){
    super(i, up);
    elementsToGrow = 0;
    this.next = next;
    last = null;
    buffNextXPos = 0;
    buffNextYPos = 0;
  }
  
  /**
   * Returns the snake's head.
   */
  public SnakeComponent getFirst(){
    if (next == null) {
      return this;
    } // end of if
    return next.getFirst();
  }
  
  /**
   * Returns the snake's last element.
	 * Relies on EntityFactory to set the last-pointer correctly.
   */
  public SnakeComponent getLast(){
    return getFirst().last;
  }
  
  protected SnakeComponent next;
  protected int elementsToGrow;
  protected SnakeComponent last;//only valid if next == null
  
  protected int buffNextXPos;
  protected int buffNextYPos;
}
