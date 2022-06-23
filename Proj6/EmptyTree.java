package searchTree;

import java.util.Collection;

/**
 * This class is used to represent the empty search tree: a search tree that
 * contains no entries.
 * 
 * This class is a singleton class: since all empty search trees are the same,
 * there is no need for multiple instances of this class. Instead, a single
 * instance of the class is created and made available through the static field
 * SINGLETON.
 * 
 * The constructor is private, preventing other code from mistakenly creating
 * additional instances of the class.
 * 
 */
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	/**
	 * This static field references the one and only instance of this class. We
	 * won't declare generic types for this one, so the same singleton can be used
	 * for any kind of EmptyTree.
	 */
	private static EmptyTree SINGLETON = new EmptyTree();

	public static <K extends Comparable<K>, V> EmptyTree<K, V> getInstance() {
		return SINGLETON;
	}

	/**
	 * Constructor is private to enforce it being a singleton
	 * 
	 */
	private EmptyTree() {
		// Nothing to do
	}

	// Empty tree has no elements --> return null
	public V search(K key) {
		return null;
	}

	// Creating a nonempty tree with the key and value inserted
	public NonEmptyTree<K, V> insert(K key, V value) {
		// Instantiates the left and right as empty
		Tree<K, V> left = EmptyTree.getInstance();
		Tree<K, V> right = EmptyTree.getInstance();
		NonEmptyTree<K, V> tree = 
				new NonEmptyTree<K, V>(key, value, left, right);
		return tree;
	}

	// nothing to delete --> return given tree
	public Tree<K, V> delete(K key) {
		return this;
	}

	// throw exception as tree is empty
	public K max() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	// throw exception as tree is empty
	public K min() throws TreeIsEmptyException {
		throw new TreeIsEmptyException();
	}

	// empty tree = size of 0
	public int size() {
		return 0;
	}

	// nothing to add, return nothing since method is void
	public void addKeysToCollection(Collection<K> c) {
		return;
	}

	// return given tree as tree is empty
	public Tree<K, V> subTree(K fromKey, K toKey) {
		return this;
	}
}