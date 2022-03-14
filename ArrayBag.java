/*
 * Name: Damien Hosea
 * Course:220-04
 * Description: This is the ArrayBag. It's a bag that hold varies items. You are able to add, remove and other things
 * with the items in the bag. This Array implements all of the attributes of the BagInterface.
 * Team: Hosea, Damien; BHARDWAJ,AKSHAT; Cotton,Jaiden Nicholas
 */
import java.util.Arrays;

/**
 *
 * @author csc220, modified by isabel
 */
public class ArrayBag<T> implements BagInterface<T> {

    private T[] bag;
    private static final int DEFAULT_CAPACITY = 40;
    private int numberOfEntries; //current # of entries in the T[], bag

    /**
     * Creates an empty bag whose initial capacity is 40.
     */
    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty bag having a given initial capacity.
     * @param capacity the integer capacity desired
     */
    public ArrayBag(int capacity) {
        //initially bag is empty
        bag = (T[]) new Object[capacity];  // Object is an ancestor of any class

    }


    public int getCurrentSize() {
        return numberOfEntries-1;
    }


    /**
     * Sees whether this bag is full.
     * @return true if the bag is full, or false if not
     */
    @Override
    public boolean isFull() {
        return numberOfEntries == bag.length;
    }



    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * Adds a new entry to this bag.
     *
     * @param newEntry the object to be added as a new entry
     * @return true if the addition is successful, or false if not
     */
    @Override
    public boolean add(T newEntry) {
        boolean result = true;

        if (isFull())
            ensureCapacity();

        bag[numberOfEntries] = newEntry;
        ++numberOfEntries;

        return result;
    }

    /*
     * Expand the bag twice of its original size
     */
    private void ensureCapacity() {
        if (isFull()) {
            bag = Arrays.copyOf(bag, 2 * bag.length);
            //https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
        }
    }

    // remove the last entry in the bag
    // @return the last entry in the bag
    @Override
    public T remove() {
        T result = null;
        if (!isEmpty() ) {

            result = bag[numberOfEntries];
            bag[numberOfEntries-1] = null;
            numberOfEntries++;

        } // end if
        return result;
    }

//    @Override
//    public boolean remove(T anEntry) {
//        return false;
//    }

    //  Removes on occurrence of a given entry from this bag
    //  @param anEntry the entry to be removed
    //  @return true if the removal was successful, false otherwise

    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);  // to make sure... assertion
    }

    // return the index where a given anEntry is located from this bag
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false; //initially it is not found yet!
        for (int index = 0; index <= numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index;
                break;  // exit the closest for loop
            } // end if
        } // end for
        // Assertion: If where > -1, anEntry is in the array bag,
        // otherwise, anEntry is not in the array
        return where;
    }

    //  remove an entry at the given index
    //  @param the index where the entry to remove is located
    //  @return the object at the given index
    private T removeEntry(int givenIndex) {
        T result = null;

        if (givenIndex<0 || isEmpty())
            return null;

        //lets remove
        result = bag[givenIndex]; // entry to remove
        numberOfEntries--;
        bag[givenIndex] = bag[numberOfEntries]; // replace entry with last entry
        bag[numberOfEntries] = null; // remove last entry

        return result;
    }

    // clear the bag
    @Override
    public void clear() {
        if (isEmpty()==true)  return;
        for (int i=0; i < numberOfEntries; i++)
            bag[i]=null;
        numberOfEntries=0;
    }

//    @Override
//    public int getFrequencyOf(T anEntry) {
//        return 0;
//    }

//    @Override
//    public boolean contains(T anEntry) {
//        return false;
//    }

    /**
     * Counts the number of times a given entry appears in this bag.
     * @param anEntry the entry to be counted
     * @return the number of times anEntry appears in the bag
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                counter++;
            } // end if
        } // end for
        return counter;
    }

    /**
     * Tests whether this bag contains a given entry.
     * @param anEntry the entry to locate\
     * @return true if the bag contains anEntry, or false otherwise
     */
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0;  index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                found = true;
                break;  // lets exit out of for loop, because we found it.
            } // end if
        } // end for
        return found;
    }

    /**
     * Retrieves all entries that are in this bag.
     * @return a newly allocated array of all the entries in the bag
     */
    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        } // end for
        return result;
    }




}