//TODO: collision detection, comments

public class AABoxShape extends CollisionShape{
  public AABoxShape(int x, int y){;
    size = new int[2];
    size[0] = x;
    size[1] = y;
    type = 2;
  }

	public int getType(){
		return 3;
	}
  
  public CollisionSolution collideWith(CollisionShape otherOne, PositionDiff pos){
    if (otherOne.getType() > this.getType()) {
      return otherOne.collideWith(this, pos.invert());
    } // end of if
    CollisionSolution ret = new CollisionSolution();
		switch (otherOne.getType()){
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		}
    return ret;
  }
  
  int[] size;
}
