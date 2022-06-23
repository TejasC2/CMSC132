package searchTree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private Tree<K, V> left, right;
	private K key;
	private V value;

	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	// Looks for key and returns associated value
	public V search(K key) {
		if (this.key.compareTo(key) == 0) {
			return this.value;
		} else if (this.key.compareTo(key) < 0) {
			return this.right.search(key);
		} else if (this.key.compareTo(key) > 0) {
			return this.left.search(key);
		} else {
			return null;
		}
	}

	// returns tree with key and value inserted in
	public NonEmptyTree<K, V> insert(K key, V value) {
		if (this.key != null) {
			if (key.compareTo(this.key) == 0) {
				this.value = value;

			} else if (key.compareTo(this.key) < 0) {
				left = left.insert(key, value);

			} else {
				right = right.insert(key, value);

			}
		}
		return this;
	}

	// deletes parameter key from tree and returns modified tree
	public Tree<K, V> delete(K key) {
		if (this.key.compareTo(key) == 0) {
			try {
				this.key = left.max();
				this.value = left.search(left.max());
				left = left.delete(left.max());
				return this;
			} catch (TreeIsEmptyException e) {
				try {
					this.key = right.min();
					this.value = right.search(right.min());
					right = right.delete(right.min());
					return this;
				} catch (TreeIsEmptyException ee) {
					return EmptyTree.getInstance();
				}
			}
		} else if (this.key.compareTo(key) < 0) {
			right = right.delete(key);
		} else {
			left = left.delete(key);
		}
		return this;
	}

	// returns max value in tree by going to rightmost value
	public K max() {
		K temp = this.key;
		try {
			temp = right.max();
		} catch (TreeIsEmptyException e) {
			return temp;
		}
		return temp;
	}

	// returns min value in tree by going to leftmost value
	public K min() {
		K temp = this.key;
		try {
			temp = left.min();
		} catch (TreeIsEmptyException e) {
			return temp;
		}
		return temp;
	}

	// returns size, add one recursively for each key visited
	public int size() {
		return left.size() + right.size() + 1;
	}

	// adds keys to collection in order, algorithm seen in class
	public void addKeysToCollection(Collection<K> c) {
		left.addKeysToCollection(c);
		c.add(this.key);
		right.addKeysToCollection(c);
	}

	// adds keys from fromKey to toKey into new tree and returns that tree
	public Tree<K, V> subTree(K fromKey, K toKey) {
		// filters out keys bigger than toKey and smaller than fromKey
		if (this.key.compareTo(fromKey) < 0) {
			return right.subTree(fromKey, toKey);
		} else if (this.key.compareTo(toKey) > 0) {
			return left.subTree(fromKey, toKey);
		} else {
			NonEmptyTree<K, V> t = new NonEmptyTree<K, V>(key, value, 
					left.subTree(fromKey, toKey),
					right.subTree(fromKey, toKey));
			return t;
		}
	}
}