/**
 * Basisklasse für alle Komponenten von Entities.
 */

public abstract class Component{
  /**
   * Benötigt den Index unter dem es im ComponentSystem zu finden ist und einen Zeiger auf den zugeordneten Entity.
   * Nur von Implementierungen von Component aus aufrufen!
   */
  public Component(int i, Entity up){;
    index = i;
    upPtr = up;
  }
  
  protected Entity upPtr;
  protected int index;
}