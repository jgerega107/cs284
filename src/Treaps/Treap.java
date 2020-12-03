package Treaps;

import java.util.Random;
import java.util.Stack;

/**
 * Author: Jacob Gerega
 * I pledge my honor that I have abided by the Stevens Honor System.
 */

public class Treap<E extends Comparable<E>> {
    private Random priorityGenerator;
    public class Node<E> {
        public  E data;
        public int priority;
        public Node<E> left;
        public Node<E> right;

        public Node(E data, int priority){
            this.data = data;
            this.priority = priority;
            left = null;
            right = null;
        }

        public Node<E> rotateRight(){
            Node<E> newRoot = this.left;
            this.left = newRoot.right;
            newRoot.right = this;
            return newRoot;
        }

        public Node<E> rotateLeft(){
            Node<E> newRoot = this.right;
            this.right = newRoot.left;
            newRoot.left = this;
            return newRoot;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append("(key=");
            s.append(data);
            s.append(", ");
            s.append("priority=");
            s.append(priority);
            s.append(")");
            return s.toString();
        }
    }

    private Node<E> root;

    /**
     * Creates a new treap with random priority and an empty root.
     */
    public Treap() {
        priorityGenerator = new Random();
        root = null;
    }
    /**
     * Creates a new treap with a priority generated using a long. Contains an empty root.
     * @param seed Seed for generating a random priority.
     */
    public Treap(long seed) {
        priorityGenerator = new Random(seed);
        root = null;
    }

    /**
     * Adds a new element to the treap. Takes in a key. Cannot add duplicate keys or priorities.
     * @param key Element to be added to treap.
     * @return Whether or not the element was added.
     */
    public boolean add(E key) {
        return add(key, priorityGenerator.nextInt());
    }

