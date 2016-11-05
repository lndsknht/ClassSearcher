package equpalt;

import java.util.*;

public class Seeker implements ISearcher {

    public Seeker() {
    }

    private Map<String, Long> map = new TreeMap<>();

    public void refresh(String[] classNames, long[] modificationDates) {
        assert classNames.length == modificationDates.length : "Both arrays should be equal size!";

        for (int i = 0; i < classNames.length; i++) {
            map.put(classNames[i], modificationDates[i]);
        }

        map = sortByValue(map);
    }

    public String[] guess(String start) {
        Iterator<Map.Entry<String, Long>> iterator = map.entrySet().iterator();

        List<String> resultList = new ArrayList<>();

        while (iterator.hasNext() && resultList.size() < 12) {
            Map.Entry<String, Long> entry = iterator.next();
            if (entry.getKey().startsWith(start)) {
                resultList.add(entry.getKey());
            }
        }

        String[] resultArr = new String[resultList.size()];
        resultArr = resultList.toArray(resultArr);

        return resultArr;
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