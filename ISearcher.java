package equpalt;

interface ISearcher {
    void refresh(String[] classNames, long[] modificationDates);
    String[] guess(String start);
}
