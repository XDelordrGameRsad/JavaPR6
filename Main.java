package festival;

import java.util.List;
import java.util.Scanner;

public final class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        FestivalCatalog catalog = SampleData.createCatalog();
        FestivalConsole festivalConsole = new FestivalConsole();

        while (true) {
            printMenu();
            int command = readInt("Выберите пункт: ");

            switch (command) {
                case 1 -> showCatalog(catalog);
                case 2 -> searchMovie(catalog);
                case 3 -> addRating(catalog);
                case 4 -> showByDuration(catalog);
                case 5 -> festivalConsole.run(catalog, false);
                case 6 -> festivalConsole.run(catalog, true);
                case 0 -> {
                    System.out.println("Работа завершена.");
                    return;
                }
                default -> System.out.println("Неизвестная команда.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""

            === КИНОФЕСТИВАЛЬ ===
            1. Показать каталог
            2. Найти фильм по названию
            3. Добавить оценку
            4. Просмотр по длительности
            5. Рейтинг по средней оценке
            6. Рейтинг по лучшей оценке
            0. Выход
            """);
    }

    private static void showCatalog(FestivalCatalog catalog) {
        for (Movie movie : catalog.getMovies()) {
            System.out.println(movie);
        }
    }

    private static void searchMovie(FestivalCatalog catalog) {
        System.out.print("Введите часть названия: ");
        String query = SCANNER.nextLine().trim();
        List<Movie> found = catalog.searchByTitle(query);

        if (found.isEmpty()) {
            System.out.println("Фильмы не найдены.");
        } else {
            found.forEach(System.out::println);
        }
    }

    private static void addRating(FestivalCatalog catalog) {
        int id = readInt("Введите ID фильма: ");
        Movie movie = catalog.findById(id);

        if (movie == null) {
            System.out.println("Фильм не найден.");
            return;
        }

        int rating = readInt("Введите оценку от 1 до 10: ");
        try {
            movie.addRating(rating);
            System.out.println("Оценка добавлена.");
        } catch (InvalidRatingException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void showByDuration(FestivalCatalog catalog) {
        int min = readInt("Минимальная длительность (мин.): ");
        int max = readInt("Максимальная длительность (мин.): ");

        if (min < 1 || max < min) {
            System.out.println("Некорректный диапазон.");
            return;
        }

        List<Movie> found = catalog.findByDuration(min, max);
        if (found.isEmpty()) {
            System.out.println("Фильмы не найдены.");
        } else {
            found.forEach(System.out::println);
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = SCANNER.nextLine();
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число.");
            }
        }
    }
}
