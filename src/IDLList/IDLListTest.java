package IDLList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDLListTest {

    @Test
    void add() {
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(5);
        assertEquals("[5]", list.toString());
        list.add(4);
        assertEquals("[4, 5]", list.toString());
        list.add(3);
        assertEquals("[3, 4, 5]", list.toString());
        list.add(2);
        assertEquals("[2, 3, 4, 5]", list.toString());
        list.add(1);
        assertEquals("[1, 2, 3, 4, 5]", list.toString());
    }

    @Test
    void addAtIndex() {
        IDLList<Integer> list = new IDLList<Integer>();
        //cases that should break add
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(69, 2));

        list.add(0, 4);
        assertEquals("[4]", list.toString());
        list.add(0, 42);
        assertEquals("[42, 4]", list.toString());
        list.add(1, 32);
        assertEquals("[42, 32, 4]", list.toString());
        list.add(list.size(), 10);
        assertEquals("[42, 32, 4, 10]", list.toString());
    }

    @Test
    void append() {
        IDLList<Integer> list = new IDLList<Integer>();
        list.append(5);
        assertEquals("[5]", list.toString());
        list.append(4);
        assertEquals("[5, 4]", list.toString());
        list.append(3);
        assertEquals("[5, 4, 3]", list.toString());
        list.append(2);
        assertEquals("[5, 4, 3, 2]", list.toString());
        list.append(1);
        assertEquals("[5, 4, 3, 2, 1]", list.toString());
    }

    @Test
    void get() {
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(2);
        list.add(9);
        list.add(3);
        list.add(4);
        list.add(1);
        assertEquals(4, list.get(1));
        assertEquals(9, list.get(3));
        assertEquals(2, list.get(4));

        //test corner cases
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(69));
    }

    @Test
    void getHead() {
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(2);
        list.append(9);
        list.add(3);
        list.append(4);
        list.add(54);
        list.add(3, 1);
        list.add(list.size(), 9);
        assertEquals(list.getHead(), 54);
        list.add(1);
        assertEquals(list.getHead(), 1);
        list.add(0, 9);
        assertEquals(list.getHead(), 9);
    }

    @Test
    void getTail() {
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(2);
        list.append(9);
        list.add(3);
        list.append(4);
        list.add(54);
        list.add(3, 1);
        list.add(list.size(), 9);
        assertEquals(list.getTail(), 9);
        list.append(6);
        assertEquals(list.getTail(), 6);
        list.add(list.size(), 32);
        assertEquals(list.getTail(), 32);
    }

    @Test
    void size() {
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(2);
        list.append(9);
        list.add(3);
        list.append(4);
        list.add(54);
        list.add(3, 1);
        list.add(list.size(), 9);
        assertEquals(list.size(), 7);
        list.append(6);
        assertEquals(list.size(), 8);
        list.add(list.size(), 32);
        assertEquals(list.size(), 9);
    }

    @Test
    void remove() {
        IDLList<Integer> list = new IDLList<Integer>();
        //check corner cases
        assertThrows(IllegalStateException.class, () -> list.remove());

        list.add(4);
        list.add(2);
        list.add(6);
        list.add(3);
        list.add(4);
        list.add(9);
        list.add(32);
        assertEquals(32, list.remove());
        assertEquals(9, list.remove());
        assertEquals(4, list.remove());
    }

    @Test
    void removeLast() {
        IDLList<Integer> list = new IDLList<Integer>();
        //check corner cases
        assertThrows(IllegalStateException.class, () -> list.removeLast());

        list.add(4);
        list.add(2);
        list.add(6);
        list.add(3);
        list.add(4);
        list.add(9);
        list.add(32);
        assertEquals(4, list.removeLast());
        assertEquals(2, list.removeLast());
        assertEquals(6, list.removeLast());
    }

    @Test
    void removeAt() {
        IDLList<Integer> list = new IDLList<Integer>();
        //check corner cases
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(69));

        list.add(4);
        list.add(2);
        list.add(6);
        list.add(3);
        list.add(4);
        list.add(9);
        list.add(32);
        assertEquals(2, list.removeAt(5));
        assertEquals(4, list.removeAt(2));
        assertEquals(32, list.removeAt(0));
    }

    @Test
    void removeElem() {
        IDLList<Integer> list = new IDLList<Integer>();
        list.add(4);
        list.add(2);
        list.add(6);
        assertTrue(list.remove(4));
        assertTrue(list.remove(2));
        assertFalse(list.remove(32));
    }

    @Test
    void testToString() {
        IDLList<Integer> list = new IDLList<Integer>();
        assertEquals("[]", list.toString());
        list.add(4);
        list.add(2);
        list.add(6);
        list.add(9);
        list.add(3);
        list.add(11);
        assertEquals("[11, 3, 9, 6, 2, 4]", list.toString());
        list.add(4, 32);
        assertEquals("[11, 3, 9, 6, 32, 2, 4]", list.toString());
    }
}