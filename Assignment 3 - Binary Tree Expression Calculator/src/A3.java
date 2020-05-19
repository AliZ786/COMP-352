// -----------------------------------------------------
// Assignment 3
// Question 1
// Written by: xxxxxxxxxx (xxxxxxx) & Mohammad Ali Zahir (40077619)
// -----------------------------------------------------
// The main class file for the binary tree expression calculator 

/**
 * Names: xxxxxxxxx (xxxxxxx) & Mohammad Ali Zahir (40077619)
// Comp 352- Winter 2020 Section S
// Assignment : #3 Q1
// Due Date: Wednesday April 1,2020
 */


import java.util.Stack;

public class A3 {

	/**
	 * Creating Nodes which contains left and right child
	 * @author Ali and Souvik
	 *
	 */

	class Node {
		int val;
		String s;
		Node left;
		Node right;

		public Node(int val, String s) {
			this.val = val;
			this.s = s;
			this.left = null;
			this.right = null;
		}

	}
	/**
	 * Method used to build the binary tree and see what it contains using stacks
	 * @param expression : expression which we need to add to stack
	 * @return element : returns the element which is build through a binary tree format 
	 */

	public Node build(String[] expression) {
		Stack<Node> stack = new Stack<Node>();
		int base = 0;
		int value = 0;

		for (int i = 0; i < expression.length; i++) {
			if (expression[i].equals("(")) {
				base += 10;
				continue;
			}
			if (expression[i].equals(")")) {
				base -= 10;
				continue;
			}
			value = prec(base, expression[i]); // finds the highest precedence to see which nodes to evaluate first

			Node node = new Node(value, expression[i]);
			//going through the tree nodes and evaluates the nodes properly
			while (!stack.isEmpty() && node.val <= stack.peek().val) {
				node.left = stack.pop();
			}
			if (!stack.isEmpty()) {
				stack.peek().right = node;
			}
			stack.push(node);

		}
		if (stack.isEmpty()) {
			return null;
		}
		Node element = stack.pop();
		while (!stack.isEmpty()) {
			element = stack.pop();
		}
		return element;
	}

	/**
	 * Method used to find the highest precedence
	 * @param base : index used to determine precedence
	 * @param s : string used to see what operation we are dealing with
	 * @return
	 */
	public int prec(int base, String s) {

		if (s.equals("+") || s.equals("-")) {
			return base + 1;
		}
		if (s.equals("*") || s.equals("/")) {
			return base + 2;
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * Evaluates the expression passed from the main, processes it and returns the result
	 * @param expression : what is passed in the stack
	 * @return
	 */

	public int evaluateExpression(String[] expression) {
		if (expression == null || expression.length == 0) {
			return 0;
		}
		Node root = build(expression);
		if (root == null) {
			return 0;
		}
		long result = Eval(root);
		return (int)result;
	}

	/**
	 * Evaluates the left and right child nodes and returns result
	 * @param node : the node that we want to evaluate 
	 * @return result : the answer of the operation
	 */
	public long Eval(Node node) {

		long result = 0;

		if (node == null) {
			return 0;
		}

		if (node.left == null && node.right == null) {
			return Long.parseLong(node.s);
		}

		long left = Eval(node.left);
		long right = Eval(node.right);

		if (node.left == null || node.right == null) {
			return node.left == null ? right : left;
		} 
		/**
		 * Switch statement to find out what operation is in the node and then execute it 
		 */
		switch (node.s) {
		case "*":
			result = left * right;
			break;
		case "/":
			result = left / right;
			break;
		case "+":
			result = left + right;
			break;
		case "-":
			result= left - right;
			break;
		}
		return result;
	}

}