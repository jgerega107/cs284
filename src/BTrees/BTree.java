package BTrees;

import Queues.Queue;

public class BTree<E> {

	protected static class Node<F>{
		// data fields
		protected F data;
		protected Node<F> left;
		protected Node<F> right;
		
		// Constructors
		public Node(F data, Node<F> left, Node<F> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		public Node(F data) {
			super();
			this.data = data;
			this.left=null;
			this.right=null;
		}
		
		public Boolean is_leaf() {
			return left==null && right==null;
		}
		
		
	}
	// Data fields
	protected Node<E> root;
	protected int size;
	
	
	// Constructors
	BTree() {
		root=null;
		size=0;
	}
	
	BTree(E item) {
		root = new Node<E>(item);
		size = 1;
	}
	
	BTree(E item, BTree<E> left, BTree<E> right) {
		root = new Node<E>(item,left.root,right.root);
		size = 1 + left.size + right.size;
	}
	
	// Methods
	
	private int height_helper(Node<E> current) {
		if (current==null) {
			return 0;
		} else {
			return 1 + Math.max(height_helper(current.left), height_helper(current.right));
		}
	}
	
	public int height() {
		return height_helper(root);
	}
	
	private Boolean is_balanced_helper(Node<E> current) {
		if (current==null) {
			return true;
		} else {
			return Math.abs(height_helper(current.left)-height_helper(current.right))<=1 &&
					is_balanced_helper(current.left) &&
					is_balanced_helper(current.right);
		}
	}
	/** 
	 * A binary tree is said to be balanced if both of its subtrees are balanced and 
	 * the height of its left subtree differs from the height of its right subtree 
	 * by at most 1.
	 * @return true if the tree is balanced, false otherwise
	 */
	public Boolean is_balanced() {
		return is_balanced_helper(root);
	}
	
	private Boolean is_full_helper(Node<E> current) {
		if (current==null || current.is_leaf()) {
			return true;
		} else {
			return current.left!=null && current.right!=null && 
					is_full_helper(current.left) &&
					is_full_helper(current.right);
		}
		
	}
	
	/**
	 * A full binary tree is a binary tree where all nodes have 
	 * either 2 children or 0 children (the leaf nodes)
	 * @return
	 */
	public Boolean is_full() {
		return is_full_helper(root);
	}

	public Boolean is_perfect() {
		return size== Math.pow(2,height())-1;
	}

	/*
	private Boolean all_nulls(Queue<Node<E>> q) {
		
		while (!q.is_empty() && q.peek()==null) {
			q.poll();
		}
		
		return q.is_empty();
	}

	 */
	/** Hint: use a queue */
	/*
	public Boolean is_complete() {
		Queue<Node<E>> q = new Queue<>();
		
		q.offer(root);
		
		while (!q.is_empty()) {
			Node<E> n = q.poll();
			if (n==null) {
				return all_nulls(q);
			} else {
				q.offer(n.left);
				q.offer(n.right);
			}
			
		}
		// Never reached...
		return true;
	}
	 */
	
	
	private Boolean is_isomorphic(Node<E> current1, Node<E> current2) {
		return (current1==null && current2==null)
				||
			   (current1!=null && current2!=null && current1.data.equals(current2.data) 
				 &&
				 (is_isomorphic(current1.left,current2.left) && is_isomorphic(current1.right,current2.right)
				 || 
				 (is_isomorphic(current1.left,current2.right) && is_isomorphic(current1.right,current2.left))));
	}
	
	public Boolean is_isomorphic(BTree<E> t2) {
		return is_isomorphic(root,t2.root);
	}
	private StringBuilder toString(Node<E> current, int i) {
		StringBuilder r = new StringBuilder() ;
		for (int j=0; j<i; j++) {
			r.append("-");
		}
		
		if (current==null) {
			r.append("null\n");
		} else {
			r.append(current.data.toString()+"\n");
			r.append(toString(current.left,i+1));
			r.append(toString(current.right,i+1));
			
		}
		return r;
		
	}
	
	public String toString() {
		return toString(root,0).toString();
	}

	public static void main(String[] args) {

	}
	
 }