    /**
     * Adds a new element to the treap. Takes in a key and a priority. Cannot add duplicate keys or priorities.
     * @param key Element to be added to treap.
     * @param priority Priority of given element.
     * @return Whether or not the element was added.
     */
    public boolean add(E key, int priority) {
        Node<E> curr = root;
        Stack<Node<E>> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr);
            if (curr.data.compareTo(key) == 0 || curr.priority == priority) {
                return false;
            } else {
                if (curr.data.compareTo(key) > 0) {
                    if (curr.left == null) {
                        curr.left = new Node<>(key, priority);
                        reheap(stack, curr.left);
                        return true;
                    } else {
                        curr = curr.left;
                    }
                } else {
                    if (curr.right == null) {
                        curr.right = new Node<>(key, priority);
                        reheap(stack, curr.right);
                        return true;
                    } else {
                        curr = curr.right;
                    }
                }
            }
        }
        root = new Node<>(key, priority);
        return true;
    }

    /**
     * Helper function to maintain properties of the treap after an add operation.
     * @param path Path to added element as a stack.
     * @param child Element added to treap.
     *
     */
    private void reheap(Stack<Node<E>> path, Node<E> child) {
        while (!path.isEmpty()) {
            Node<E> parent = path.pop();
            if (parent.priority < child.priority) {
                //if the left child is non null
                if (parent.left != null && parent.right == null) {
                    if (path.isEmpty()) {
                        root = parent.rotateRight();
                    } else if (path.peek().left == (parent)) {
                        path.peek().left = parent.rotateRight();
                    } else if (path.peek().right == (parent)) {
                        path.peek().right = parent.rotateRight();
                    }
                }
                //if the right child is non null
                else if (parent.right != null && parent.left == null) {
                    if (path.isEmpty()) {
                        root = parent.rotateLeft();
                    } else if (path.peek().right == (parent)) {
                        path.peek().right = parent.rotateLeft();
                    } else if (path.peek().left == (parent)) {
                        path.peek().left = parent.rotateLeft();
                    }
                }
                //if both children are non null
                else if (parent.right != null && parent.left != null) {
                    if (parent.right.priority > parent.left.priority) {
                        if (path.isEmpty()) {
                            root = parent.rotateLeft();
                        } else if (path.peek().left == (parent)) {
                            path.peek().left = parent.rotateLeft();
                        } else if (path.peek().right == (parent)) {
                            path.peek().right = parent.rotateLeft();
                        }
                    } else if (parent.left.priority > parent.right.priority) {
                        if (path.isEmpty()) {
                            root = parent.rotateRight();
                        } else if (path.peek().left == (parent)) {
                            path.peek().left = parent.rotateRight();
                        } else if (path.peek().right == (parent)) {
                            path.peek().right = parent.rotateRight();
                        }
                    }
                }
            }
        }
    }

    /**
     * Deletes an element from the treap.
     * @param key Element to be deleted.
     * @return Whether or not the element was deleted.
     */
    public boolean delete(E key) {
        Node<E> curr = root;
        //is it in treap to begin with?
        if(find(key)){
            //main search
            while(curr != null){
                curr = root;
                Stack<Node<E>> path;
                //is it still within the tree?
                if(find(key)){
                    path = new Stack<>();
                    //update location of our target
                    while(curr.data != key){
                        path.push(curr);
                        if(key.compareTo(curr.data) < 0){
                            curr = curr.left;
                        }
                        else if(key.compareTo(curr.data) > 0){
                            curr = curr.right;
                        }
                    }
                }else{
                    return true;
                }
                //begin deletion conditionals

                //curr is a leaf
                if(curr.left == null && curr.right == null){
                    if(path.peek().left == curr){
                        path.peek().left = null;
                    }
                    else if(path.peek().right == curr){
                        path.peek().right = null;

                    }
                    return true;
                }
                //root cases with children
                else if(curr.left == null && curr.right != null && path.isEmpty()){
                    root = curr.rotateLeft();
                }
                else if(curr.left != null && curr.right == null && path.isEmpty()){
                    root = curr.rotateRight();
                }
                else if(curr.left != null && curr.right != null && path.isEmpty()){
                    if(curr.left.priority > curr.right.priority){
                        root = curr.rotateRight();
                    }
                    else{
                        root = curr.rotateLeft();
                    }
                }
                //curr has one child
                else if(curr.left == null && curr.right != null){
                    if(path.peek().left == curr){
                        path.peek().left = curr.right;
                    }
                    else if(path.peek().right == curr){
                        path.peek().right = curr.right;
                    }
                }
                else if(curr.left != null && curr.right == null){
                    if(path.peek().left == curr){
                        path.peek().left = curr.left;
                    }
                    else if(path.peek().right == curr){
                        path.peek().right = curr.left;
                    }
                }
                //curr has two children
                else if(curr.left != null && curr.right != null){
                    if(path.peek().left == curr){
                        path.peek().left = curr.rotateRight();
                    }
                    else if(path.peek().right == curr){
                        path.peek().right = curr.rotateLeft();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Inner function for find.
     * @param key Element to be found.
     * @param root Treaps.Treap to be searched.
     * @return Whether or not element was found.
     */
    private boolean find(Node<E> root, E key){
        Node<E> curr = root;
        //search
        while(curr != null){
            if(key.compareTo(curr.data) == 0){
                return true;
            }
            else if(key.compareTo(curr.data) < 0){
                curr = curr.left;
            }
            else if(key.compareTo(curr.data) > 0){
                curr = curr.right;
            }
        }
        return false;
    }

    /**
     * Shows whether or not an element is contained within the treap.
     * @param key Element to be found.
     * @return Whether or not element was found.
     */
    public boolean find(E key){
        return find(root, key);
    }

    private StringBuilder toString(Node<E> current, int i) {
        StringBuilder r = new StringBuilder();
        for (int j = 0; j < i; j++) {
            r.append(" ");
        }

        if (current == null) {
            r.append("null\n");
        } else {
            r.append(current.toString());
            r.append("\n");
            r.append(toString(current.left, i + 1));
            r.append(toString(current.right, i + 1));
        }
        return r;

    }

    public String toString() {
        return toString(root, 0).toString();
    }

    public static void main(String[] args) {
        Treap<Integer> testTreap = new Treap<Integer>();

        //Testing add function
        testTreap.add(4,19);
        testTreap.add(2,31);
        testTreap.add(6,70);
        testTreap.add(1,84);
        testTreap.add(3,12);
        testTreap.add(5,83);
        testTreap.add(7,26);
        System.out.println(testTreap.delete(543));
        System.out.println(testTreap);
    }

}
