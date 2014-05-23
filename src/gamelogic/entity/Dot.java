package gamelogic.entity;

import shared.math.PositionDiff;
import gamelogic.physics.CollisionShape;
import gamelogic.physics.CollisionSolution;

/**
 * Dot-Shaped collision object.
 */
public class Dot extends CollisionShape {
	/**
	 * Default-CTor
	 */
	public Dot() {
	}

	/**
	 * @inheritDoc
	 */
	public int getType() {
		return 1;
	}

	/**
	 * @inheritDoc
	 */
	public CollisionSolution collideWith(CollisionShape otherOne, PositionDiff pos) {

		if (otherOne.getType() > this.getType()) {
			return otherOne.collideWith(this, pos.invert());
		}
		
		CollisionSolution ret = new CollisionSolution();
		if (otherOne.getType() == 1) {
			ret.setCollides((pos.getPos()[0] == 0) && (pos.getPos()[1] == 0));
		}
		
		return ret;
	}
}
