package Queues;

public class PQueue<E extends Comparable<E>> {


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
			}
			
			
		}

		// data fields
		private Node<E> front;
		private Node<E> rear;
		private int size;
		
		// Constructor
		PQueue() {
			front=null;
			rear=null;
			size=0;
		}
		
		// Methods
		
		/**
		 * Adds the new item at the rear of the queue
		 * @param item is the item to be added
		 * @return the item
		 */
        public E offer(E item) {
            if(size == 0){
                front = new Node<>(item);
                rear = front;
            }
            else{
                rear.next = new Node<>(item);
                rear = rear.next;
            }
            size++;
            return item;
        }
		public E offerp(E item) {
			if (front==null) {
				front = new Node<>(item);
				rear=front;
			}
			else if(front.data.compareTo(item) > 0){ //item goes first
                front = new Node<>(item, front);
            }
			else{
			    Node<E> current = front;
			    while (current.next != null && current.next.data.compareTo(item) < 0){
			        current = current.next;
                }
			    if(current.next == null){ //item goes last
			        current.next = new Node<>(item);
			        rear = current.next;
                }
			    else{
			        current.next = new Node<>(item, current.next);
                }
            }
			size++;
			return item;
		}
		
		/**
		 * Returns the item at the front
		 * @return item at the front
		 * @throws IllegalStateException if the queue is empty
		 */
		public E peek() {
			if (front==null) {
				throw new IllegalStateException("peek: queue is empty");
			}
			return front.data;
		}
		
		/** 
		 * Returns the item at the front and removes it
		 * @return item at the front
		 * @throws IllegalStateException is the queue is empty
		 */
		public E poll() {
			if (front==null) {
				throw new IllegalStateException("poll: queue is empty");
			}
			E temp = front.data;
			front = front.next;
			size--;
			return temp;
		}
		
		/**
		 * Returns the size of the queue
		 * @return
		 */
		public int size() {
			return size;
		}
		
		public String toString() {
			StringBuilder s = new StringBuilder();
			Node<E> current = front;
			s.append("front<= ");
			while (current!=null) {
				s.append(current.data.toString()+",");
				current=current.next;
			}
			return s.toString()+"<= rear";
					
		}
		
		public static void main(String[] args) {
			PQueue<Integer> q = new PQueue<>();
			
			for (int i=0;i<10; i++) {
				q.offer(i);
			}
			
			System.out.println(q);
			q.offerp(4);
			System.out.println(q);
			
		}

}
