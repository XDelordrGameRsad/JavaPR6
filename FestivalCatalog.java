package festival;

import java.util.ArrayList;
import java.util.List;

public final class FestivalCatalog {
    private final List<Movie> movies;

    public FestivalCatalog(List<Movie> movies) {
        this.movies = new ArrayList<>(movies);
    }

    // Возвращается копия списка: внешний код не сможет изменить порядок каталога.
    public List<Movie> getMovies() {
        return List.copyOf(movies);
    }

    public Movie findById(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    public List<Movie> searchByTitle(String query) {
        List<Movie> found = new ArrayList<>();
        String normalized = query.toLowerCase();

        for (Movie movie : movies) {
            if (movie.getTitle().toLowerCase().contains(normalized)) {
                found.add(movie);
            }
        }
        return found;
    }

    public List<Movie> findByDuration(int min, int max) {
        List<Movie> found = new ArrayList<>();
        for (Movie movie : movies) {
            if (movie.getDuration() >= min && movie.getDuration() <= max) {
                found.add(movie);
            }
        }
        return found;
    }
}
