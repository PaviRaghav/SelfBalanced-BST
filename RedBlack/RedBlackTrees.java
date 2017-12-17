package SelfBalancing;

import Implementation.*;

/**
 * implementation of Red-Black trees. 1. nodes are either red or black. 2. root
 * node is black 3. leaves (NIL) are black 4. red nodes have 2 black children 5.
 * all simple paths from (all) node to its descendant leaves have same number of
 * black nodes
 * 
 * @author pavithraraghavan
 */

public class RedBlackTrees {
	class BST {
		int data;
		BST left, right, parent;
		boolean color; // red=true

		public BST(int i) {
			data = i;
			left = right = parent = null;
			color = true;
		}
	}

	BST root;

	public void leftRotate(BST root, BST node) {
		BST y = node.right;
		node.right = y.left;
		if (y.left != null)
			y.left.parent = node;
		y.parent = node.parent;
		if (node.parent == null)
			root = y;
		else if (node == node.parent.left)
			node.parent.left = y;
		else
			node.parent.right = y;
		y.left = node;
		node.parent = y;
	}

	public void rightRotate(BST root, BST node) {
		BST y = node.left;
		node.left = y.right;
		if (y.right != null)
			y.right.parent = node;
		y.parent = node.parent;
		if (node.parent == null)
			root = y;
		else if (node == node.parent.left)
			node.parent.left = y;
		else
			node.parent.right = y;
		y.right = node;
		node.parent = y;
	}

	public boolean search(int item) {
		BST node = root;
		while (node != null && node.data != item) {
			if (item < node.data)
				node = node.left;
			else
				node = node.right;
		}
		return node != null;
	}

	public void insert(int item) {
		BST newnode = new BST(item);
		BST node = root, parent = null;
		while (node != null) { //find a place to insert newnode
			if (item < node.data)
				node = node.left;
			else
				node = node.right;
			parent = node;
		}
		if (parent == null)
			root = newnode;
		else if (item < parent.data)
			parent.left = newnode;
		else
			parent.right = newnode;
		newnode.parent = parent;
		insert_fixup(newnode);
	}

	private void insert_fixup(BST node) {
		while (node.parent.color) {
			if (node.parent == node.parent.parent.left) {
				BST y = node.parent.parent.right; // uncle
				if (y.color) {
					node.parent.color = false; // parent = balck
					y.color = false; // uncle = black
					node.parent.parent.color = true; // grandparent = red
					node = node.parent.parent;
				} else {
					if (node == node.parent.right) {
						node = node.parent;
						leftRotate(root, node);
					}
					node.parent.color = false; // parent = black
					node.parent.parent.color = true; // grandparent = red
					rightRotate(root, node);
				}
			} else {
				BST y = node.parent.parent.left; // uncle
				if (y.color) {
					node.parent.color = false; // parent = balck
					y.color = false; // uncle = black
					node.parent.parent.color = true; // grandparent = red
					node = node.parent.parent;
				} else {
					if (node == node.parent.left) {
						node = node.parent;
						rightRotate(root, node);
					}
					node.parent.color = false; // parent = black
					node.parent.parent.color = true; // grandparent = red
					leftRotate(root, node);
				}
			}
			root.color = false; // root is black
		}
	}
	
	public void delete(int item) {
		
	}
}
