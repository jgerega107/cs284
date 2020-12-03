package IDLList;

import java.util.ArrayList;

/*
Jacob Gerega
I pledge my honor that I have abided by the Stevens Honor System.
3/8/2020
 */

public class IDLList<E> {
    //Node class
    public static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E elem) {
            super();
            data = elem;
            next = null;
            prev = null;
        }

        public Node(E elem, Node<E> prev, Node<E> next) {
            super();
            data = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    //IDList
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;

    public IDLList() {
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<>();
    }


    public boolean add(E elem) {
        if (size == 0) {
            Node<E> newElem = new Node<>(elem);
            indices.add(0, newElem);
            head = newElem;
            tail = newElem;
        } else {
            Node<E> newElem = new Node<>(elem, null, head);
            head.prev = newElem;
            indices.add(0, newElem);
            head = newElem;
        }
        size++;
        return true;
    }

    public boolean add(int index, E elem) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        } else if (size == 0 || index == 0) {
            add(elem);
        } else if (index == size) {
            append(elem);
        } else {
            Node<E> prev = indices.get(index - 1);
            Node<E> next = indices.get(index);
            Node<E> current = new Node<>(elem, prev, next);
            prev.next = current;
            next.prev = current;
            indices.set(index - 1, prev);
            indices.set(index, current);
            indices.add(index + 1, next);
            size++;
        }
        return true;
    }

    public boolean append(E elem) {
        if (size == 0) {
            add(elem);
        } else {
            Node<E> last = indices.get(indices.size() - 1);
            Node<E> newLast = new Node<>(elem, last, null);
            last.next = newLast;
            tail = newLast;
            size++;
            indices.add(newLast);
        }
        return true;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return indices.get(index).data;
    }

    public E getHead() {
        return head.data;
    }

    public E getTail() {
        return tail.data;
    }

    public int size() {
        return size;
    }

    public E remove() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        } else {
            Node<E> currHead = head;
            head = head.next;
            head.prev = null;
            size--;
            indices.remove(0);
            return currHead.data;
        }
    }

    public E removeLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        } else {
            Node<E> currTail = tail;
            tail = tail.prev;
            tail.next = null;
            size--;
            indices.remove(indices.size() - 1);
            return currTail.data;
        }
    }

    public E removeAt(int index) {
        if (index < 0 || index >= indices.size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        } else if (index == 0) {
            return remove();
        } else if (index == indices.size() - 1) {
            return removeLast();
        } else {
            Node<E> currElem = indices.get(index);
            Node<E> prev = indices.get(index - 1);
            Node<E> next = indices.get(index + 1);
            next.prev = prev;
            prev.next = next;
            indices.set(index - 1, prev);
            indices.set(index + 1, next);
            indices.remove(index);
            size--;
            return currElem.data;
        }
    }

    public boolean remove(E elem) {
        for (int i = 0; i < indices.size(); i++) {
            if (indices.get(i).data.equals(elem)) {
                if (i == 0) {
                    remove();
                    return true;
                } else {
                    removeAt(i);
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        sb.append("[");
        if (size != 0) {
            while (current.next != null) {
                sb.append(current.data);
                sb.append(", ");
                current = current.next;
            }
            sb.append(current.data);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        IDLList<Integer> list = new IDLList<Integer>();
        list.append(5);
        list.append(2);
        list.append(9);
        list.append(12);
        System.out.println(list);
        System.out.println(list.getHead());
        System.out.println(list.getTail());
    }
}
