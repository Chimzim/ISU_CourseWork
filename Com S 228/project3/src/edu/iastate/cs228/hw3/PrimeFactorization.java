package edu.iastate.cs228.hw3;

/**
 *  
 * @author
 *
 */

import java.util.ListIterator;



public class PrimeFactorization implements Iterable<PrimeFactor>
{
	private static final long OVERFLOW = -1;
	private long value; 	// the factored integer 
							// it is set to OVERFLOW when the number is greater than 2^63-1, the
						    // largest number representable by the type long. 
	
	/**
	 * Reference to dummy node at the head.
	 */
	private Node head;
	  
	/**
	 * Reference to dummy node at the tail.
	 */
	private Node tail;
	
	private int size;     	// number of distinct prime factors
	// ------------
	// Constructors 
	// ------------
	
    /**
	 *  Default constructor constructs an empty list to represent the number 1.
	 *  
	 *  Combined with the add() method, it can be used to create a prime factorization.  
	 */
	public PrimeFactorization() 
	{
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		value = 1;
	}

	
	/** 
	 * Obtains the prime factorization of n and creates a doubly linked list to store the result.   
	 * Follows the direct search factorization algorithm in Section 1.2 of the project description. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization(long n) throws IllegalArgumentException 
	{
		if(n < 1) {
			throw new IllegalArgumentException();
		}
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		long myNumber = n;
		int mult = 0;
		int value = 2;
		while(myNumber > (value*value)) {
			if(myNumber % value == 0 && isPrime(value)) {
				myNumber /= value;
				mult++;
				while(myNumber % value == 0) {
					myNumber /= value;
					mult++;
				}
			}
			add(value, mult);
			value++;
			mult = 0;
		}
		// TODO 
	}
	
	
	/**
	 * Copy constructor. It is unnecessary to verify the primality of the numbers in the list.
	 * 
	 * @param pf
	 */
	public PrimeFactorization(PrimeFactorization pf)
	{
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		PrimeFactorizationIterator iter = pf.iterator();
		while(iter.hasNext()) {
			add(iter.cursor.pFactor.prime, iter.cursor.pFactor.multiplicity);
		}
	}
	
	/**
	 * Constructs a factorization from an array of prime factors.  Useful when the number is 
	 * too large to be represented even as a long integer. 
	 * 
	 * @param pflist
	 */
	public PrimeFactorization (PrimeFactor[] pfList)
	{
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.previous = head;
		for(int i = 0; i < pfList.length; i++) {
			add(pfList[i].prime, pfList[i].multiplicity);
		}
	}
	
	

	// --------------
	// Primality Test
	// --------------
	
    /**
	 * Test if a number is a prime or not.  Check iteratively from 2 to the largest 
	 * integer not exceeding the square root of n to see if it divides n. 
	 * 
	 *@param n
	 *@return true if n is a prime 
	 * 		  false otherwise 
	 */
    public static boolean isPrime(long n) 
	{
	    // TODO 
    	int value = 2;
    	while(n > (value*value)) {
    		if(n % value == 0) {
    			return false;	
    		}
    		value++;
    	}
		return true; 
	}   

   
	// ---------------------------
	// Multiplication and Division 
	// ---------------------------
	
	/**
	 * Multiplies the integer v represented by this object with another number n.  Note that v may 
	 * be too large (in which case this.value == OVERFLOW). You can do this in one loop: Factor n and 
	 * traverse the doubly linked list simultaneously. For details refer to Section 3.1 in the 
	 * project description. Store the prime factorization of the product. Update value and size. 
	 * 
	 * @param n
	 * @throws IllegalArgumentException if n < 1
	 */
	public void multiply(long n) throws IllegalArgumentException 
	{
		long myNumber = n;
		int mult = 0;
		int value = 2;
		if(n < 1) {
			throw new IllegalArgumentException();
		}
		if(isPrime(n)) {
			add((int)n, 1);
		}
		else {
			while(n > (value*value)) {
				if(myNumber % value == 0 && isPrime(value)) {
					myNumber /= value;
					mult++;
					while(myNumber % value == 0) {
						myNumber /= value;
						mult++;
					}
					add(value, mult);
				}
				value++;
				mult = 0;
			}
			if(isPrime(myNumber)) {
				add((int)myNumber, 1);
			}
		}
	}
	
