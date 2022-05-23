package cs228hw2.test;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
/**
 * 
 * @author chimzim Ogbondah
 *Node class that implements the list and list iterator interfaces
 * @param <E>
 */
public class AmusingLinkedList<E> implements List<E> {
	/**
	 * Head - Dummy node that points to the first node
	 * root - As roots grow so will this node, this temporary node will grow the linked list
	 * end - A node pointer that keeps track of where the first node is held
	 * index - Keeps track of the amount of items inside the linked list
	 */
	private Node head = null, root = null, end = null;
	private int index = 0;
	public class Node {
		/**
		 * prev - A node that will point to the node before it
		 * next - A node that will point to the node next to it
		 * myData - Holds the data for the given node
		 */
		private Node prev, next;
		private E myData;
		/**
		 * Constructs a new Node object with two nodes points to the next and previous and a given data field
		 * @param p - the previous node
		 * @param n - the next node
		 * @param data - the data for the current node
		 */
		public Node(Node p, Node n, E data) {
			prev = p;
			next = n;
			myData = data;
		}
		/**
		 * returns the data for the given Node
		 * @return myData - which holds the data for the given node
		 */
		public E getData() {
			return myData;
		}
		/**
		 * WIll return the node after itself
		 * @return next - Pointer node that holds the next node
		 */
		public Node getNext() {
			return next;
		}
		/**
		 * Returns the node after it
		 * @return prev - While all nodes are linked together this method only works for even indexes which point to the last even index
		 */
		public Node getPrev() {
			if(index % 2 == 0) {
				return prev;
			}
			return null;
		}
	}
	
	@Override
	/**
	 * This adds a new node to the linked list and then returns if true or false if the node was successfully added. 
	 * The method checks to see if the the dummy node (Head) is equal to not. If it is then the linked list is just being 
	 * constructed. If it isn't then it checks to see whether the index is odd or even. If the index is odd then it links both previous and next 
	 * However if it is even the the previous is the last previous index.
	 */
	public boolean add(E e) {
		// TODO Auto-generated method stub
		if(head.next == null) {
			end = new Node(null, null, e);
			head = new Node(end, end, null);
			end.next = end;
			end.prev = end;
			index++;
			return true;
		}
		else {
			if(index % 2 == 0) {
				root = end.prev;
				root.next = new Node(root.prev, end, e);
				index++;
				return true;
			}
			else {
				root = end.prev;
				root = root.next;
				root.next = new Node(root.prev.prev,end,e);
				index++;
				return true;
			}
		}
		
	}
	
	public String toString() {
		String myNode = "";
		Node string = end;
		for(int i = 0; i <= index; i++) {
			myNode += i;
			myNode += " ";
			if(getNodeAtIndex(i).getPrev().equals(null)) {
				myNode += -1;
			}
			else if (i == 0 && index % 2 == 1){
				myNode += indexOf(getNodeAtIndex(i).prev.prev);
			} 
			else { 
				myNode += indexOf(getNodeAtIndex(i).prev);
			}
			myNode += " ";
			myNode += indexOf(getNodeAtIndex(i).next);
			myNode += " ";
			if(string.myData.equals(null)) {
				myNode += "null";
			}
			else {
				myNode += string.myData;
			}
			myNode += "\n";
			string = string.next;
		}
		return myNode;
	}

	@Override
	/**
	 * Adds a new Node variable into the linked list at the given index. If the index is less than zero or greater than the 
	 * size of the current linked list it throw an IndexOutBoundsException exception. Else it creates a pointer node that 
	 * iterates to the index to be added at. From there it adds the data to the node at that index. Using a simple 3 variable switch
	 * it moves the data down the list until the end is met, then it calls the add method that takes on a data parameter.
	 * MyNode - pointer Node
	 * temp - holds the data of the node that will have its data updated
	 * newData - gets the data from the first and temp variable and adds it to the next node
	 */
	public void add(int idx, E e) {
		// TODO Auto-generated method stub
		Node myNode = end;
		E temp = null;
		E newData = null;
		int found = 0;
		if(idx > size() || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		for(int i = 0; i <= index; i++) {
			if(i == index) {
				newData = myNode.myData;
				myNode.myData = e;
				found = 1;
			}
			else if(found == 1) {
				temp = myNode.myData;
				myNode.myData = newData;
				newData = temp;
			}
			myNode = myNode.next;
		}
		add(newData);
	}

	@Override
	/**
	 * loops through the collection of items, it calls the add method with the data parameter and adds all the items from the 
	 * collection in order. Returns true once successful.
	 */
	public boolean addAll(Collection<? extends E> items) {
		// TODO Auto-generated method stub
		for(E e: items) {
			add(e);
		}
		return true;
	}

	@Override
	/**
	 * Adds all the items from the collection in order. It iterates through all the items in the collection and then uses the 
	 * add at an index for the collection. To ensure the collection stays in the right order the next index to be added at gets updated 
	 * by one after every successful addition to the linked list.
	 */
	public boolean addAll(int idx, Collection<? extends E> items) {
		// TODO Auto-generated method stub
		for(E e: items) {
			add(idx, e);
			idx++;
		}
		return true;
	}

	@Override
	/**
	 * Clears the entire linked list. Goes through and sets the first node equal to the next one until there is no more.
	 */
	public void clear() {
		// TODO Auto-generated method stub
		for(int i = 0; i <= index; i++) {
			end = end.next;
		}
	}

	@Override
	/**
	 * uses a pointer node to iterate through the list check every node against the given object unless the node is found. In that
	 * case it breaks from the while loop returning true, if the object is never found it returns false
	 * myNode - Pointer Node
	 * tempCounter - allows the loop to realize when it is at the end
	 */
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		Node myNode = end;
		int tempCounter = 0;
		while(tempCounter != index) {
			if(myNode.equals(o)) {
				return true;
			}
			else { 
				myNode = myNode.next;
				tempCounter++;
			}
		}
		return false;
	}

