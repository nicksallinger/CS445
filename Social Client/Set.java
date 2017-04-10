import java.util.Arrays;

public class Set<T> implements SetInterface<T>{

	private T[] set;
	private int numberOfEntries;
	private static final int maxCapacity = 10000;
	private int size;
	private boolean initialized = false;
	
	public Set(){
		this(25);
	}
	
	public Set(int capacity){
		
		@SuppressWarnings("unchecked")
		
        T[] tempSet = (T[]) new Object[capacity]; // Unchecked cast
        set = tempSet;
        numberOfEntries = 0;
        initialized = true;
        
	}
	
	
	
	
	
	
	
	
	/**
     * Determines the current number of entries in this set.
     *
     * @return  The integer number of entries currently in this set
     */
	public int getCurrentSize() {
		return numberOfEntries;
		
	}

	/**
     * Determines whether this set is empty.
     *
     * @return  true if this set is empty; false if not
     */
	public boolean isEmpty() {
		if(numberOfEntries == 0){
		return true;
		}
		else
			return false;
	}

	/**
     * Adds a new entry to this set, avoiding duplicates.
     *
     * <p> If newEntry is not null, this set does not contain newEntry, and this
     * set has available capacity, then add modifies the set so that it contains
     * newEntry. All other entries remain unmodified.
     *
     * <p> If newEntry is null, then add throws
     * java.lang.IllegalArgumentException without modifying the set. If this set
     * already contains newEntry, then add returns false without modifying the
     * set. If this set has a capacity limit, and does not have available
     * capacity, then add throws SetFullException without modifying the set.
     *
     * @param newEntry  The object to be added as a new entry
     * @return  true if the addition is successful; false if the item already is
     * in this set
     * @throws SetFullException  If this set does not have the capacity to store
     * an additional entry
     * @throws java.lang.IllegalArgumentException  If newEntry is null
     */
	public boolean add(T newEntry) throws SetFullException, IllegalArgumentException {
		
		boolean add;
		
		if(isArrayFull()){
			@SuppressWarnings("unchecked")
			T[] tempSet = (T[]) new Object[set.length*2]; // Unchecked
			for(int i = 0; i < set.length; i++){
				tempSet[i] = set[i];
				set = tempSet;	
			}
		}
		//true if set contains entry
		if(contains(newEntry)){
			System.out.println("duplicate");
			return false;
		}
		else
		try{
			set[numberOfEntries] = newEntry;
			numberOfEntries++;
			add = true;
		}
		catch(Exception e){
			return false;
		}
		
		return add;
	}

	/**
     * Removes a specific entry from this set, if possible.
     *
     * <p> If this set contains entry, remove will modify the set so that it no
     * longer contains entry. All other entries remain unmodified.
     *
     * <p> If this set does not contain entry, remove will return false without
     * modifying the set. If entry is null, then remove throws
     * java.lang.IllegalArgumentException without modifying the set.
     *
     * @param entry  The entry to be removed
     * @return  true if the removal was successful; false if not
     * @throws java.lang.IllegalArgumentException  If entry is null
     */
	public boolean remove(T entry) throws IllegalArgumentException {
		boolean exists = false;
		int location = -1;
		if(entry == null){
			IllegalArgumentException i = new IllegalArgumentException();
			throw i;
		}
		
		while(exists = false){
			for (int i = 0; i < numberOfEntries; i++){
				if(set[i].equals(entry)){
					exists = true;
					location = i;
				}
			}
		}
		if(exists){
			set[location] = set[numberOfEntries-1];
			set[numberOfEntries-1] = null;
			numberOfEntries--;
			
		}
		return exists;
	}

	/**
     * Removes an unspecified entry from this set, if possible.
     *
     * <p> If this set contains at least one entry, remove will modify the set
     * so that it no longer contains one of its entries. All other entries
     * remain unmodified. The removed entry will be returned.
     *
     * <p> If this set is empty, remove will return null without modifying the
     * set. Because null cannot be added, a return value of null will never
     * indicate a successful removal.
     *
     * @return  The removed entry if the removal was successful; null otherwise
     */
	public T remove() {
		T removed = null;
		if(numberOfEntries >= 1){
	
			removed = set[numberOfEntries-1];
			set[numberOfEntries-1] = null;
			numberOfEntries--;
		}
			return removed;
	}

	/**
     * Removes all entries from this set.
     *
     * <p> If this set is already empty, clear will not modify the set.
     * Otherwise, the set will be modified so that it contains no entries.
     */
	public void clear() {
		while(!isEmpty()){
			remove();
		}
		
	}

	/**
     * Tests whether this set contains a given entry.
     *
     * <p> If this set contains entry, then contains returns true. Otherwise
     * (including if this set is empty), contains returns false. If entry is
     * null, then remove throws java.lang.IllegalArgumentException without
     * modifying the set.
     *
     * @param entry  The entry to locate
     * @return  true if this set contains entry; false if not
     * @throws java.lang.IllegalArgumentException  If entry is null
     */
	public boolean contains(T entry) throws IllegalArgumentException {
		boolean exists = false;
		while(exists = false){
			for (int i = 0; i < numberOfEntries; i++){
				if(set[i].equals(entry)){
					exists = true;
				}
			}
		}
		return exists;
	}

	/**
     * Retrieves all entries that are in this set.
     *
     * <p> An array is returned that contains a reference to each of the entries
     * in this set. Its capacity will be equal to the number of elements in this
     * set, and thus the array will contain no null values.
     *
     * <p> If the implementation of set is array-backed, toArray will not return
     * the private backing array. Instead, a new array will be allocated with
     * the appropriate capacity.
     *
     * @return  A newly-allocated array of all the entries in this set
     */
	public T[] toArray() {
		
		@SuppressWarnings("unchecked")
		T[] tempSet = (T[]) new Object[numberOfEntries];
		for(int i = 0; i< numberOfEntries; i++){
			tempSet[i] = set[i];
		}
		
		
		
		
		
		return tempSet;
		
		
	}
	
	private boolean isArrayFull(){
		return numberOfEntries >= set.length;
	}
	
}