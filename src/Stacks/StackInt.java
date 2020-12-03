package Stacks;

public interface StackInt<E> {
    E push(E obj);

    E pop();

    E peek();

    boolean empty();
}
