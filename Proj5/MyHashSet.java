import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashSet<E> implements Iterable<E> {

	private final static int DEFAULT_INITIAL_CAPACITY = 4;

	private final static double MAX_LOAD_FACTOR = 0.75;

	public ArrayList<Node<E>> hashTable;

	private int size;

	public static class Node<T> {
		private T data;
		public Node<T> next;

		private Node(T data) {
			this.data = data;
			next = null;
		}
	}
	// creates hashset with initial capacity size and null elements
	public MyHashSet(int initialCapacity) {
		hashTable = new ArrayList<Node<E>>();
		while (hashTable.size() < initialCapacity) {
			hashTable.add(null);
		}
	}
	//calls constructor with default initial capacity
	public MyHashSet() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	//returns number of elements
	public int size() {
		return size;
	}
	//returns table size
	public int getCapacity() {
		return hashTable.size();
	}

	public boolean contains(Object element) {
		//checks if element is in hashtable
		Node<E> currNode = hashTable.get(Math.abs(element.hashCode() 
				% hashTable.size()));
		
		if (currNode == null) {
			return false;
		}
		//iterates through hashtable looking for element
		while (currNode.next != null) {
			if (currNode.data.equals(element)) {
				return true;
			}
			currNode = currNode.next;
			if (currNode.next == null) {
				if (currNode.data.equals(element)) {
					return true;
				}
			}
		}
		return false;
	}

	public void rehash() {
		//creates arraylist with double the size and moves elements over
		ArrayList<Node<E>> newArr = new ArrayList<Node<E>>(2 * hashTable.size());
		for (int i = 0; i < 2 * hashTable.size(); i++) {
			newArr.add(null);
		}
		for (int i = 0; i < hashTable.size(); i++) {
			Node<E> curr = hashTable.get(i);
			while (curr != null) {
				int currHash = Math.abs(curr.data.hashCode() % newArr.size());
				Node<E> newNode = new Node<E>(curr.data);
				if (newArr.get(currHash) == null) {
					newArr.set(currHash, newNode);
				} else {
					Node<E> currNode = newArr.get(currHash);
					while (currNode.next != null) {
						currNode = currNode.next;
					}
					if (currNode.next == null) {
						currNode.next = newNode;
					}
				}
				curr = curr.next;
			}
		}
		hashTable = newArr;

	}

	public void add(E element) {
		//add element to hashtable if not present
		if (this.contains(element) == false) {
			int index = Math.abs(element.hashCode() % this.getCapacity());
			Node<E> newNode = new Node<E>(element);
			if (hashTable.get(index) == null) {
				hashTable.set(index, newNode);
			} else {
				Node<E> curr = hashTable.get(index);
				while (curr.next != null) {
					curr = curr.next;
				}
				if (curr.next == null) {
					curr.next = newNode;
				}
			}
			size++;
			//if load factor >= .75 then rehash table w double size
			double currLoadFactor = (double) size / (double) hashTable.size();
			if (currLoadFactor >= MAX_LOAD_FACTOR) {
				rehash();
			}
		} else {

		}
	}

	public boolean remove(Object element) {
		//remove element from hashTable
		if (this.contains(element) == true) {
			int bucket = Math.abs(element.hashCode() % hashTable.size());
			Node<E> currNode = hashTable.get(bucket);
			Node<E> prev = null;
			//iterates through hashtable looking for element
			while (currNode != null) {
				if (currNode.data.equals(element)) {
					if (currNode == hashTable.get(bucket)) {
						hashTable.set(bucket, hashTable.get(bucket).next);
					} else {
						prev.next = currNode.next;
					}
					break;
				} else {
					prev = currNode;
					currNode = currNode.next;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			//Initialize at first element
			Node<E> currentNode = null;
			private int bucket = -1;

			@Override
			public boolean hasNext() {
				if (currentNode != null && currentNode.next != null) {
					//if not at the last node, has next is true
					return true;
				}
				for (int b = bucket + 1; b < hashTable.size(); b++) {
					if (hashTable.get(b) != null) {
						//if the bucket hasnt reached its end, has next is true
						return true;
					}
				}
				return false;
			}

			@Override
			public E next() {
				{
					//if the node and next node are not null, set curr to next
					if (currentNode != null && currentNode.next != null) {
						currentNode = currentNode.next;
					} else {
						//iterate through bucket
						//if bucket reaches end of table, throw exception
						do {
							bucket++;
							if (bucket == hashTable.size()) {
								throw new NoSuchElementException();
							}
							currentNode = hashTable.get(bucket);
						} while (currentNode == null);
					}
					return currentNode.data;
				}
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

		};
	}

}
