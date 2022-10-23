import java.util.ListIterator;
import java.util.NoSuchElementException;


/** Basic component of a double linked list list iterator
 *
 * @author Bruce Dong
 */
public class DoubleLinkedListIterator<T> implements ListIterator<T> {

  // The front pointer of a node
  private DLNode<T> front;

  // The back pointer of a node
  private DLNode<T> back;

  // The node that is returned by next() or previous()
  private DLNode<T> returned=null;

  // a counter to see how many times next() or previous() is called
  private int count = 0;

  // a counter to see how many times next() or previous() is called (before the last time) add one when remove() is called
  private int oldCount = 0;

  // get the DLinked list
  private DoubleLinkedList<T> list;



  /**
   * Create an list iterator that starts at the input node
   * @param front the node where the iteration of the list begins
   * @param back the node where the iteration of the list begins
   * @param list the list that we want to add to the iterator 
   */
  public DoubleLinkedListIterator(DLNode<T> front, DLNode<T> back, DoubleLinkedList<T> list) {
    this.back = back;
    this.front = front;
    this.list = list;
  }

  /**
   * add an node in the list
   * @param element the node that we want to add
   */
  public void add(T element){
    if(front == null){
      front = new DLNode<T> (element, front, back);
      front.getNext().setPrevious(front);
      list.setFront(front);
      return;
    }
    
    if(back == null){
      back = new DLNode<T> (element, front, back);
      back.getPrevious().setNext(back);
      list.setBack(back);
      return;
    }
    
    front = new DLNode<T> (element, front, back);
    front.getPrevious().setNext(front);
    front.getNext().setPrevious(front);
  }

  /**
   * return whether there is one more node at the next
   * @return true if there is one more node at next
   */
  public boolean hasNext(){
    return back != null;
  }

  /**
   * return whether there is one more node at previous
   * @return true if there is one more node at previous
   */
  public boolean hasPrevious(){
    return front != null;
  }

  /**
   * return the next node in the list
   * @return the next node in the list
   */
  public T next() throws NoSuchElementException{
    if( back == null)
      throw new NoSuchElementException();
    
    front = back;
    back = front.getNext();
    returned = front;
    oldCount = count;
    ++count;
    return front.getElement();
  }

  /**
   * return the next index in the list
   * @return the next index in the list
   */
  public int nextIndex() throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }

  /**
   * Returns the element in front of the cursor
   * @return the element in front of the cursor
   */
  public T previous() throws NoSuchElementException{
    if( front == null)
      throw new NoSuchElementException();
    back = front;
    front = back.getPrevious();
    returned = back;
    oldCount = count;
    ++count;
    return back.getElement();
  }

  /**
   * Returns the the previous index
   * @return the the previous index
   */
  public int previousIndex() throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }
 

  /**
   * remove the element in front of the cursor
   * @throws NoSuchElementException if there are no more elements when next is called
   */
  public void remove() throws IllegalStateException {
    if(oldCount == count)
      throw new IllegalStateException();
    
    if( returned.getPrevious() == null){
      front = null;
      back = returned.getNext();
      returned.getNext().setPrevious(returned.getPrevious());
      list.setFront(returned.getNext());
      return ;
    }
    
    if( returned.getNext() == null){
      front = returned.getPrevious();
      back = returned.getNext();
      returned.getPrevious().setNext(returned.getNext());
      list.setBack(returned.getPrevious());
      return ;
    }

    ++oldCount;
    returned.getPrevious().setNext(returned.getNext());
    returned.getNext().setPrevious(returned.getPrevious());
    front = returned.getPrevious();
    back = returned.getNext();
  }

  /**
   * Replaces the last element returned by next() or previous() with the specified element
   * @param element the element that we want to set
   */
  public void set(T element) throws UnsupportedOperationException{
    throw new UnsupportedOperationException();
  }


}
  