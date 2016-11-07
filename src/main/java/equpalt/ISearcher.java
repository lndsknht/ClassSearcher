package equpalt;

public interface ISearcher {
    void refresh(String[] classNames, long[] modificationDates);
    String[] guess(String start);
}
