import tester.Tester;
import java.util.function.Predicate;

//abstract class
abstract class ANode<T> {
  ANode<T> next;
  ANode<T> prev;

  ANode(ANode<T> next, ANode<T> prev) {
    this.next = next;
    this.prev = prev;
  }

  ANode() {
    this.next = this;
    this.prev = this;
  }

  //Initializes the previous node to the given node
  public void initNodePrev(ANode<T> t) {
    this.prev = t;
  } 

  //Initializes the next node to the given node
  public void initNodeNext(ANode<T> t) {
    this.next = t;
  }

  //removes this node - between next and prev
  abstract public T remove();

  //maps the given function onto every member of this list, returning a list of
  // the results
  abstract ANode<T> findHelper(Predicate<T> pred);

  //checks the size of the node or sentinel
  abstract public int sizeHelp();
}

//class for node
class Node<T> extends ANode<T> {
  T data;

  Node(T data) {
    super(null, null);
    this.data = data;
  }

  Node(T data, ANode<T> next, ANode<T> prev) {
    super(next, prev);
    this.data = data;
    if ((next == null) || (prev == null)) {
      throw new IllegalArgumentException("Node value cannot be null");
    }
    else {
      this.next.initNodePrev(this);
      this.prev.initNodeNext(this);
    }
  }

  //checks the size of the node or sentinel
  public int sizeHelp() {
    return 1 + this.next.sizeHelp();
  }

  //removes this node - between next and prev
  public T remove()  {
    this.prev.initNodeNext(this.next);
    this.next.initNodePrev(this.prev);
    return this.data;
  } 

  //maps the given function onto every member of this list, returning a list of
  // the results
  public ANode<T> findHelper(Predicate<T> pred) {
    if (pred.test(this.data)) {
      return this;
    }
    else {
      return
          this.next.findHelper(pred);
    }      
  }
}

//class for sentinel
class Sentinel<T> extends ANode<T> {
  Sentinel() {
    super();
  }

  //checks the size of the node or sentinel
  public int sizeHelp() {
    return 0;
  }

  //adds a node between the sentinel and it's original next
  void addAtHeadHelper(T t) {
    new Node<T>(t, this.next, this);
  }

  //adds a node between the sentinel and it's original prev
  void addAtTailHelper(T t) {
    new Node<T>(t, this, this.prev);  
  }

  //removes the node from the node class
  public T remove()  {
    throw new RuntimeException("Cannot remove from an empty list");
  } 

  //removes the node between the sentinel and it's original next
  public T removeFromHead() {
    return this.next.remove(); 
  }

  //removes the node between the sentinel and it's original prev
  public T removeFromTail() {
    return this.prev.remove();
  }

  //returns the sentinel because none of the nodes satisfied the predicate
  ANode<T> findHelper(Predicate<T> pred) {
    return this;
  }

  //finds the first node that satisfies the predicate
  ANode<T> find(Predicate<T> pred) {
    return this.next.findHelper(pred);
  }
}

//class for a deque
class Deque<T> {
  Sentinel<T> header;

  Deque() {
    this.header = new Sentinel<T>();
  }

  Deque(Sentinel<T> header) {
    this.header = header;
  }

  //returns the size of the deque
  public int size() {
    return this.header.next.sizeHelp();
  }

  //inserts given value of type T in front of the list
  public void addAtHead(T t) {
    this.header.addAtHeadHelper(t);
  }

  //inserts given value of type T in front of the list
  public void addAtTail(T t) {
    this.header.addAtTailHelper(t);
  }

  //removes the node between the sentinel and it's original next
  public T removeFromHead() {
    return this.header.next.remove(); 
  }

  //removes the node between the sentinel and it's original prev
  public T removeFromTail() {
    return this.header.prev.remove();
  }

  //finds the first node that satisfies the predicate
  public ANode<T> find(Predicate<T> pred) {
    return this.header.next.findHelper(pred);
  }
}  

//function object class used for testing of find
class StartsWithA implements Predicate<String> {

  //determines if a string starts with an "a"
  public boolean test(String str) {
    return str.toLowerCase().substring(0,1).equals("a");
  }  
}

//function object class used for testing of find
class StartsWithZ implements Predicate<String> {

  //determines if a string starts with an "a"
  public boolean test(String str) {
    return str.toLowerCase().substring(0,1).equals("z");
  }  
}

//examples of deque
class ExamplesDeques {

  Deque<String> deque1;
  Deque<String> deque2;
  Deque<Integer> deque3;

  Sentinel<String> sentinel1;  
  Sentinel<String> sentinel2;
  Sentinel<Integer> sentinel3;

  ANode<String> node1;
  ANode<String> node2;
  ANode<String> node3;
  ANode<String> node4;
  ANode<Integer> node5;
  ANode<Integer> node6;
  ANode<Integer> node7;
  ANode<Integer> node8;

  // Node<String> node5;

  void init() { // are these right
    this.sentinel1 = new Sentinel<String>();
    this.sentinel2 = new Sentinel<String>();   

    this.node1 = new Node<String>("abc", sentinel2, sentinel2);
    this.node2 = new Node<String>("bcd", sentinel2, node1);
    this.node3 = new Node<String>("cde", sentinel2, node2);
    this.node4 = new Node<String>("def", sentinel2, node3);

    this.sentinel3 = new Sentinel<Integer>();
    this.node5 = new Node<Integer>(1, sentinel3, sentinel3);
    this.node6 = new Node<Integer>(3, sentinel3, node5);
    this.node7 = new Node<Integer>(2, sentinel3, node6);
    this.node8 = new Node<Integer>(4, sentinel3, node7);

    this.deque1 = new Deque<String>(sentinel1);
    this.deque2 = new Deque<String>(sentinel2);
    this.deque3 = new Deque<Integer>(sentinel3);
  }

