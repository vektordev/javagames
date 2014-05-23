package client.rendering;

import gamelogic.entity.Component;
import gamelogic.entity.Entity;

import java.awt.Color;

/**
 * Interface between Entity and RenderWindow. Enables RenderWindow to render an
 * entity.
 */
public class RenderableComponent extends Component {

	protected int type;
	protected int size;
	protected boolean renderMe;
	protected Color color;

	/**
	 * Default CTor Requires it's ID in the Component System and a pointer to
	 * the associated entity as well as the object's type Type = 1 -> foreground
	 * Type = 2 -> background
	 */
	public RenderableComponent(int id, Entity up, int type) {
		super(id, up);
		this.type = type;
		size = 20;
		renderMe = false;
		color = Color.BLACK;
	}
	
	public void render(){
		renderMe = true;
	}
	
	/*
	 * GETTER AND SETTER METHODS
	 */
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		this.color = c;
	}
	
	public void setColor(int r, int g, int b, int gamma){
		color = new Color(r, g, b, gamma);
	}

}