	/**
	 * Multiplies the represented integer v with another number in the factorization form.  Traverse both 
	 * linked lists and store the result in this list object.  See Section 3.1 in the project description 
	 * for details of algorithm. 
	 * 
	 * @param pf 
	 */
	public void multiply(PrimeFactorization pf)
	{
		PrimeFactorizationIterator iter = pf.iterator();
		while(iter.cursor.pFactor != null) {
			add(iter.cursor.pFactor.prime, iter.cursor.pFactor.multiplicity);
			iter.cursor = iter.cursor.next;
		}
	}
	
	
	/**
	 * Multiplies the integers represented by two PrimeFactorization objects.  
	 * 
	 * @param pf1
	 * @param pf2
	 * @return object of PrimeFactorization to represent the product 
	 */
	public static PrimeFactorization multiply(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		pf1.multiply(pf2);
		return pf1; 
	}

	
	/**
	 * Divides the represented integer v by n.  Make updates to the list, value, size if divisible.  
	 * No update otherwise. Refer to Section 3.2 in the project description for details. 
	 *  
	 * @param n
	 * @return  true if divisible 
	 *          false if not divisible 
	 * @throws IllegalArgumentException if n <= 0
	 */
	public boolean dividedBy(long n) throws IllegalArgumentException
	{
		PrimeFactorization myNumber = new PrimeFactorization(n);
		if(n <= 0) {
			throw new IllegalArgumentException();
		}
		return this.dividedBy(myNumber); 
	}

	
	/**
	 * Division where the divisor is represented in the factorization form.  Update the linked 
	 * list of this object accordingly by removing those nodes housing prime factors that disappear  
	 * after the division.  No update if this number is not divisible by pf. Algorithm details are 
	 * given in Section 3.2. 
	 * 
	 * @param pf
	 * @return	true if divisible by pf
	 * 			false otherwise
	 */
	public boolean dividedBy(PrimeFactorization pf)
	{
		if(value != -1 && pf.value != -1 && value < pf.value ||
				this.value != -1 && pf.value == -1) {
			return false;
		}
		if(pf.value == value) {
			clearList();
			return true;
		}
		PrimeFactorization iterCopy = new PrimeFactorization(this);
		PrimeFactorizationIterator iter = iterCopy.iterator();
		PrimeFactorizationIterator iter2 = pf.iterator();
		while(iter.cursor.pFactor.prime >= iter2.cursor.pFactor.prime ||
				!iter.hasNext() && iter2.hasNext()) {
			iter.cursor = iter.cursor.next;
			iter2.cursor = iter2.cursor.next;
		
		if(iter.cursor.next == null && iter2.cursor.next != null) {
			return false;
		}
		else if(iter.cursor.pFactor.prime >= iter2.cursor.pFactor.prime) {
			if(iter.cursor.pFactor.prime > iter2.cursor.pFactor.prime) {
				return false;
				}
			else if(iter.cursor.pFactor.prime == iter2.cursor.pFactor.prime &&
					iter.cursor.pFactor.multiplicity > iter2.cursor.pFactor.multiplicity) {
				return false;
			}
			else if(iter.cursor.pFactor.prime == iter2.cursor.pFactor.prime &&
					iter.cursor.pFactor.multiplicity <= iter2.cursor.pFactor.multiplicity) {
				if(iter.cursor.pFactor.multiplicity - iter2.cursor.pFactor.multiplicity <= 0) {
					remove(iter.cursor.pFactor.prime, iter.cursor.pFactor.multiplicity);
					updateValue();
				}
				else {
					iter.cursor.pFactor.multiplicity -= iter2.cursor.pFactor.multiplicity;
					updateValue();
				}
			}
			}
		}
		this.clearList();
		this.multiply(iterCopy);
		return true;
	}

	
	/**
	 * Divide the integer represented by the object pf1 by that represented by the object pf2. 
	 * Return a new object representing the quotient if divisible. Do not make changes to pf1 and 
	 * pf2. No update if the first number is not divisible by the second one. 
	 *  
	 * @param pf1
	 * @param pf2
	 * @return quotient as a new PrimeFactorization object if divisible
	 *         null otherwise 
	 */
	public static PrimeFactorization dividedBy(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		PrimeFactorization toReturn = new PrimeFactorization(pf1);
		toReturn.dividedBy(pf2);
		return toReturn; 
	}

	
	// -----------------------
	// Greatest Common Divisor
	// -----------------------

	/**
	 * Computes the greatest common divisor (gcd) of the represented integer v and an input integer n.
	 * Returns the result as a PrimeFactor object.  Calls the method Euclidean() if 
	 * this.value != OVERFLOW.
	 *     
	 * It is more efficient to factorize the gcd than n, which can be much greater. 
	 *     
	 * @param n
	 * @return prime factorization of gcd
	 * @throws IllegalArgumentException if n < 1
	 */
	public PrimeFactorization gcd(long n) throws IllegalArgumentException
	{
		PrimeFactorization myNumber = null;
		if(n < 1) {
			throw new IllegalArgumentException();
		}
		if(value != OVERFLOW) {
			myNumber = new PrimeFactorization(Euclidean(value,n));
		}
		return myNumber; 
	}
	

