/**
 * Base class for any Components of Entities.
 */

public abstract class Component{
  /**
	 * Default CTor
	 * Requires it's ID in the Component System and a pointer to the associated entity.
   */
  public Component(int i, Entity up){;
    index = i;
    upPtr = up;
  }
  
  protected Entity upPtr;
  protected int index;
}
