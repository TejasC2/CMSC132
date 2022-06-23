import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	@Test
	public void testCons() {
		MyHashSet<String> s = new MyHashSet<String>();
		assertEquals(4,s.getCapacity());
	}
	@Test
	public void testIter() {
		MyHashSet<Integer> s = new MyHashSet<Integer>();
		String newStr = "";
		s.add(0);
		s.add(1);
		s.add(2);
		for(Integer x: s) {
			newStr += Integer.toString(x);
		}
		assertEquals("012", newStr);
	}
	@Test 
	public void remove() {
		MyHashSet<String> s = new MyHashSet<String>();
		s.add("bacon");
		s.remove("bacon");
		s.add("hi");
		String newStr = "";
		for(String str:s) {
			newStr += str;
		}
		assertEquals("hi",newStr);
	}
}
