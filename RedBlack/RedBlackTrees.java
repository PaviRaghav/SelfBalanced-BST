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
}