  //tests the add at head method
  void testAddAtHead(Tester t) {
    this.init();
    t.checkExpect(this.deque1.header, sentinel1);
    t.checkExpect(this.deque2.header, node1.prev);
    t.checkExpect(this.deque3.header, node5.prev);

    this.deque1.addAtHead("abc");
    this.deque2.addAtHead("abc");
    this.deque3.addAtHead(1);
    t.checkExpect(this.deque1.header, sentinel1);
    t.checkExpect(this.deque2.header, sentinel2);
    t.checkExpect(this.deque3.header, sentinel3);
  }

  //tests the remove method
  void testRemove(Tester t) { 
    this.init();

    t.checkException(new RuntimeException("Cannot remove from an empty list"), 
        sentinel1, "remove");

    //before we remove node 2
    t.checkExpect(this.node2.next, node3);
    t.checkExpect(this.node2.prev, node1);

    //after we remove node 2
    this.node2.remove();
    t.checkExpect(this.node1.next, node3);
    t.checkExpect(this.node3.prev, node1);
  }

  //tests the remove at tail method
  void testRemoveAtTail(Tester t) {
    this.init();
    t.checkExpect(this.deque2.header, node4.next);          
    t.checkExpect(this.deque3.header, node8.next);

    this.deque2.removeFromTail();
    this.deque3.removeFromTail();

    t.checkExpect(this.deque2.header, node3.next);          
    t.checkExpect(this.deque3.header, node7.next);        
  }

  //tests the remove at head method
  void testRemoveAtHead(Tester t ) {
    this.init();
    t.checkExpect(this.deque2.header, node1.prev);
    t.checkExpect(this.deque3.header, node5.prev);  

    this.deque2.removeFromHead();
    this.deque3.removeFromHead();
    t.checkExpect(this.deque2.header, node2.prev);
    t.checkExpect(this.deque3.header, node6.prev);       
  }

  //tests the init node prev method
  void testinitNodePrev(Tester t) {
    this.init();

    this.node2.initNodePrev(node1);
    t.checkExpect(this.node2.prev, node1);

    this.node3.initNodePrev(node1);
    t.checkExpect(this.node3.prev, node1);

    this.sentinel1.initNodePrev(node1);
    t.checkExpect(this.sentinel1.prev, node1);

    this.sentinel1.initNodePrev(sentinel1);
    t.checkExpect(this.sentinel1.prev, sentinel1);

    this.node1.initNodePrev(sentinel1);
    t.checkExpect(this.node1.prev, sentinel1);
  }

  //tests the init node next method
  void testInitNodeNext(Tester t) {
    this.init();

    this.node1.initNodeNext(node2);
    t.checkExpect(this.node1.next, node2);

    this.node3.initNodeNext(node1);
    t.checkExpect(this.node3.next, node1);

    this.sentinel1.initNodeNext(node1);
    t.checkExpect(this.sentinel1.next, node1);

    this.sentinel1.initNodeNext(sentinel1);
    t.checkExpect(this.sentinel1.next, sentinel1);

    this.node1.initNodeNext(sentinel1); 
    t.checkExpect(this.node1.next, sentinel1); 
  }

  //tests the size method
  boolean testSize(Tester t) {
    this.init();

    return 
        t.checkExpect(this.deque3.size(), 4)
        &&
        t.checkExpect(this.deque1.size(), 0)
        &&
        t.checkExpect(this.deque2.size(), 4);
  }

  //tests the find helper
  boolean testFindHelper(Tester t) {
    this.init();

    return 
        t.checkExpect(this.node1.findHelper(new StartsWithA()), node1)
        &&
        t.checkExpect(this.node2.findHelper(new StartsWithA()), sentinel2)
        &&
        t.checkExpect(this.node2.findHelper(new StartsWithZ()), deque2.header) 
        &&
        t.checkExpect(this.sentinel1.findHelper(new StartsWithA()), sentinel1);
  }  

  //tests the add at head helper
  void testAddAtHeadHelper(Tester t) {
    this.init();

    t.checkExpect(this.sentinel2.next, node1);

    sentinel2.addAtHeadHelper("hij");
    t.checkExpect(this.sentinel2.next, new Node<String>("hij", node1, sentinel2));

    sentinel1.addAtHeadHelper("plop");
    t.checkExpect(this.sentinel1.next, new Node<String>("plop", sentinel1, sentinel1));
  }

  //tests the add at tail helper
  void testAddAtTailHelper(Tester t) {
    this.init();

    t.checkExpect(this.sentinel2.prev, node4);

    sentinel2.addAtTailHelper("hij");
    t.checkExpect(this.sentinel2.prev, new Node<String>("hij", sentinel2, node4));

    sentinel1.addAtTailHelper("plop");
    t.checkExpect(this.sentinel1.prev, new Node<String>("plop", sentinel1, sentinel1));
  }

  //tests size helper
  boolean testSizeHelp(Tester t) {
    this.init();

    return 
        t.checkExpect(this.node1.sizeHelp(), 4) 
        &&
        t.checkExpect(this.sentinel2.sizeHelp(), 0)
        &&
        t.checkExpect(this.node8.sizeHelp(), 1);
  }
  
  //tests the constructor exception for the node class
  boolean testConstructorExceptions(Tester t) {
    this.init();

    return
        t.checkConstructorException(
            new IllegalArgumentException("Node value cannot be null"),
            "Node", "abc", null, null)
        &&
        t.checkConstructorException(
            new IllegalArgumentException("Node value cannot be null"),
            "Node", "abc", null, node4)
        &&
        t.checkConstructorException(
            new IllegalArgumentException("Node value cannot be null"),
            "Node", "abc", node4, null);
  }
}

