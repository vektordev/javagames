/**
 * Bewegungskoodination für Schlangenrumpf-Segmente.
 */
public class SnakeComponent extends Component{
  
  /**
	 * Default CTor
	 * Requires it's ID in the Component System and a pointer to the associated entity.
  * Benötigt den Index unter dem es im ComponentSystem zu finden ist und einen Zeiger auf den zugeordneten Entity sowie das nächste Element der Schlange.
  * Nur von ComponentSystem aus aufrufen!
  */
  public SnakeComponent(int i, Entity up, SnakeComponent next){
    super(i, up);
    elementsToGrow = 0;
    this.next = next;
    last = null;
    buffNextXPos = 0;
    buffNextYPos = 0;
  }
  
  /**
   * Gibt das erste Element der Schlange zurück.
   */
  public SnakeComponent getFirst(){
    if (next == null) {
      return this;
    } // end of if
    return next.getFirst();
  }
  
  /**
   * Gibt das letzte Element der Schlange zurück.
   * Funkitoniert nur, sofern der Zeiger 'last' des ersten Elements (Kopf) gesetzt wurde.
   * Dies wird von EntityFactory standartmäßig korrekt ausgeführt.
   */
  public SnakeComponent getLast(){
    return getFirst().last;
  }
  
  protected SnakeComponent next;
  protected int elementsToGrow;
  protected SnakeComponent last;//only valid if next == null
  
  protected int buffNextXPos;
  protected int buffNextYPos;
}
