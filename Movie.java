package festival;

import java.util.ArrayList;
import java.util.List;

public final class Movie {
    private final int id;
    private final String title;
    private final int duration;
    private final List<Integer> ratings = new ArrayList<>();

    public Movie(int id, String title, int duration, List<Integer> initialRatings) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID должен быть положительным");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Название не должно быть пустым");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Длительность должна быть положительной");
        }

        this.id = id;
        this.title = title;
        this.duration = duration;

        if (initialRatings != null) {
            for (int rating : initialRatings) {
                try {
                    addRating(rating);
                } catch (InvalidRatingException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public List<Integer> getRatings() {
        return List.copyOf(ratings);
    }

    public boolean hasRatings() {
        return !ratings.isEmpty();
    }

    public double averageRating() {
        if (!hasRatings()) {
            throw new IllegalStateException("У фильма пока нет оценок");
        }

        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }
        return (double) sum / ratings.size();
    }

    public void addRating(int rating) throws InvalidRatingException {
        if (rating < 1 || rating > 10) {
            throw new InvalidRatingException("Оценка должна быть от 1 до 10");
        }
        ratings.add(rating);
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + duration + " мин. | оценки: "
            + (hasRatings() ? ratings : "нет");
    }
}