	/**
	  * Implements the Euclidean algorithm to compute the gcd of two natural numbers m and n. 
	  * The algorithm is described in Section 4.1 of the project description. 
	  * 
	  * @param m
	  * @param n
	  * @return gcd of m and n. 
	  * @throws IllegalArgumentException if m < 1 or n < 1
	  */
 	public static long Euclidean(long m, long n) throws IllegalArgumentException
	{
 		long number1 = m;
 		long number2 = n;
 		long gcd = 0;
 		if(m < 1 || n < 1) {
 			throw new IllegalArgumentException();
 		}
 		if(number2 > number1) {
 			number2 %= number1;
 			if(number2 != 0) {
 				return Euclidean(number1, number2);
 			}
 			else {
 				gcd = number1;
 			}
 		}
 		else {
 			number1 %= number2;
 			if(number1 != 0) {
 				return Euclidean(number1, number2);
 			}
 			else {
 				gcd = number2;
 			}
 		}
		return gcd; 
	}

 	
	/**
	 * Computes the gcd of the values represented by this object and pf by traversing the two lists.  No 
	 * direct computation involving value and pf.value. Refer to Section 4.2 in the project description 
	 * on how to proceed.  
	 * 
	 * @param  pf
	 * @return prime factorization of the gcd
	 */
	public PrimeFactorization gcd(PrimeFactorization pf)
	{
		PrimeFactorizationIterator iter = pf.iterator();
		PrimeFactorization toReturn = new PrimeFactorization();
		while(iter.cursor.next.pFactor != null) {
			Node temp = head.next;
			while(temp.next.pFactor != null) {
				if(temp.pFactor.prime == iter.cursor.pFactor.prime) {
					if(temp.pFactor.multiplicity < iter.cursor.pFactor.multiplicity) {
						toReturn.add(temp.pFactor.prime, temp.pFactor.multiplicity);
					}
					else {
						toReturn.add(temp.pFactor.prime, iter.cursor.pFactor.multiplicity);
					}
				}
				temp = temp.next;
			}
			iter.cursor = iter.cursor.next;
		}
		return toReturn; 
	}
	
	
	/**
	 * 
	 * @param pf1
	 * @param pf2
	 * @return prime factorization of the gcd of two numbers represented by pf1 and pf2
	 */
	public static PrimeFactorization gcd(PrimeFactorization pf1, PrimeFactorization pf2)
	{
		pf1.gcd(pf2);
		return pf1; 
	}

	// ------------
	// List Methods
	// ------------
	
	/**
	 * Traverses the list to determine if p is a prime factor. 
	 * 
	 * Precondition: p is a prime. 
	 * 
	 * @param p  
	 * @return true  if p is a prime factor of the number v represented by this linked list
	 *         false otherwise 
	 * @throws IllegalArgumentException if p is not a prime
	 */
	public boolean containsPrimeFactor(int p) throws IllegalArgumentException
	{
		if(isPrime(p) == false) {
			throw new IllegalArgumentException();
		}
		Node temp = head.next;
		while(temp.next.pFactor != null) {
			if(temp.pFactor.prime == p) {
				return true;
			}
			temp = temp.next;
		} 
		return false; 
	}
	
	// The next two methods ought to be private but are made public for testing purpose. Keep
	// them public 
	
