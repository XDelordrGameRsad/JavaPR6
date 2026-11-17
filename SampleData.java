package festival;

import java.util.List;

public final class SampleData {
    private SampleData() {
    }

    public static FestivalCatalog createCatalog() {
        // Порядок каталога намеренно отличается от порядка ID.
        return new FestivalCatalog(List.of(
            new Movie(3, "Тихий берег", 105, List.of(7, 7)),
            new Movie(1, "Северный свет", 110, List.of(8, 8)),
            new Movie(6, "Письмо домой", 95, List.of()),
            new Movie(2, "Последний трамвай", 120, List.of(10, 2)),
            new Movie(5, "Летний дождь", 98, List.of(9, 3)),
            new Movie(4, "Город на рассвете", 115, List.of(8, 6))
        ));
    }
}
