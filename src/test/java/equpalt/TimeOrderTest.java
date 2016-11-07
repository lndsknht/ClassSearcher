package equpalt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TimeOrderTest {
    private ISearcher searcher;

    @Before
    public void init() {
        searcher = new TrieSearcher();
        searcher.refresh(new String[]{"BAA", "BAB", "BBA", "BBB"}, new long[]{1, 0, 2, 3});
    }

    @Test
    public void testB() {
        String[] names = searcher.guess("B");
        assertTrue(names[0] == "BBB");
        assertTrue(names[1] == "BBA");
        assertTrue(names[2] == "BAA");
        assertTrue(names[3] == "BAB");
    }

    @Test
    public void testBB() {
        String[] names = searcher.guess("BB");
        assertTrue(names[0] == "BBB");
        assertTrue(names[1] == "BBA");
    }
}
