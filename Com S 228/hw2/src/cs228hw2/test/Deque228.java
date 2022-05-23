package cs228hw2.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
/**
 * Has A relationship implementation for the post fix calculator
 * @author chimzim Ogbondah
 *
 * @param <E>
 */
public class Deque228<E> implements Deque<E>{
	/**
	 * Calculator - ArrayList used to implement the Deque interface
	 */
	ArrayList<E> Calculator = new ArrayList<E>();
	@Override
	/**
	 * Adds all the items in the collection to the arraylist
	 */
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		for(E e: c) {
			Calculator.add(e);
		}
		return true;
	}

	@Override
	/**
	 * Clears every item inside the array list
	 */
	public void clear() {
		// TODO Auto-generated method stub
		Calculator.clear();		
	}

	@Override
	/**
	 * Checks to see if everything in the collection is contained inside of the array list
	 */
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Calculator.size(); i++) {
			for(Object e: c) {
				if(!contains(e)) {
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	/**
	 * Checks to see if the array list contains any items, then returns true or false
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return Calculator.size() == 0;
	}

	@Override
	/**
	 * Removes all the items from the collection in the array list, upon doing so successfully it returns true 
	 */
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		Calculator.removeAll(c);
		return true;
	}

	@Override
	/**
	 * retains all the items inside the collection in the array list
	 */
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		Calculator.retainAll(c);
		return true;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * adds and object at the end of the list with the given data
	 */
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		Calculator.add(arg0);
		return true;
	}

	@Override
	/**
	 * adds and object at the beginning of the list with the given data
	 */
	public void addFirst(E arg0) {
		// TODO Auto-generated method stub
		Calculator.add(0, arg0);
		
	}

	@Override
	/**
	 * adds and object at the end of the list with the given data
	 */
	public void addLast(E arg0) {
		// TODO Auto-generated method stub
		Calculator.add(Calculator.size()-1, arg0);
	}

	@Override
	/**
	 * checks to see if the object is found inside the array list, then returns true or false
	 */
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Calculator.size(); i++) {
			if(Calculator.get(i).equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * returns the first item in the list
	 */
	public E element() {
		// TODO Auto-generated method stub
		return Calculator.get(0);
	}

	@Override
	/**
	 * returns the first item in the list
	 */
	public E getFirst() {
		// TODO Auto-generated method stub
		return Calculator.get(0);
	}

	@Override
	/**
	 * returns the last item in the list
	 */
	public E getLast() {
		// TODO Auto-generated method stub
		return Calculator.get(Calculator.size()-1);
	}

	@Override
	/** 
	 * returns an iterator for the array list
	 */
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return Calculator.iterator();
	}

	@Override
	/**
	 * adds the data point to the end of the list
	 */
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		Calculator.add(arg0);
		return true;
	}

	@Override
	/**
	 * adds the data at the beginning of the list
	 */
	public boolean offerFirst(E arg0) {
		// TODO Auto-generated method stub
		Calculator.add(0, arg0);
		return true;
	}

	@Override
	/**
	 * adds the data to the end of the array list the returns true
	 */
	public boolean offerLast(E arg0) {
		// TODO Auto-generated method stub
		Calculator.add(arg0);
		return true;
	}

	@Override
	/**
	 * returns the first item in the list, if the list contains no elements returns null
	 */
	public E peek() {
		// TODO Auto-generated method stub
		if(Calculator.size() == 0) {
			return null;
		}
		else {
			return Calculator.get(0);
		}
	}

	@Override
	/**
	 * returns the first item in the list, if the list contains no elements returns null
	 */
	public E peekFirst() {
		// TODO Auto-generated method stub
		if(Calculator.size() == 0) {
			return null;
		}
		else {
			return Calculator.get(0);
		}
	}

	@Override
	/**
	 * returns the last item in the list, if the list contains no elements returns null
	 */
	public E peekLast() {
		// TODO Auto-generated method stub
		if(Calculator.size() == 0) {
			return null;
		}
		else {
			return Calculator.get(Calculator.size()-1);
		}
	}

	@Override
	/**
	 * returns the first item in the list, if the list contains no elements returns null, then returns the deleted item 
	 */
	public E poll() {
		// TODO Auto-generated method stub
		if(Calculator.size() == 0) {
			return null;
		}
		else {
			E temp = Calculator.get(0);
			Calculator.remove(0);
			return temp;
		}
	}

	@Override
	/**
	 * returns the first item in the list, if the list contains no elements returns null, then returns the deleted item 
	 */
	public E pollFirst() {
		// TODO Auto-generated method stub
		if(Calculator.size() == 0) {
			return null;
		}
		else {
			E temp = Calculator.get(0);
			Calculator.remove(0);
			return temp;
		}
	}

	@Override
	/**
	 * returns the last item in the list, if the list contains no elements returns null, then returns the deleted item 
	 */
	public E pollLast() {
		// TODO Auto-generated method stub
		if(Calculator.size() == 0) {
			return null;
		}
		else {
			E temp = Calculator.get(Calculator.size()-1);
			Calculator.remove(Calculator.size()-1);
			return temp;
		}
	}

	@Override
	/**
	 * grabs the first item in the list then removes it and returns it
	 */
	public E pop() {
		// TODO Auto-generated method stub
		E temp = Calculator.get(0);
		Calculator.remove(0);
		return temp;
	}

	@Override
	/**
	 * adds the given item to the beginning of the list
	 */
	public void push(E arg0) {
		// TODO Auto-generated method stub
		Calculator.add(0,arg0);
		
	}

	@Override
	/**
	 * removes the first item in the list and then returns it
	 */
	public E remove() {
		// TODO Auto-generated method stub
		E tempData = Calculator.get(0);
		Calculator.remove(0);
		return tempData;
	}

	@Override
	/**
	 * removed the first instance of the item in the array list 
	 */
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Calculator.size(); i++) {
			if(Calculator.get(i).equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	/**
	 * removed the first item in the array list and the returns the data element
	 * tempData - data that was just removed
	 */
	public E removeFirst() {
		// TODO Auto-generated method stub
		E tempData = Calculator.get(0);
		Calculator.remove(0);
		return tempData;
	}

	@Override
	/**
	 * Removes the first occurance of the object by going through the array finding the first then returning it
	 */
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		for(int i = 0; i < Calculator.size(); i++) {
			if(Calculator.get(i).equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	/** 
	 * removes and returns the last item inside of the array list
	 * tempData - the item that was just removed
	 */
	public E removeLast() {
		// TODO Auto-generated method stub
		E tempData = Calculator.get(Calculator.size()-1);
		Calculator.remove(Calculator.size()-1);
		return tempData;
	}

	@Override
	/**
	 * Removes the last occurence of the object inside a list by updating the index at which the object is found at until there are no more items in the array 
	 * idx - index of the object
	 */
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		int idx = -1;
		for(int i = 0; i < Calculator.size()-1; i++) {
			if(Calculator.get(i).equals(o)) {
				idx = i;
			}
		}
		Calculator.remove(idx);
		if(idx > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	/**
	 * returns the size of the array list
	 */
	public int size() {
		// TODO Auto-generated method stub
		return Calculator.size();
	}
}
