package Queues;

public class Queue<E> {

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

    // date fields
    private Node<E> front;
    private Node<E> rear;
    private int size;

    // Constructor
    Queue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Methods

    /**
     * Adds a new element to the rear of the queue
     *
     * @param item to be added
     * @return item that was added
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

    /**
     * Inspects the item at the front
     *
     * @return the element at the front
     * @throws IllegalStateException if the queue is empty
     */
    public E peek() {
        if(front == null){
        	throw new IllegalArgumentException("peek: Queue is empty");
		}
        else{
        	return front.data;
		}
    }

    /**
     * Inspects and removes the item at the front
     *
     * @return the item at the front
     * @throws IllegalStateException if the queue is empty
     */
    public E poll() {
		Node<E> formerFront = front;
        if(front == null){
        	throw new IllegalArgumentException("poll: Queue is empty");
		}
        else{
        	front = front.next;
        	size--;
		}
        return formerFront.data;
    }

    /**
     * Size of the queue
     *
     * @return
     */
    public int size() {
        return size;
    }

    public String toString(){
    	StringBuilder sb  = new StringBuilder();
    	Node<E> current = front;
    	while(current != null){
    		sb.append(current.data);
			sb.append(" ");
    		current = current.next;
		}
    	return sb.toString();
	}

	public static void main(String[] args){
    	Queue<Integer> q = new Queue<>();
    	q.offer(1);
    	q.offer(3);
    	System.out.println(q);
	}


}
