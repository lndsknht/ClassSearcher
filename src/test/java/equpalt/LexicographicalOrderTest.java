package equpalt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LexicographicalOrderTest {
    private ISearcher searcher;

    @Before
    public void init() {
        searcher = new TrieSearcher();
        searcher.refresh(new String[] {"ABC", "ABB", "AAA", "ABA"}, new long[] {0, 0, 0, 0});
    }

    @Test
    public void testA() {
        String[] names = searcher.guess("A");
        assertTrue(names[0] == "AAA");
        assertTrue(names[1] == "ABA");
        assertTrue(names[2] == "ABB");
        assertTrue(names[3] == "ABC");
    }

    @Test
    public void testAA() {
        String[] names = searcher.guess("AA");
        assertTrue(names[0] == "AAA");
    }
}
