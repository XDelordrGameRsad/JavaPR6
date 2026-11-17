package festival;

public interface RankingStrategy {
    // Вызывается только для фильма, у которого есть оценки.
    double score(Movie movie);
    String name();
}
