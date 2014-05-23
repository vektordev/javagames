package gamelogic.physics;

import gamelogic.entity.PhysicalComponent;
import shared.math.PositionDiff;

/**
 * Detects Collisions and sends out events.
 */

public class CollisionDispatcher {
	/**
	 * Detects all collisions, sends out events.
	 */
	public void dispatchCollisions(PhysicalComponent[] in) {
		// System.out.println("checking collisions");
		for (int i = 0; i < in.length; i++) {
			for (int j = i + 1; j < in.length; j++) {
				if ((in[i] != null) && (in[j] != null)) {
					boolean needsCollision = false;
					if (in[i].getType() == 1 || in[j].getType() == 1) {
						needsCollision = true;
					} // end of if
					if (in[i].getType() == 0 || in[j].getType() == 0) {
						needsCollision = false;
					} // end of if
					/*
					 * if (in[i].type == 3 && in[j].type == 3) { needsCollision
					 * = false; } // end of if if (in[i].type == 2 && in[j].type
					 * == 2) { needsCollision = false; } // end of if if
					 * (in[i].type == 5 && in[j].type == 5) { needsCollision =
					 * false; } // end of if//
					 */
					if (needsCollision) {
						PositionDiff pDiff = new PositionDiff(in[j].getXPos()
								- in[i].getXPos(), in[j].getYPos()
								- in[i].getYPos());
						CollisionSolution sol = in[i].getShape().collideWith(
								in[j].getShape(), pDiff);
						if (sol.isColliding()) {
							// System.out.println("Collision Detected!");
							// System.out.println(in[i].upPtr.id);
							// System.out.println(in[j].upPtr.id);
							in[i].getAssocEntity().performOnCollision(sol,
									in[j].getAssocEntity());
							in[j].getAssocEntity().performOnCollision(sol,
									in[i].getAssocEntity());
						} // end of if
					} // end of if
				} // end of if
			} // end of for
		} // end of for
	}

	/**
	 * checks whether the Component at index i collides with any other
	 * component. Doesn't send out events, only returns a boolean.
	 */
	public boolean checkCollisionsNoDispatch(int pos[], PhysicalComponent[] in) {

		if(pos.length != 2)
			return false;
		
		for(PhysicalComponent pc: in){
			if(pc.getXPos() == pos[0] && pc.getYPos() == pos[1])
				return false;
		}
		return true;
		
		/*
		 * OLD IMPLEMENTATION, DOES NOT WORK ANYMORE
		 *//*
		// System.out.println("NoDispatch CollisionDetect");
		for (int j = 0; j < in.length; j++) {
			if (i == j) {
				j++;
				if (j >= in.length) {
					return false;
				} // end of if
			} // end of if
			if ((in[i] != null) && (in[j] != null)) {
				boolean needsCollision = true;
				if (in[i].getType() == 1 || in[j].getType() == 1) {
					needsCollision = true;
				} // end of if
				if (in[i].getType() == 0 || in[j].getType() == 0) {
					needsCollision = false;
				} // end of if
				if (needsCollision) {
					PositionDiff pDiff = new PositionDiff(in[j].getXPos()
							- in[i].getXPos(), in[j].getYPos()
							- in[i].getYPos());
					CollisionSolution sol = in[i].getShape().collideWith(
							in[j].getShape(), pDiff);
					if (sol.isColliding()) {
						// System.out.println("NoDispatch Collision Detected!");
						// System.out.println(in[i].upPtr.id);
						// System.out.println(in[j].upPtr.id);
						return sol.isColliding();
					} // end of if
				} // end of if
			} // end of if
		} // end of for
		return false;*/
	}
}
