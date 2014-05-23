package gamelogic.physics;

import shared.math.PositionDiff;

/**
 * Axis-aligned line that splits the world into a in-accessible and an
 * accessible part.
 */
public class AABorderShape extends CollisionShape {
	
	protected int axis;
	// 1 = +x
	// 2 = +y
	// 3 = -x
	// 4 = -y
	
	/**
	 * Default-Constructor. 
	 * @param: axis(int): as axis direction:
	 * <li> 1: cuts off left of this entity's position </li>
	 * <li> 2: cuts off below this entity's position </li>
	 * <li> 3: cuts off right of this entity's position </li>
	 * <li> 4: cuts off above this entity's position </li>
	 */
	public AABorderShape(int axis) {
		this.axis = axis;
	}

	/**
	 * @inheritDoc
	 */
	public int getType() {
		// should be static for the most part, but whatever. This function is
		// basically just type info.
		return 2;
	}

	/**
	 * @inheritDoc
	 * @param otherOne(CollsisionShape):
	 * @param pos(PositionDiff): 
	 * @return CollsisionSolution: 
	 */
	public CollisionSolution collideWith(CollisionShape otherOne, PositionDiff pos) {
		
		if (otherOne.getType() > this.getType()) {
			return otherOne.collideWith(this, pos.invert());
		}
		
		CollisionSolution ret = new CollisionSolution();
		if (otherOne.getType() == 1) {
			if (axis == 1 && pos.getPos()[0] >= 0) {
				ret.setCollides(true);
			}
			else if (axis == 2 && pos.getPos()[1] >= 0) {
				ret.setCollides(true);
			}
			else if (axis == 3 && pos.getPos()[0] <= 0) {
				ret.setCollides(true);
			}
			else if (axis == 4 && pos.getPos()[1] <= 0) {
				ret.setCollides(true);
			}
		}
		return ret;
	}

	
}
