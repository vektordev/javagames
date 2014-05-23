package gamelogic.entity;

/**
 * Base class for any Components of Entities.
 */

public abstract class Component {

	protected Entity upPtr;
	protected int index;

	/**
	 * Default Constructor Requires it's ID in the Component System and a pointer to
	 * the associated entity.
	 */
	public Component(int i, Entity up) {
		index = i;
		upPtr = up;
	}

	
	/*
	 * GETTER AND SETTER METHODS
	 */
	
	public Entity getAssocEntity(){
		return upPtr;
	}
}
