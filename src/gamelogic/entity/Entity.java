package gamelogic.entity;

import client.rendering.RenderableComponent;
import gamelogic.physics.CollisionSolution;

/**
 * An Entity in the game world.
 */
public class Entity {
	
	protected RenderableComponent rComp;
	protected PhysicalComponent pComp;
	protected ControllableComponent cComp;
	protected SnakeComponent sComp;
	protected int id;
	protected boolean killThisThing;
	
	/**
	 * Default-Constructor for empty Entity
	 */
	public Entity(int id) {
		this.id = id;
		pComp = null;
		cComp = null;
		rComp = null;
		sComp = null;
		killThisThing = false;
	}

	public void run() {
		pComp.xPos += pComp.veloc[0];
		pComp.yPos += pComp.veloc[1];
		if (sComp != null) {
			if (sComp.getFirst().last == null) {
				killThisThing = true;
			} // end of if
			if (sComp.getFirst().upPtr == null
					|| sComp.getFirst().upPtr.killThisThing) {
				killThisThing = true;
			} // end of if
		} // end of if
	}

	/**
	 * Returns the position as an int[2]. [0] is x, [1] is y.
	 */
	public int[] getPosition() {
		int[] ret = new int[2];
		ret[0] = pComp.xPos;
		ret[1] = pComp.yPos;
		return ret;
	}

	public void bufferNextPos() {
		// System.out.println("tryinna buffer");
		if (sComp != null && sComp.next != null) {
			// System.out.println("erryday im buffering");
			Entity nextEntity = sComp.next.upPtr;
			nextEntity.getPosition();
			int[] pos = nextEntity.getPosition();
			sComp.buffNextXPos = pos[0];
			sComp.buffNextYPos = pos[1];
		} // end of if
	}

	public void moveToBuffPos() {
		// System.out.println("tryin to move");
		if (sComp != null && sComp.next != null) {
			// System.out.println("moving");
			pComp.xPos = sComp.buffNextXPos;
			pComp.yPos = sComp.buffNextYPos;
		} // end of if
	}

	public void performOnCollision(CollisionSolution sol, Entity otherOne) {
		int otherType = otherOne.pComp.type;
		if (pComp.type == 1) {// SnakeHead
			if (otherType == 1 || otherType == 2 || otherType == 3
					|| otherType == 4) {// Snakehead, Snake, Wall or Block
				destroy();
			} // end of if
			if (otherType == 5) {// PickUp
				this.sComp.last.elementsToGrow++;
			} // end of if
		} else {
			if (pComp.type == 5) {// PickUp
				destroy();
			} else {
				if (pComp.type == 2) {// snakeTail
					if (otherType == 3 || otherType == 4) {// Wall/Block
						destroy();
					} // end of if
				} // end of if

			} // end of if-else
		}
	}

	protected void destroy() {
		killThisThing = true;
	}
	
	public boolean shallBeDestroyed(){
		return killThisThing;
	}
	
	public void updateVelocity() {
		cComp.updateVelocity();
	}
	
	/**
	 * 
	 * @param x(int): the new x number
	 * @param y(int): the new y number
	 */
	public void setPos(int x, int y){
		pComp.xPos = x;
		pComp.yPos = y;
	}
	
	/**
	 * 
	 * @return boolean: whether there are still elements to grow or not
	 */
	public boolean StillElementsToGrow(){
		return sComp != null && sComp.elementsToGrow != 0;
	}
	
	public int getSnakeheadID(){
		return sComp.getFirst().upPtr.id;
	}
	
	public void growUp(){
		sComp.elementsToGrow--;
	}
	
	public boolean physicalComponentIsPickUp(){
		return pComp.isPickUp();
	}
	
	public void render(){
		rComp.render();
	}
	
	public boolean hasRenderableComponent(){
		return rComp != null;
	}
	
	public boolean hasControllableComponent(){
		return cComp != null;
	}
	
	public boolean hasSnake(){
		return sComp != null && sComp.last != null;
	}
	
	public int getPhysicalIndex(){
		return rComp.index;
	}
	
	/*
	 * GETTER AND SETTER METHODS
	 */
	
	public ControllableComponent getAssocControllableComponent(){
		return this.cComp;
	}

	public boolean hasPhysicalComponent() {
		return pComp != null;
	}
}
