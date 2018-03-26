//Kevin Lin
//APCS2 Pd1
//HW25 -- Generically Speaking...
//2018-03-27

public class LList<T> implements List<T> //your List interface must be in same dir
{

  //instance vars
  private DLLNode<T> _head;
  private DLLNode<T> _tail;
  private int _size;

  // constructor -- initializes instance vars
  public LList( )
  {
    _head = null; //at birth, a list has no elements
    _size = 0;
  }


  //--------------v  List interface methods  v--------------

  public boolean add( T newVal )
  {
    DLLNode<T> tmp = new DLLNode<T>( newVal, null, _tail );
    if (_tail != null) {
      _tail.setNext(tmp);
      _tail = tmp;
      _size++;
      return true;
    }
    _head = tmp;
    _tail = tmp;
    return true;
  }


  public T get( int index )
  {
    if ( index < 0 || index >= size() )
    throw new IndexOutOfBoundsException();

    T retVal;
    DLLNode<T> tmp = _head; //create alias to head

    //walk to desired node
    for( int i=0; i < index; i++ )
    tmp = tmp.getNext();

    //check target node's cargo hold
    retVal = tmp.getCargo();
    return retVal;
  }


  public T set( int index, T newVal )
  {

    if ( index < 0 || index >= size() )
    throw new IndexOutOfBoundsException();

    DLLNode<T> tmp = _head; //create alias to head

    //walk to desired node
    for( int i=0; i < index; i++ )
    tmp = tmp.getNext();

    //store target node's cargo
    T oldVal = tmp.getCargo();

    //modify target node's cargo
    tmp.setCargo( newVal );

    return oldVal;
  }


  //return number of nodes in list
  public int size() { return _size; }


  //insert a node containing newVal at position index
  public void add( int index, T newVal ) {

    if ( index < 0 || index >= size() )
    throw new IndexOutOfBoundsException();

    DLLNode<T> newNode = new DLLNode<T>( newVal, null, null );

    //if index==0, insert node before head node
    if ( index == 0 ) {
      newNode.setNext(_head);
      _head.setPrev(newNode);
      _head = newNode;
      _size++;
    }
    else {
      DLLNode<T> tmp = _head; //create alias to head

      //walk to node before desired node
      for( int i=0; i < index-1; i++ )
      tmp = tmp.getNext();

      //insert new node
      newNode.setNext( tmp.getNext() );
      newNode.getNext().setPrev(newNode);
      newNode.setPrev( tmp );
      tmp.setNext( newNode );

      //increment size attribute
      _size++;
    }
  }


  //remove node at pos index, return its cargo
  public T remove( int index ) {

    if ( index < 0 || index >= size() )
    throw new IndexOutOfBoundsException();

    T retVal;
    DLLNode<T> tmp = _head; //create alias to head

    //if index==0, remove head node
    if ( index == 0 ) {
      //check target node's cargo hold
      retVal = _head.getCargo();

      //remove target node
      _head = _head.getNext();
      _head.setPrev(null);
    }
    else {
      //walk to node before desired node
      for( int i=0; i < index-1; i++ )
      tmp = tmp.getNext();

      //check target node's cargo hold
      retVal = tmp.getNext().getCargo();

      //remove target node
      tmp.setNext( tmp.getNext().getNext() );
      tmp.getNext().setPrev(tmp);
    }

    //decrement size attribute
    _size--;

    return retVal;
  }

  //--------------^  List interface methods  ^--------------


  // override inherited toString
  public String toString()
  {
    String retStr = "HEAD->";
    DLLNode<T> tmp = _head; //init tr
    while( tmp != null ) {
      retStr += tmp.getCargo() + "->";
      tmp = tmp.getNext();
    }
    retStr += "NULL";
    return retStr;
  }


  //main method for testing
  public static void main( String[] args )
  {

    LList james = new LList();

    System.out.println( james );
    System.out.println( "size: " + james.size() );

    james.add("beat");
    System.out.println( james );
    System.out.println( "size: " + james.size() );

    james.add("a");
    System.out.println( james );
    System.out.println( "size: " + james.size() );

    james.add("need");
    System.out.println( james );
    System.out.println( "size: " + james.size() );

    james.add("I");
    System.out.println( james );
    System.out.println( "size: " + james.size() );

    System.out.println( "2nd item is: " + james.get(1) );

    System.out.println( "...and now 2nd item is: " + james.set(1,"got") );
    System.out.println( james );

    james.add(0,"whut");
    System.out.println( "...after add(0,whut): " );
    System.out.println( james );

    james.add(4,"phat");
    System.out.println( "...after add(4,phat): " );
    System.out.println( james );

    System.out.println( "...after remove last: "
    + james.remove( james._size-1) );
    System.out.println( james );

    System.out.println( "...after remove(0): " + james.remove(0) );
    System.out.println( james );

    System.out.println( "...after remove(0): " + james.remove(0) );
    System.out.println( james );

    System.out.println( "...after remove(0): " + james.remove(0) );
    System.out.println( james );
  }

}//end class LList