	@Override
	/**
	 * Checks to see if the collection of items is found inside the linked list. Loops through the collection of items and then 
	 * calls the contains method to see if it is found inside. Has a check to see if just one isn't found. At that point it returns false.
	 * If not it finds all the returns true.
	 */
	public boolean containsAll(Collection<?> items) {
		// TODO Auto-generated method stub
		for(Object e: items) {
			if(!contains(e)) {
				return false;
			}
		}
		return true;
	}

	@Override
	/**
	 * Returns the data at the desired index. If the desired index is less than zero or greater than the size of the linked list it throws an
	 * IndexOutOfBoundsException exception. Else it uses a pointer Node to loop through the linked list until it gets to the desired index at which case 
	 * it returns the data of that Node.
	 * myNode - Pointer node
	 */
	public E get(int i) {
		// TODO Auto-generated method stub
		Node myNode = end;
		if(i > index || i < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			for(int j = 0; j <= i; i++) {
				myNode = myNode.next;
			}
		}
		return myNode.myData;
	}

	@Override
	/**
	 * returns the index at which the object is found. Using a pointer node it loops through the linked list checking to see if the object is found
	 * if the object is found then it returns the iterator variable i. In the case the object is not found it returns -1;
	 */
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		Node myNode = end;
		for(int i = 0; i <= index; i++) {
			if(myNode.equals(o)) {
				return i;
			}
			else {
				myNode = myNode.next;
			}
		}
		return -1;
	}

	@Override
	/**
	 * Checks to see if the linked list is empty. if End equals null the it returns true for and emtty list other wise false
	 * end - the beginning of the linked list
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return end == null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * Returns the last instance of the object inside the linked list. Iterates through the linked list updating the index of the object each time a new 
	 * instance of the object. 
	 * idx - int variable to keep track of the object inside the linked list. keeps updating after each occurance
	 */
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		int i, idx = 0;
		Node myNode = end;
		for(i = 0; i <= index; i++) {
			if(myNode.equals(o)) {
				idx = i;
				myNode = myNode.next;
			}
			myNode = myNode.next;
		}
		return idx;
	}

	@Override
	/**
	 * returns a new AmusingListIterator variable
	 */
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return new AmusingListIterator();
	}

	@Override
	/**
	 * returns a new AmusingListIterator at a given index in the linked list
	 */
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return new AmusingListIterator(arg0);
	}

	@Override
	/**
	 * removes the first instance of the object found in the linked list. Returns true if it was removed false if it wasn't.
	 * A pointer node is used which iterates through the linked list checking to see if the object is contained. If the object is
	 * found then after all the data from the the index after is moved back one until the end is reach at which point the next and previous variables are set to null
	 * so the object can no longer be found then the size is decremented.
	 * myNode - Pointer node
	 * temp - holds the data of the next node in the link
	 * fixer = fixes the indexes of the previous variables
	 */
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		Node myNode = end;
		E temp = null;
		int fixer = 0;
		for(int i = 0; i <= index; i++) {
			if(fixer == 0) {
				if(myNode.equals(o)) {
					temp = myNode.next.myData;
					myNode.myData = temp;
					fixer = 1;
				}
				myNode = myNode.next;
			}
			else if (fixer == 1){
				myNode = myNode.next;
				myNode.myData = myNode.next.myData;
			}
			else if(i == index) {
				end.prev = myNode;
				myNode.next = null;
				myNode.prev = null;
			}
		}
		return temp == o;
	}

	@Override
	/**
	 *Removes the node at the given index. Loops through the linked linked list to get to the index. From the point it moves all the data back one 
	 *until it gets to the end then makes the last node.next and .prev equal to null so it can no longer be found.
	 *temp1 - uses a simple swtich to move data back with myNode
	 *temp2 - used in simple switch to move data back with myNode
	 *myNode - pointer node
	 */
	public E remove(int index) {
		// TODO Auto-generated method stub
		if(index > this.index || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node myNode = end;
	//	Node temp2 = getNodeAtIndex(index);
		E temp = null;
		int fixer = 0;
		for(int i = 0; i <= this.index; i++) {
			if(fixer == 0) {
				if(i == index) {
					temp = myNode.next.myData;
					myNode.myData = temp;
					fixer = 1;
				}
				myNode = myNode.next;
			}
			else if (fixer == 1){
				myNode = myNode.next;
				myNode.myData = myNode.next.myData;
			}
			else if(i == this.index) {
				end.prev = myNode;
				myNode.next = null;
				myNode.prev = null;
			}
		}
		return temp;
	}
	/**
	 * 
	 * @param idx the index of the node to return
	 * @return the node variable at the given index
	 */
	public Node getNodeAtIndex(int idx) {
		if(idx > index  || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node myNode = end;
		for(int i = 0; i <= index; i++) {
			if(idx == i) {
				break;
			}
			myNode = myNode.next;
		}
		return myNode;
	}

	@Override
	/**
	 * Loops through the collection and calls the remove method to remove the given node in the collection 
	 * once the all items have been removed from the collection the method returns true
	 */
	public boolean removeAll(Collection<?> items) {
		// TODO Auto-generated method stub
		for(Object e: items) {
			remove(e);
		}
		return true;
	}

	@Override
	/**
	 * Only keeps the nodes listed inside the collection. loops through the linked list and if the variable isn't equal to the collection
	 * the remove method at index method is called
	 */
	public boolean retainAll(Collection<?> keepList) {
		// TODO Auto-generated method stub
		for(int i = 0; i < index; i++) {
			for(Object e: keepList) {
				if(!getNodeAtIndex(i).equals(e)) {
					remove(i);
				}
			}
		}
		
		return true;
	}

	@Override
	/**
	 * Sets the data at the given index with the data parameter. If the index is greater than the size or less than zero it throws and 
	 * IndexOutOfBoundException exception. Else it uses a pointer node to find the node at the index. saves the old data to be returned and then sets the new 
	 * data
	 * oldData- Holds the data of the node pre reset
	 * Returns oldData
	 */
	public E set(int idx, E newData) {
		// TODO Auto-generated method stub
		if(idx > index || idx < - 1) {
			throw new IndexOutOfBoundsException();
		}
		Node myNode = end;
		E oldData = null;
		for(int i = 0; i <= index; i++) {
			if(i == idx) {
				oldData = myNode.myData;
				myNode.myData = newData;
			}
			myNode = myNode.next;
		}
		return oldData;
	}

	@Override
	/**
	 * returns the size of the linked list(the amount of objects)
	 */
	public int size() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		Node myNode = end;
		Object[] temp = new Object[index];
		// TODO Auto-generated method stub
		for(int i = 0; i <= index; i++) {
			temp[i] = myNode.myData;
			myNode = myNode.next;
		}
		return temp;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public class AmusingListIterator implements ListIterator<E> {
		Node iter = end;
		int iterNumb = 0;

		public AmusingListIterator(int idx) {
			AmusingListIterator E = new AmusingListIterator();
		}
		
		public AmusingListIterator() {
			//AmusingListIterator<E> E = new AmusingListIterator<E>();
		}

		@Override
		/**
		 * checks to see if the node has a next value. Returns true if it does and false if the value is null or the size of the linked list 
		 */
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(iter.myData.equals(end.prev) || iter.next == iter|| end == null) {
				return false;
			}
			else {
				return true;
				
			}
		}

		@Override
		/**
		 * checks to see if the variable has a previous node. Returns false if the node is equal to null or if it is at the beginning 
		 * else it returns true
		 */
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			if(iter.myData.equals(end)|| end == null) {
				return false;
			}
			else {
				return true;
			}
		}

		@Override
		/**
		 * returns the data of the next node based on the iterator number. Uses getNodeAtIndex to retrive the node
		 */
		public E next() {
			// TODO Auto-generated method stub
			iterNumb++;
			return getNodeAtIndex(iterNumb).myData;
		}

		@Override
		/**
		 * returns the index of the next index
		 */
		public int nextIndex() {
			// TODO Auto-generated method stub
			return index+1;
		}

		@Override
		/**
		 * returns the previous node based on the iterator number. Uses the getNodeAtIndex to return the node object
		 */
		public E previous() {
			// TODO Auto-generated method stub
			iterNumb--;
			return getNodeAtIndex(iterNumb).myData;
		}

		@Override
		/**
		 * returns the index of the previous node
		 */
		public int previousIndex() {
			// TODO Auto-generated method stub
			return index-1;
		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
