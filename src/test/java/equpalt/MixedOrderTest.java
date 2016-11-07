package equpalt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MixedOrderTest {
    private ISearcher searcher;

    @Before
    public void init() {
        searcher = new TrieSearcher();
        searcher.refresh(new String[] {"ABC", "BBB", "ABB", "AAA", "ABA", "BAA", "BAB"}, new long[] {0, 2, 0, 0, 1, 0, 0});
    }

    @Test
    public void testA() {
        String[] names = searcher.guess("A");
        assertTrue(names[0] == "ABA");
        assertTrue(names[1] == "AAA");
        assertTrue(names[2] == "ABB");
        assertTrue(names[3] == "ABC");
    }

    @Test
    public void testB() {
        String[] names = searcher.guess("B");
        assertTrue(names[0] == "BBB");
        assertTrue(names[1] == "BAA");
        assertTrue(names[2] == "BAB");
    }
}
