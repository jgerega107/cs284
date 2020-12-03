package Stacks;

public class Stack<E> implements StackInt<E> {
	
		private static class Node<F> {
			// data fields
			private F data;
			private Node<F> next;
			
			// Constructors

			public Node(F data, Node<F> next) {
				super();
				this.data = data;
				this.next = next;
			}
			public Node(F data) {
				super();
				this.data = data;
				this.next = null;
			}		
			
		}
		// data fields
		private Node<E> top;
		private int size;
		
		// Constructor
		Stack() {
			top = null;
			size = 0;
		}
		
		// Methods
		
		/**
		 * Pushes obj onto the top of the stack
		 * @param obj may be null
		 * @return returns obj
		 */
		public E push(E obj) {
			top = new Node<E>(obj,top);
			size++;
			return obj;
			
		}
		
		/**
		 * Returns the topmost element of the stack
		 * @return the topmost element
		 * @throws IllegalStateException is the stack is empty 
		 */
		public E peek() {
			if (top==null) {
				throw new IllegalStateException("peek: stack is empty");
			}
			return top.data;
			
		}
		
		/**
		 * Returns and removes topmost element on the stack
		 * @return returns topmost element
		 * @throws IllegalStateException is the stack is empty
		 */
		public E pop() {
			if (top==null) {
				throw new IllegalStateException("pop: stack is empty");
			}
			E temp = top.data;
			top = top.next;
			size--;
			return temp;
		}
		
		public boolean empty() {
			return top==null;
		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
			
			Node<E> current = top;
			s.append("[");
			while (current!=null) {
				s.append(current.data.toString()+",");
				current = current.next;
			}
			s.append("]");
			return s.toString();
		}
		
		public static void main(String[] args) {
			Stack<Integer> s = new Stack<>();
			
			for (int i=0; i<10; i++) {
				s.push(i);
			}
			
			System.out.println(s);
			System.out.println(s.pop());
			System.out.println(s);
			
		}
		
}
