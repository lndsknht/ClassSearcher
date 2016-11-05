package equpalt;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.*;

public class Searcher implements ISearcher {

    private Trie<String, Long> trie = new PatriciaTrie<>();

    public Searcher() {
    }

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        assert classNames.length == modificationDates.length : "Both arrays should be equal size!";

        for (int i = 0; i < classNames.length; i++) {
            trie.put(classNames[i], modificationDates[i]);
        }
    }

    @Override
    public String[] guess(String start) {

        String[] resultArray;
        Map<String, Long> resultMap = sortByValue(trie.prefixMap(start));
        if (resultMap.size() >= 12) {
            resultArray = new String[12];
        } else {
            resultArray = new String[resultMap.size()];
        }

        Iterator<Map.Entry<String, Long>> iterator = resultMap.entrySet().iterator();

        for (int i = 0; i < resultArray.length; i++) {
            if (iterator.hasNext()) {
                resultArray[i] = iterator.next().getKey();
            }
        }

        return resultArray;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map)
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>()
        {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue().compareTo(o1.getValue()));
            }
        } );

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
