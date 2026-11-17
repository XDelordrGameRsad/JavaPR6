package festival;

import java.util.List;

public final class FestivalConsole {
    public void run(FestivalCatalog catalog, boolean best) {
        RankingStrategy strategy = best
            ? new BestRatingStrategy()
            : new AverageRatingStrategy();

        RankingService service = new RankingService(strategy);
        List<RankedMovie> rows = service.rank(catalog.getMovies());
        printRanking(rows, service.ruleName());
    }

    public void printRanking(List<RankedMovie> rows, String ruleName) {
        new FestivalReport().print(rows, ruleName);
    }
}
