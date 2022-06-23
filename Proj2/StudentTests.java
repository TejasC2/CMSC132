import org.junit.Test;

import java.util.Arrays;

public class StudentTests {
	static MinimumSnippet get(String [] doc, String [] terms) {
		return new MinimumSnippet(Arrays.asList(doc), Arrays.asList(terms));
	}

	@Test
	public void testNotFoundAny() {
		MinimumSnippet m = get(new String[] {"1", "2", "3"}, new String[] {"x"} );
		System.out.println(m);
	}
}
