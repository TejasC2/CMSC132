import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;

/**
 * Some test cases distributed as part of the project.
 */
public class StudentTests {

	
	/**
     * Utility function Given a String, return a list consisting of one
     * character Strings
     */
    public static List<String> makeListOfCharacters(String s) {
        List<String> lst = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++)
            lst.add(s.substring(i, i + 1));
        return lst;
    }

    /**
     * Test adding to a Bag
     * 
     */
    @Test
    public void testBagAddSizeUniqueElements() {
        List<String> lst = makeListOfCharacters("aaabbc");
        HeavyBag<String> b = new HeavyBag<String>();
        b.addAll(lst);
        assertEquals(6, b.size());
        assertEquals(3, b.uniqueElements().size());
    }

    /**
     * Test checking if a Bag contains a key, and the count for each element
     * 
     */
    @Test
    public void testBagContainsAndCount() {
        List<String> lst = makeListOfCharacters("aaabbc");
        HeavyBag<String> b = new HeavyBag<String>();
        b.addAll(lst);
        assertEquals(6, b.size());
        assertEquals(3, b.uniqueElements().size());
        assertTrue(b.contains("a"));
        assertTrue(b.contains("b"));
        assertTrue(b.contains("c"));
        assertFalse(b.contains("d"));
        assertEquals(3, b.getCount("a"));
        assertEquals(2, b.getCount("b"));
        assertEquals(1, b.getCount("c"));
        assertEquals(0, b.getCount("d"));
    }
    @Test
    public void testBagHashCode() {
    	List<String> lst = makeListOfCharacters("ababa");
    	List<String> lst2 = makeListOfCharacters("ababa");
    	List<String> lst3 = makeListOfCharacters("ababababa");
    	HeavyBag<String> b1 = new HeavyBag<String>();
    	HeavyBag<String> b2 = new HeavyBag<String>();
    	HeavyBag<String> b3 = new HeavyBag<String>();
    	b1.addAll(lst);
    	b2.addAll(lst2);
    	b3.addAll(lst3);
    	assertEquals(b1.hashCode(),b2.hashCode());
    	assertFalse(b1.hashCode() == b3.hashCode());
    }
    @Test
    public void testEquals() {
    	List<String> lst = makeListOfCharacters("ababa");
    	List<String> lst2 = makeListOfCharacters("ababa");
    	List<String> lst3 = makeListOfCharacters("ababababa");
    	HeavyBag<String> b1 = new HeavyBag<String>();
    	HeavyBag<String> b2 = new HeavyBag<String>();
    	HeavyBag<String> b3 = new HeavyBag<String>();
    	b1.addAll(lst);
    	b2.addAll(lst2);
    	b3.addAll(lst3);
    	assertTrue(b1.equals(b2));
    	assertFalse(b1.equals(b3));
    	assertTrue(b1.equals(b1));
    }
    @Test
    public void testIter() {
    	List<String> lst = makeListOfCharacters("ababab");
    	HeavyBag<String> b1 = new HeavyBag<String>();
    	b1.addAll(lst);
    	Iterator<String> iter = b1.iterator();
    	iter.remove();
    	while(iter.hasNext()){
    		System.out.print(iter.next());
    	}

    }
    @Test
    public void testRandom() {
    	List<String> lst = makeListOfCharacters("babab");
    	HeavyBag<String> b1 = new HeavyBag<String>();
    	b1.addAll(lst);
    	Random r = new Random();
    	assertEquals(b1.choose(r),"a");
    	
    }

 
}