	/**
	 * Adds a prime factor p of multiplicity m.  Search for p in the linked list.  If p is found at 
	 * a node N, add m to N.multiplicity.  Otherwise, create a new node to store p and m. 
	 *  
	 * Precondition: p is a prime. 
	 * 
	 * @param p  prime 
	 * @param m  multiplicity
	 * @return   true  if m >= 1
	 *           false if m < 1   
	 */
    public boolean add(int p, int m) 
    {
    	Node iter = head.next;
    	if(m < 1) {
    		return false;
    	}
    	while(iter.next.pFactor != null) {
    		if(iter.pFactor.prime == p) {
    			iter.pFactor.multiplicity += m;
    			updateValue();
    			return true;
    		}
    		else if(p < iter.next.pFactor.prime && iter.next != null) {
    			PrimeFactor temp = new PrimeFactor(p, m);
    	    	Node aNode = new Node(temp);
    			link(iter, aNode);
    			size++;
    			updateValue();
    			return true;
    		}
    		iter = iter.next;
    	}
    	Node aNode = new Node(p,m);
    	link(iter, aNode);
    	size++;
    	updateValue();
    	return true; 
    }

	    
    /**
     * Removes m from the multiplicity of a prime p on the linked list.  It starts by searching 
     * for p.  Returns false if p is not found, and true if p is found. In the latter case, let 
     * N be the node that stores p. If N.multiplicity > m, subtracts m from N.multiplicity.  
     * If N.multiplicity <= m, removes the node N.  
     * 
     * Precondition: p is a prime. 
     * 
     * @param p
     * @param m
     * @return true  when p is found. 
     *         false when p is not found. 
     * @throws IllegalArgumentException if m < 1
     */
    public boolean remove(int p, int m) throws IllegalArgumentException
    {
    	Node iter = head.next;
    	int found = 0;
    	if(m < 1) {
    		throw new IllegalArgumentException();
    	}
    	
    	while(iter.next.pFactor != null) {
    		if(iter.pFactor.prime == p) {
    			if(iter.pFactor.multiplicity > m) {
    				iter.pFactor.multiplicity -= m;
    			}
    			else {
    				unlink(iter);
    				found = 1;
    				size--;
    			}
    		}
    		iter = iter.next;
    	}
    	updateValue();
    	return found == 1; 
    }


    /**
     * 
     * @return size of the list
     */
	public int size() 
	{
		return size; 
	}

	
	/**
	 * Writes out the list as a factorization in the form of a product. Represents exponentiation 
	 * by a caret.  For example, if the number is 5814, the returned string would be printed out 
	 * as "2 * 3^2 * 17 * 19". 
	 */
	@Override 
	public String toString()
	{
		String toPrint = "";
		Node temp = head;
		while(temp.next.pFactor != null) {
			if(temp.pFactor.multiplicity == 1) {
				toPrint += temp.pFactor.prime;
			}
			else {
				toPrint += temp.pFactor.prime + '*'+ temp.pFactor.multiplicity;
				temp = temp.next;
			}
		}
		return toPrint; 
	}

	
	// The next three methods are for testing, but you may use them as you like.  

	/**
	 * @return true if this PrimeFactorization is representing a value that is too large to be within 
	 *              long's range. e.g. 999^999. false otherwise.
	 */
	public boolean valueOverflow() {
		return value == OVERFLOW;
	}

	/**
	 * @return value represented by this PrimeFactorization, or -1 if valueOverflow()
	 */
	public long value() {
		return value;
	}

	
	public PrimeFactor[] toArray() {
		PrimeFactor[] arr = new PrimeFactor[size];
		int i = 0;
		for (PrimeFactor pf : this)
			arr[i++] = pf;
		return arr;
	}


	
	@Override
	public PrimeFactorizationIterator iterator()
	{
	    return new PrimeFactorizationIterator();
	}
	
	/**
	 * Doubly-linked node type for this class.
	 */
    private class Node 
    {
		public PrimeFactor pFactor;			// prime factor 
		public Node next;
		public Node previous;
		
		/**
		 * Default constructor for creating a dummy node.
		 */
		public Node()
		{
			pFactor = null;
			previous = null;
			next = null;
			// TODO 
		}
	    
		/**
		 * Precondition: p is a prime
		 * 
		 * @param p	 prime number 
		 * @param m  multiplicity 
		 * @throws IllegalArgumentException if m < 1 
		 */
		public Node(int p, int m) throws IllegalArgumentException 
		{	
			if(m < 1) {
				throw new IllegalArgumentException();
			}
			PrimeFactor temp = new PrimeFactor(p,m);
			pFactor = temp;
		}   

		
		/**
		 * Constructs a node over a provided PrimeFactor object. 
		 * 
		 * @param pf
		 * @throws IllegalArgumentException
		 */
		public Node(PrimeFactor pf)  
		{
			pFactor = pf;
			// TODO 
		}


		/**
		 * Printed out in the form: prime + "^" + multiplicity.  For instance "2^3". 
		 * Also, deal with the case pFactor == null in which a string "dummy" is 
		 * returned instead.  
		 */
		@Override
		public String toString() 
		{
			Node iter = head.next;
			return iter.toString(); 
		}
    }

    
    private class PrimeFactorizationIterator implements ListIterator<PrimeFactor>
    {  	
        // Class invariants: 
        // 1) logical cursor position is always between cursor.previous and cursor
        // 2) after a call to next(), cursor.previous refers to the node just returned 
        // 3) after a call to previous() cursor refers to the node just returned 
        // 4) index is always the logical index of node pointed to by cursor

