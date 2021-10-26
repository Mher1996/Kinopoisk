package realtest;

import base.BaseTest;


import dataprovider.IndexDataProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.Helper;
import pages.KinoPoiskTopMovies;
import pages.MoviePage;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class KinoPoiskTopMoviesTest extends BaseTest {

    private List<Double> topMoviesRatings;
    private List<WebElement> topMoviesLinks;
    private List<String> topMovesNames;
    private List<Integer> topMoviesProductionYears;
    private MoviePage moviePage;
    private Helper helper;


    @BeforeTest
    public void setUp() {
        getDriver().get("https://www.kinopoisk.ru/lists/top250/?tab=all");
        final KinoPoiskTopMovies kinoPoiskTopMovies = PageFactory.initElements(getDriver(), KinoPoiskTopMovies.class);
        topMoviesRatings = kinoPoiskTopMovies.movieRating();
        topMoviesLinks = kinoPoiskTopMovies.movesLinks();
        topMovesNames = kinoPoiskTopMovies.moviesNames();
        topMoviesProductionYears=kinoPoiskTopMovies.getFilmsProductionYears();
        helper = new Helper();
    }

    @Test(testName = "Is Ratings Same", dataProvider = "Indexes", dataProviderClass = IndexDataProvider.class)
    public void moviesRatingsTest(int index) {

        helper.scrollIntoView(topMoviesLinks.get(index),false);
        helper.ctrlClick(getDriver(), topMoviesLinks.get(index));
        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1));
        moviePage = PageFactory.initElements(getDriver(), MoviePage.class);
        Double theMovieRating = moviePage.getMovieRatingFromPage();
        getDriver().close();
        getDriver().switchTo().window(tabs.get(0));


        assertEquals(theMovieRating, topMoviesRatings.get(index), "Ratings should be some in two pages ");

    }

    @Test(testName = "Is names same", dataProvider = "Indexes", dataProviderClass = IndexDataProvider.class)
    public void movesNamesTest(int movieIndex) {
        helper.scrollIntoView(topMoviesLinks.get(movieIndex),false);
        helper.ctrlClick(getDriver(), topMoviesLinks.get(movieIndex));
        List<String> tabs1 = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs1.get(1));
        moviePage = PageFactory.initElements(getDriver(), MoviePage.class);
        String movieRatingPage = moviePage.getMovieNameFromPage();
        getDriver().close();
        getDriver().switchTo().window(tabs1.get(0));

        assertEquals(movieRatingPage, topMovesNames.get(movieIndex));
    }

    @Test(testName = "Is moves production years same",
            dataProvider = "Indexes", dataProviderClass = IndexDataProvider.class)
    public void movesProductionYears(int yearsIndex) {
        helper.scrollIntoView(topMoviesLinks.get(yearsIndex),false);
        helper.ctrlClick(getDriver(), topMoviesLinks.get(yearsIndex));
        List<String> tabs2 = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs2.get(1));
        moviePage = PageFactory.initElements(getDriver(), MoviePage.class);
        Integer movieProductionYearInPage = moviePage.getMovieProductionYear();
        getDriver().close();
        getDriver().switchTo().window(tabs2.get(0));

        assertEquals(movieProductionYearInPage, topMoviesProductionYears.get(yearsIndex));

    }


    @Test(testName = "CheckingNames", priority = 10)
    public void topMoviesTest() {
        final KinoPoiskTopMovies kinoPoiskTopMovies = PageFactory.initElements(getDriver(), KinoPoiskTopMovies.class);
        final String zelyanayaMilya = "Зеленая миля";
        final String pabegIzShoushenka = "Побег из Шоушенка";
        assertEquals(kinoPoiskTopMovies.moviesNames().get(0), zelyanayaMilya);
        assertEquals(kinoPoiskTopMovies.moviesNames().get(1), pabegIzShoushenka);

    }

    @AfterSuite
    public void tearDown() {
        getDriver().quit();
    }


}

