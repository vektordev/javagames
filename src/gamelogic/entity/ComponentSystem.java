package gamelogic.entity;

import gamelogic.physics.CollisionShape;

import java.util.AbstractSequentialList;
import java.util.Deque;
import java.util.LinkedList;

import client.rendering.RenderableComponent;


/**
 * Acts as a factory and container for Components of all tastes.
 */
public class ComponentSystem {

	
	//Deque was choose to be prepared for changes in the future
	protected Deque<SnakeComponent> sComps;
	protected Deque<RenderableComponent> rComps;
	protected Deque<PhysicalComponent> pComps;
	protected Deque<ControllableComponent> cComps;

	/**
	 * Default-Constructor
	 * @information: initializes the containers for the components
	 */
	public ComponentSystem() {
		rComps = new LinkedList<RenderableComponent>();		//expected length 2609
		pComps = new LinkedList<PhysicalComponent>();		//expected length 2609
		cComps = new LinkedList<ControllableComponent>();	//expected length 2
		sComps = new LinkedList<SnakeComponent>();			//expected length 1305
	}

	/**
	 * Call for an instance of RenderableComponent.
	 * @param type(int): parameter for the new RenderableComponent
	 * @param up(Entity): parameter for the new RenderableComponent
	 * @return RenderableComponent: the appended RenderableComponent 
	 */
	public RenderableComponent getNewRendComp(Entity up, int type) {
		
		rComps.addLast(new RenderableComponent((rComps.size()), up, type));
		return rComps.getLast();
		
		/* TO REMOVE: OLD IMPLEMENTATION
		// RenderableComponent ret = new RenderableComponent();
		for (int i = 0; i < rComps.length; i++) {
			if (rComps[i] == null) {
				// if (ret == null) {
				// System.out.println("Error 1");
				// } // end of if
				rComps[i] = new RenderableComponent(i, up, type);
				// System.out.println("creating RComp at: " + i);
				return rComps[i];
			} // end of if
		} // end of for
		System.out.println("RComps Full");
		return null;*/
	}

	/**
	 * Call for an instance of SnakeComponent.
	 * @param up(Entity): parameter for the new SnakeComponent
	 * @param next(SnakeComponent): parameter for the SnakeComponent  
	 * @return SnakeComponent: the appended SnakeComponent.
	 */
	public SnakeComponent getNewSComp(Entity up, SnakeComponent next) {
		
		sComps.addLast(new SnakeComponent(sComps.size(), up, next));
		return sComps.getLast();
		
		/* TO REMOVE: OLD IMPLEMENTATION
		for (int i = 0; i < rComps.length; i++) {
			if (sComps[i] == null) {
				sComps[i] = new SnakeComponent(i, up, next);
				return sComps[i];
			} // end of if
		} // end of for
		System.out.println("SComps Full");
		return null;*/
	}

	/**
	 * Call for an instance of PhysicalComponent.
	 */
	public PhysicalComponent getNewPhysComp(
			Entity up, 
			int x, 
			int y, 
			CollisionShape cShape, 
			int type
	){
		
		pComps.addLast(new PhysicalComponent(pComps.size(), up, x, y, cShape, type));
		return pComps.getLast();
		
		/* TO REMOVE: OLD IMPLEMENTATION
		for (int i = 0; i < pComps.length; i++) {
			if (pComps[i] == null) {
				pComps[i] = new PhysicalComponent(i, up, x, y, cShape, type);
				return pComps[i];
			} // end of if
		} // end of for
		System.out.println("PComps Full");
		return null;*/
	}

	/**
	 * Call for an instance of ControllableComponent.
	 */
	public ControllableComponent getNewContComp(Entity up) {
		
		cComps.addLast(new ControllableComponent(cComps.size(), up));
		return cComps.getLast();
		
		
		/* TO REMOVE: OLD IMPLEMENTATION
	 	for (int i = 0; i < cComps.length; i++) {
			if (cComps[i] == null) {
				cComps[i] = new ControllableComponent(i, up);
				return cComps[i];
			} // end of if
		} // end of for
		System.out.println("CComps Full");
		return null;*/
	}
	
	public void removeComponent(Entity e){
		cComps.remove(e.cComp);
		rComps.remove(e.rComp);
		pComps.remove(e.pComp);
		
	}

	/*
	 * GETTER AND SETTER METHODS
	 */
	
	public RenderableComponent[] getrComps() {
		RenderableComponent[] rca = new RenderableComponent[rComps.size()];
		return rComps.toArray(rca);
	}
	
	public PhysicalComponent[] getpComps(){
		PhysicalComponent[] pca = new PhysicalComponent[pComps.size()];
		return pComps.toArray(pca);
	}

}
