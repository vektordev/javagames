package shared.math;

/**
 * Relative position of two CollisionShapes
 */
public class PositionDiff {
	private int[] pos;

	/**
	 * Default CTor
	 */
	public PositionDiff(int x, int y){
    pos = new int[]{x, y};
  }

	/**
	 * @return int[2]: Returns the inverse PositionDiff
	 */
	public PositionDiff invert() {
		return new PositionDiff(-pos[0], -pos[1]);
	}

	/**
	 * @return int[2]: a clone of the position
	 */
	public int[] getPos() {
		return pos.clone();
	}

}
