import java.util.NoSuchElementException;

/** Basic component of a double linked list
 *
 * @author Bruce Dong
 */

public class DoubleLinkedList<T> implements Iterable<T> {
  /** a reference to the first node of the double linked list */
  private DLNode<T> front;
  
  /** a reference to the last node of a double linked list */
  private DLNode<T> back;
  
  private int FrontSet =0;
  private int BackSet =0;
  
  
  /** Create an empty double linked list. */
  public DoubleLinkedList() {
    front = back = null;
  }
  
  /** 
   * Returns true if the list is empty.
   * @return  true if the list has no nodes
   */
  protected boolean isEmpty() {
    return (getFront() == null);
  }
  
  /**
   * Returns the reference to the first node of the linked list.
   * @return the first node of the linked list
   */
  public DLNode<T> getFront() {
    return front;
  }
  
  /**
   * Sets the first node of the linked list.
   * @param node  the node that will be the head of the linked list.
   */
  protected void setFront(DLNode<T> node) {
    front = node;
    if(front != null){
      if(front.getNext() != null)
        front.getNext().setPrevious(front);
    }
  }
  
  /**
   * Returns the reference to the last node of the linked list.
   * @return the last node of the linked list
   */
  protected DLNode<T> getBack() {
    return back;
  }
  
  /**
   * Sets the last node of the linked list.
   * @param node the node that will be the last node of the linked list
   */
  protected void setBack(DLNode<T> node) {
    back = node;
    if(back != null){
      if(back.getPrevious() != null)
        back.getPrevious().setNext(back);
    }
  }
  
  /*----------------------------------------*/
  /* METHODS TO BE ADDED DURING LAB SESSION */
  /*----------------------------------------*/
  
  /**
   * Add an element to the head of the linked list.
   * @param element  the element to add to the front of the linked list
   */
  public void addToFront(T element) {
    DLNode<T> currentBack = this.getBack();
    
    this.setBack(this.getFront());
    this.setFront( new DLNode<T> ( element, null, this.getBack() ));
    if( this.getFront().getNext() != null)
      this.getBack().setPrevious(this.getFront());

    if( this.getFront() !=null && currentBack != null){
      if( this.getFront().getNext() ==null && currentBack.getPrevious() == null){
        this.getFront().setNext(currentBack);
        currentBack.setPrevious(this.getFront());
      }
    }

    // find the back element and link them
    while( currentBack != null){
      this.setBack(currentBack);
      currentBack = currentBack.getNext();
    }
    link();
  }
  
  /**
   * Add an element to the tail of the linked list.
   * @param element  the element to add to the tail of the linked list
   */
  public void addToBack(T element) {
    DLNode<T> currentFront = this.getFront();
    if( this.getFront() != null ){ 
      if( this.getFront().getNext() == null)
        this.getFront().setNext(this.getBack());
    }
    setFront(getBack());
    setBack( new DLNode<T> ( element, this.getFront(), null ));
    if( this.getFront() != null)
     this.getFront().setNext(this.getBack());

    // find the back element and link them
    while( currentFront != null){
      this.setFront(currentFront);
      currentFront = currentFront.getPrevious();
    }
    link();
  }

  /**
   * Remove and return the element at the front of the linked list.
   * @return the element that was at the front of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromFront() throws NoSuchElementException {
    if (this.getFront() == null)
      throw new NoSuchElementException();
    
    this.setFront(this.getFront().getNext());

    if( this.getFront() == null){
      DLNode<T> currentBack = new DLNode<T> ( this.getBack().getElement(), null, null);
      this.setBack(null);
      return currentBack.getElement();
    }
    return this.getFront().getPrevious().getElement();
  }
  
  /**
   * Remove and return the element at the back of the linked list.
   * @return the element that was at the back of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromBack() {
    if (this.getBack() == null)
      throw new NoSuchElementException();
    this.setBack(this.getBack().getPrevious());
    
    if( this.getBack() == null){
      DLNode<T> currentFront = new DLNode<T> ( this.getFront().getElement(), null, null);
      this.setFront(null);
      return currentFront.getElement();
    }
    return this.getBack().getNext().getElement();
  }

  /**
   * return the total DLlist
   * @return the total DLlist
   */
  public DoubleLinkedList<T> getItself(){
    return this;
  }
     
  /**
   * Returns an iterator for the linked list that starts at the head of the list and runs to the tail.
   * @return  the iterator that runs through the list from the head to the tail
   */
  @Override
  public DoubleLinkedListIterator<T> iterator() {
    return new DoubleLinkedListIterator<T>(null, getFront(), this);
  }
  
  /**
   * Link the front and back node if nothing is in between
   */
  public void link(){
    if( front != null && back != null){
      if( front.getNext() == null && back.getPrevious() ==null){
        front.setNext(back);
        back.setPrevious(front);
      }
    }
  }
}
