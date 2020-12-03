package Treaps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreapTest {

    @Test
    void add() {
        //generic add 1
        Treap<Integer> tr1 = new Treap<>();
        tr1.add(40,4);
        tr1.add(50,15);
        tr1.add(30,5);
        tr1.add(70,10);
        tr1.add(20,2);
        StringBuilder case1 = new StringBuilder();
        case1.append("(key=50, priority=15)\n");
        case1.append(" (key=30, priority=5)\n");
        case1.append("  (key=20, priority=2)\n");
        case1.append("   null\n");
        case1.append("   null\n");
        case1.append("  (key=40, priority=4)\n");
        case1.append("   null\n");
        case1.append("   null\n");
        case1.append(" (key=70, priority=10)\n");
        case1.append("  null\n");
        case1.append("  null\n");
        assertEquals(tr1.toString(), case1.toString());

        //try to add existing node (must fail)
        assertFalse(tr1.add(40));
        assertFalse(tr1.add(35,15));

        //generic add 2
        Treap<Integer> tr2 = new Treap<>();
        tr2.add(4,19);
        tr2.add(2,31);
        tr2.add(6,70);
        tr2.add(1,84);
        tr2.add(3,12);
        tr2.add(5,83);
        tr2.add(7,26);
        StringBuilder case2 = new StringBuilder();
        case2.append("(key=1, priority=84)\n");
        case2.append(" null\n");
        case2.append(" (key=5, priority=83)\n");
        case2.append("  (key=2, priority=31)\n");
        case2.append("   null\n");
        case2.append("   (key=4, priority=19)\n");
        case2.append("    (key=3, priority=12)\n");
        case2.append("     null\n");
        case2.append("     null\n");
        case2.append("    null\n");
        case2.append("  (key=6, priority=70)\n");
        case2.append("   null\n");
        case2.append("   (key=7, priority=26)\n");
        case2.append("    null\n");
        case2.append("    null\n");
        assertEquals(tr2.toString(), case2.toString());
    }

    @Test
    void delete() {
        Treap<Integer> tr1 = new Treap<>();
        tr1.add(40,4);
        tr1.add(50,15);
        tr1.add(30,5);
        tr1.add(70,10);
        tr1.add(20,2);

        //delete 50
        assertTrue(tr1.delete(50));
        StringBuilder case1 = new StringBuilder();
        case1.append("(key=70, priority=10)\n");
        case1.append(" (key=30, priority=5)\n");
        case1.append("  (key=20, priority=2)\n");
        case1.append("   null\n");
        case1.append("   null\n");
        case1.append("  (key=40, priority=4)\n");
        case1.append("   null\n");
        case1.append("   null\n");
        case1.append(" null\n");

        //try to delete element not in treap
        assertFalse(tr1.delete(69));
        assertEquals(tr1.toString(), case1.toString());

    }

    @Test
    void find() {
        Treap<Integer> tr1 = new Treap<>();
        tr1.add(40,4);
        tr1.add(50,15);
        tr1.add(30,5);
        tr1.add(70,10);
        tr1.add(20,2);

        assertTrue(tr1.find(30));
        assertFalse(tr1.find(-1));
        assertTrue(tr1.find(70));
    }

    @Test
    void testToString() {
        Treap<Integer> tr2 = new Treap<>();
        tr2.add(4,19);
        tr2.add(2,31);
        tr2.add(6,70);
        tr2.add(1,84);
        tr2.add(3,12);
        tr2.add(5,83);
        tr2.add(7,26);

        StringBuilder case2 = new StringBuilder();
        case2.append("(key=1, priority=84)\n");
        case2.append(" null\n");
        case2.append(" (key=5, priority=83)\n");
        case2.append("  (key=2, priority=31)\n");
        case2.append("   null\n");
        case2.append("   (key=4, priority=19)\n");
        case2.append("    (key=3, priority=12)\n");
        case2.append("     null\n");
        case2.append("     null\n");
        case2.append("    null\n");
        case2.append("  (key=6, priority=70)\n");
        case2.append("   null\n");
        case2.append("   (key=7, priority=26)\n");
        case2.append("    null\n");
        case2.append("    null\n");
        assertEquals(tr2.toString(), case2.toString());
    }
}