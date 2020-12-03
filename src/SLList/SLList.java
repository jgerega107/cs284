package SLList;

import javax.swing.plaf.SliderUI;
import java.util.ArrayList;

public class SLList<E> {

    private static class Node<F> {
        // data fields
        private F data;
        private Node<F> next;
        // Constructor

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
    private Node<E> head;
    private int size;

    // Constructor
    SLList() {
        head = null;
        size = 0;
    }

    // Methods

    public E addFirst(E item) {
        head = new Node<E>(item, head);
        size++;
        return item;
    }


    public E addLast(E item) {
        if (head == null) {
            return this.addFirst(item);
        }
        Node<E> current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = new Node<>(item);
        size++;
        return item;
    }

    private Node<E> addLastRHelper(E item, Node<E> current){
        if(current == null){
            return new Node<E>(item);
        }
        else{
            current.next = addLastRHelper(item, current.next);
            return current;
        }
    }
    public E addLastR(E item){
        head = addLastRHelper(item, head);
        size++;
        return item;
    }
    private Node<E> addRHelper(E item, int index, Node<E> current){
        if(index == 0){
            return new Node<E>(item, current);
        }
        else{
            current.next = addRHelper(item, index-1, current.next);
            return current;
        }
    }
    public E addR(int index, E item){
        if(index < 0 || index > size){
            throw new IllegalArgumentException();
        }
        head = addRHelper(item, index, head);
        size++;
        return item;
    }

    public E add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            return this.addFirst(item);
        }

        Node<E> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        current.next = new Node<>(item, current.next);
        size++;
        return item;
    }

    public E removeFirst(E item) {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        E temp = head.data;
        head = head.next;
        size--;
        return temp;

    }

    public E removeLast(E item) {
        return null;
    }

    public E remove(int index, E item) {
        return null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        Node<E> current = head;
        s.append("[");
        while (current != null) {
            s.append(current.data.toString() + ",");
            current = current.next;
        }
        s.append("]");
        return s.toString();

    }

    public SLList<E> clone() {

        Node<E> current = head;
        Node<E> last = new Node<>(null);
        Node<E> newHead = last;

        while (current != null) {
            last.next = new Node<E>(current.data);
            current = current.next;
            last = last.next;
        }

        SLList<E> result = new SLList<E>();
        result.head = newHead.next;
        result.size = size;
        return result;
    }
    public Node<E> cloneRHelper(Node<E> current){
        if(current == null){
            return null;
        }
        else{
            return new Node<E>(current.data, cloneRHelper(current.next));
        }
    }
    public SLList<E> cloneR(){
        SLList<E> result = new SLList<E>();
        result.head = cloneRHelper(head);
        result.size = size;
        return result;
    }

    public void take(int n) {

        if (n == 0) {
            head = null;
            size = 0;
            return;
        }

        Node<E> current = head;
        int i = 0;

        while (current != null & i < n - 1) {
            current = current.next;
            i++;
        }

        if (current != null) {
            current.next = null;
            size = i + 1;
        }

    }

    private Node<E> takeRHelper(Node<E> current, int n){
        if(n == 0){
            return null;
        }
        else{
            current.next = takeRHelper(current.next, n-1);
            return current;
        }
    }
    public void takeR(int n){
        if(n < 0){
            throw new IllegalArgumentException();
        }
        else if(n>=size){
            return;
        }
        head = takeRHelper(head, n);
        size = n;
    }

    public void drop(int n) {
        if (n >= size) {
            head = null;
            size = 0;
            return;
        }
        Node<E> current = head;
        int i = 0;

        while (current != null & i < n) {
            current = current.next;
            i++;
        }

        if (current != null) {
            head = current;
            size = size - i;
        }
    }

    public boolean isSingleton() {
        return size == 1;
    }

    public boolean allNonNull() {
        Node<E> current = head;
        boolean isNonNull = true;
        while (current != null) {
            if (current.data == null) {
                isNonNull = false;
            }
            current = current.next;
        }
        return isNonNull;
    }

    public boolean mem(E el) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(el)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean nonDuplicates() {
        Node<E> current = head;
        while (current.next != null) {
            if (current.data.equals(current.next.data)) {
                return false;
            }
        }
        return true;
    }

    public SLList<E> append(SLList<E> l2) {
        SLList<E> list = clone();
        Node<E> current = list.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = l2.head;
        list.size+=l2.size;
        return list;
    }

    public Pair<SLList<E>, SLList<E>> splitAt(int n) {
        SLList<E> lc1 = this.clone();
        SLList<E> lc2 = this.clone();

        lc1.take(n);
        lc2.drop(n);
        return new Pair<SLList<E>, SLList<E>>(lc1, lc2);
    }

    public void reverseDumbAndStupid(){
        Node<E> current = head;
        Node<E> currentCopy = head;
        ArrayList<E> list = new ArrayList<>();
        while(current != null){
            list.add(0, current.data);
            current = current.next;
        }
        for(int i = 0; i < list.size(); i++){
            currentCopy.data = list.get(i);
            currentCopy = currentCopy.next;
        }
    }

    public void reverse(){
        Node<E> prev = null;
        Node<E> current = head;
        Node<E> next = null;
        while(current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public SLList<Boolean> areNull(){
        Node<E> current = head;
        Node<Boolean> booleanNode = new Node<Boolean>(null);
        Node<Boolean> booleanHead = booleanNode;
        SLList<Boolean> list = new SLList<Boolean>();
        while(current != null){
            booleanNode.next = new Node<>(current.data == null);
            booleanNode = booleanNode.next;
            current = current.next;
        }
        list.size = size;
        list.head = booleanHead.next;
        return list;
    }

    public boolean hasCycle(){
        if(head == null || head.next == null){
            return false;
        }
        Node<E> slow = head;
        Node<E> fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SLList<Integer> cl = new SLList<Integer>();
        cl.addFirst(1);
        cl.addLastR(12);
        cl.addLastR(12);
        cl.addLastR(12);
        cl.addLastR(12);
        cl.takeR(3);
        System.out.println(cl.toString());

    }
}
