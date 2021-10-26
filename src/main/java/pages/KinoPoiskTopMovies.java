package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KinoPoiskTopMovies {
    @FindBy(css = "[class='selection-list'] [class='desktop-rating-selection-film-item']")
    private List<WebElement> top50Films;
    @FindBy(css = "[class='rating__value rating__value_positive']")
    private List<WebElement> filmsRatings;
    @FindBy(css = "[class='selection-film-item-meta__name']")
    private List<WebElement> filmsNames;
    @FindBy(css = "[class='selection-film-item-meta selection-film-item-meta_theme_desktop'] a")
    private List<WebElement> filmsNamesClickable;
    @FindBy(css = "[class='selection-film-item-meta__original-name']")
    private List<WebElement> filmsProductionYears;


    public List<Integer> getFilmsProductionYears() {
        List<Integer> filmsProductionYear = new ArrayList<>();
        for (WebElement element : filmsProductionYears) {
            String [] splited=element.getText().split("[,]");
            String year =splited[splited.length-1].strip();
            filmsProductionYear.add(Integer.parseInt(year));
        }
        return filmsProductionYear;
    }

    public List<WebElement> getTop50Films() {
        return top50Films;
    }

    public List<WebElement> movesLinks() {
        return filmsNamesClickable;
    }

    public List<String> moviesNames() {
        List<String> topMoviesNames = new ArrayList<>();
        for (WebElement element : filmsNames) {
            String name = element.getText();
            topMoviesNames.add(name);
        }
        return topMoviesNames;
    }

    public List<Double> movieRating() {
        List<Double> ratings = new ArrayList<>();
        for (WebElement element : filmsRatings) {
            String rating = element.getText();
            ratings.add(Double.parseDouble(element.getText()));

        }
        return ratings;
    }

    public Map<String, Double> movieRatingMap() {
        Map<String, Double> moviesRatingsAndNames = new HashMap<>();
        List<String> movieName = moviesNames();
        List<Double> movieRating = movieRating();
        for (int i = 0; i < filmsRatings.size(); i++) {
            moviesRatingsAndNames.put(movieName.get(i), movieRating.get(i));
        }
        return moviesRatingsAndNames;

    }


}
