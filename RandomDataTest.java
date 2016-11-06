package equpalt;

import equpalt.ISearcher;
import equpalt.TrieSearcher;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RandomDataTest {
    private static final int classLimit = 100000;
    private static final String ABC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static ISearcher searcher;
    private static Map<String, Long> classData = new HashMap<>();

    @Before
    public void init() {
        if (searcher == null) {
            searcher = new TrieSearcher();
            String[] names = new String[classLimit];
            long[] dates = new long[classLimit];
            Random random = new Random();
            for (int i = 0; i < classLimit; i++) {
                names[i] = randomString(random.nextInt(32));
                dates[i] = new Date().getTime() - random.nextInt(10000);
                classData.put(names[i], dates[i]);
            }
            searcher.refresh(names, dates);
        }
    }

    private String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ABC.charAt(random.nextInt(ABC.length())));
        }
        return sb.toString();
    }

    @Test
    public void creation() {
        assertNotNull(searcher);
    }

    @Test
    public void searchA() {
        String[] names = searcher.guess("a");
        Long prevValue = null;
        for (int i = 0; i < names.length; i++) {
            Long value = classData.get(names[i]);
            assertNotNull(value);
            if (prevValue != null)
                assertTrue(prevValue >= value);
            prevValue = value;
        }
    }

    @Test
    public void searchAB() {
        String[] names = searcher.guess("ab");
        Long prevValue = null;
        for (int i = 0; i < names.length; i++) {
            Long value = classData.get(names[i]);
            assertNotNull(value);
            if (prevValue != null)
                assertTrue(prevValue >= value);
            prevValue = value;
        }
    }

    @Test
    public void searchABC() {
        String[] names = searcher.guess("abc");
        Long prevValue = null;
        for (int i = 0; i < names.length; i++) {
            Long value = classData.get(names[i]);
            assertNotNull(value);
            if (prevValue != null)
                assertTrue(prevValue >= value);
            prevValue = value;
        }
    }

    @Test
    public void bigSearch() {
        for (int i = 0; i < 100; i++) {
            assertNotNull(searcher.guess(randomString(3)));
        }
    }

    @Test
    public void thousandLoops() {
        for (int i = 0; i < 1000; i++) {
            assertNotNull(searcher.guess(randomString(3)));
        }
    }

    @Test
    public void tenThousandLoops() {
        for (int i = 0; i < 10000; i++) {
            assertNotNull(searcher.guess(randomString(3)));
        }
    }

    @Test
    public void hundredThousandLoops() {
        for (int i = 0; i < 100000; i++) {
            assertNotNull(searcher.guess(randomString(3)));
        }
    }
}