        private Node cursor = head.next;
        private Node pending = null;    // node pending for removal
        private int index = 0;    
    //    private PrimeFactorizationIterator iter;
  	  
    	// other instance variables ... 
    	  
      
        /**
    	 * Default constructor positions the cursor before the smallest prime factor.
    	 */
    	public PrimeFactorizationIterator()
    	{
    		//cursor = head;
    	}

    	@Override
    	public boolean hasNext()
    	{
    		if(cursor.next != null) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}

    	
    	@Override
    	public boolean hasPrevious()
    	{
    		if(cursor.previous != null) {
    			return true;
    		}
    		else {
    			return false;
    		} 
    	}

 
    	@Override 
    	public PrimeFactor next() 
    	{
    		if(hasNext() != false) {
    			cursor = cursor.next;
    		}
    		pending = cursor.previous;
    		index += 1;
    		return cursor.previous.pFactor; 
    	}

 
    	@Override 
    	public PrimeFactor previous() 
    	{
    		// TODO
    		if(hasPrevious() != false) {
    			cursor = cursor.previous;
    		}
    		pending = cursor;
    		index -= 1;
    		return cursor.pFactor; 
    	}

   
    	/**
    	 *  Removes the prime factor returned by next() or previous()
    	 *  
    	 *  @throws IllegalStateException if pending == null 
    	 */
    	@Override
    	public void remove() throws IllegalStateException
    	{
    		if(pending == null) {
    			throw new IllegalStateException();
    		}
    		unlink(pending);
    		index -= 1;
    		size -= 1;
    	}
 
 
    	/**
    	 * Adds a prime factor at the cursor position.  The cursor is at a wrong position 
    	 * in either of the two situations below: 
    	 * 
    	 *    a) pf.prime < cursor.previous.pFactor.prime if cursor.previous != head. 
    	 *    b) pf.prime > cursor.pFactor.prime if cursor != tail. 
    	 * 
    	 * Take into account the possibility that pf.prime == cursor.pFactor.prime. 
    	 * 
    	 * Precondition: pf.prime is a prime. 
    	 * 
    	 * @param pf  
    	 * @throws IllegalArgumentException if the cursor is at a wrong position. 
    	 */
    	@Override
        public void add(PrimeFactor pf) throws IllegalArgumentException 
        {
    		if(pf.prime < cursor.previous.pFactor.prime && cursor.previous != head ||(pf.prime > cursor.pFactor.prime && cursor != tail) ) {
    			throw new IllegalArgumentException();
    		}
    		Node temp = new Node(pf);
    		link(cursor.previous, temp);
    		index += 1;
    		size += 1;
        }


    	@Override
		public int nextIndex() 
		{
			return index;
		}


    	@Override
		public int previousIndex() 
		{
			return index - 1;
		}

		@Deprecated
		@Override
		public void set(PrimeFactor pf) 
		{
			throw new UnsupportedOperationException(getClass().getSimpleName() + " does not support set method");
		}
        
    	// Other methods you may want to add or override that could possibly facilitate 
    	// other operations, for instance, addition, access to the previous element, etc.
    	// 
    	// ...
    	// 
    }

    
    // --------------
    // Helper methods 
    // -------------- 
    
    /**
     * Inserts toAdd into the list after current without updating size.
     * 
     * Precondition: current != null, toAdd != null
     */
    private void link(Node current, Node toAdd)
    {
    	toAdd.previous = current;
    	toAdd.next = current.next;
    	current.next.previous = toAdd;
    	current.next = toAdd;
    }

	 
    /**
     * Removes toRemove from the list without updating size.
     */
    private void unlink(Node toRemove)
    {
    	toRemove.previous.next = toRemove.next;
    	toRemove.next.previous = toRemove.previous;
    }


    /**
	  * Remove all the nodes in the linked list except the two dummy nodes. 
	  * 
	  * Made public for testing purpose.  Ought to be private otherwise. 
	  */
	public void clearList()
	{
		head.next = tail;
		tail.previous = head;
		size = 0;
		value = 1;
	}	
	
	/**
	 * Multiply the prime factors (with multiplicities) out to obtain the represented integer.  
	 * Use Math.multiply(). If an exception is throw, assign OVERFLOW to the instance variable value.  
	 * Otherwise, assign the multiplication result to the variable. 
	 * 
	 */
	private void updateValue()
	{
		Node updating = head.next;
		try {
			while(updating.next != null) {
				value += Math.pow(updating.pFactor.prime, updating.pFactor.multiplicity); 
			}
		} 
			
		catch (ArithmeticException e) 
		{
			value = OVERFLOW;
		}
		
	}
}
