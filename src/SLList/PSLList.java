package SLList;

import java.util.Arrays;

public class PSLList<E> {
    private static class Node<F> {
        // data fields
        private F data;
        private Node<F> next;
        private int priority;
        // Constructor

        public Node(F data, Node<F> next) {
            super();
            this.data = data;
            this.next = next;
            this.priority = 0;
        }

        public Node(F data, int priority, Node<F> next) {
            super();
            this.data = data;
            this.next = next;
            this.priority = priority;
        }

    }

    // data fields
    private Node<E> head;
    private int size;

    // Constructor
    PSLList() {
        head = null;
        size = 0;
    }

    public E add(E item, int priority){
        if(size == 0 || head.priority > priority){
            head = new Node<E>(item, priority, head);
        }
        else{
            Node<E> current = head;
            while(current.next != null && current.priority < priority){
                current = current.next;
            }
            current.next = new Node<E>(item, priority, current.next);
        }
        size++;
        return item;
    }

    public int top_priority(){
        if(size == 0){
            throw new IllegalStateException();
        }
        int curr_max = head.priority;
        Node<E> current = head;
        while(current != null){
            curr_max = Math.max(curr_max, current.priority);
            current = current.next;
        }
        return curr_max;
    }

    public Node<E>[] build_priority_index(){
        Node<E>[] arr = new Node[top_priority()+1];
        Node<E> current = head;
        while(current != null){
            arr[current.priority] = current;
            current = current.next;
        }
        return arr;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while(current != null){
            sb.append(current.data);
            sb.append(" ");
            current = current.next;
        }
        return sb.toString();
    }

    public static void main(String[] args){
        PSLList<Integer> list = new PSLList<Integer>();
        list.add(5, 2);
        System.out.println(list.toString());
        list.add(3, 5);
        System.out.println(list.toString());
        System.out.println(Arrays.toString(list.build_priority_index()));
    }

}
