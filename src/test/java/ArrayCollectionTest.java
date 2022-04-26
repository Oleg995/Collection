import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

public class ArrayCollectionTest {
    private Collection<Integer> testInstance;

    @Before
    public void setUp() {
        testInstance = new ArrayCollection<>();
    }

    @Test
    public void testHasNextInIterator() {
        assertFalse(testInstance.iterator().hasNext());

        testInstance.add(65);
        assertTrue(testInstance.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextInIterator() {
        testInstance.iterator().next();
    }

    @Test
    public void testSizeWhenSizeIs0() {

        testInstance.add(1);
        testInstance.remove(1);
        assertEquals(0, testInstance.size());
    }

    @Test
    public void testSizeWhenSizeIs1() {
        testInstance.add(10);
        assertEquals(1, testInstance.size());
    }

    @Test
    public void testIsEmptyWhenEmpty() {
        testInstance.add(1);
        testInstance.remove(1);
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testIsEmptyWhenIsNoEmpty() {
        testInstance.add(10);
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testContains() {
        testInstance.add(1);
        testInstance.add(2);

        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(0));
    }

    @Test
    public void testIterator() {
        for (int i = 3; i < 15; i++) {
            testInstance.add(i);
        }
        int i = 0;
        System.out.println("\n testIterator");
        System.out.format("Size: %s\n", testInstance.size());
        for (final Integer iT : testInstance) {
            System.out.format("Element %s: %s \n", i++, iT);
        }
        if (i != testInstance.size()) {
            throw new RuntimeException("ElementsIterator does not working!");
        }
    }

    @Test
    public void testToArray() {
        for (int i = 1; i < 15; i++) {
            testInstance.add(i);
        }
        testInstance.remove(10);
        testInstance.remove(8);
        if (testInstance.toArray().length != testInstance.size()) {
            throw new RuntimeException("The returned array does not "
                    + "match the length of the "
                    + "collection.");
        }
    }

    @Test
    public void testAdd() {
        for (int i = -10; i < 15; i++) {
            testInstance.add(i);
        }

        assertEquals(25, testInstance.size());
        assertFalse(testInstance.isEmpty());

    }

    @Test
    public void testRemoveFirstElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.remove(1);

        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveCenterElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.remove(3);

        assertEquals(4, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveLastElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.remove(4);

        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());
    }

    @Test
    public void testRemoveEverElementFromBeginToEnd() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance.remove(1);
        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(2);
        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(3);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(4);
        assertEquals(0, testInstance.size());
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testRemoveEverElementFromEndToBegin() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance.remove(4);
        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(3);
        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(2);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(1);
        assertEquals(0, testInstance.size());
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testRemoveOneThroughOne() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance.remove(2);
        assertEquals(3, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(1);
        assertEquals(2, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(4);
        assertEquals(1, testInstance.size());
        assertFalse(testInstance.isEmpty());

        testInstance.remove(3);
        assertEquals(0, testInstance.size());
        assertTrue(testInstance.isEmpty());
    }

    @Test
    public void testRemoveRandomElement() {
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        Random rnd = new Random();

        while (!testInstance.isEmpty()) {
            int i = rnd.nextInt(9) + 1;
            testInstance.remove(i);
            assertFalse(testInstance.contains(i));
        }
    }

    @Test
    public void testContainsAll() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(2);
        testInstance2.add(1);

        assertTrue(testInstance.containsAll(testInstance2));
    }

    @Test
    public void testAddAll() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);

        testInstance2.add(3);
        testInstance2.add(4);
        testInstance.addAll(testInstance2);

        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
    }

    @Test
    public void testRemoveAll() {
        final ArrayCollection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);

        testInstance2.add(3);
        testInstance2.add(2);
        testInstance2.add(4);

        testInstance.removeAll(testInstance2);

        assertEquals(1, testInstance.size());
        assertTrue(testInstance.contains(1));
        assertFalse(testInstance.contains(2));
    }

    @Test
    public void testRetainAll3() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(2, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertFalse(testInstance.contains(4));
        assertFalse(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll2() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(4);
        testInstance.add(5);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(4));
        assertTrue(testInstance.contains(5));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testRetainAll1() {
        final Collection<Integer> testInstance2 = new ArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(3);
        testInstance.add(6);
        testInstance.add(7);
        testInstance.add(8);
        testInstance.add(9);

        testInstance2.add(3); //0 size1
        testInstance2.add(4); //1 size2
        testInstance2.add(5); //2 size3
        testInstance2.add(6); //3 size4

        testInstance.retainAll(testInstance2);

        assertEquals(4, testInstance.size());
        assertTrue(testInstance.contains(3));
        assertTrue(testInstance.contains(6));
    }

    @Test
    public void testClear() {
        testInstance.add(1);
        testInstance.add(1);

        testInstance.clear();

        assertTrue(testInstance.isEmpty());
        assertEquals(0, testInstance.size());
    }
}
