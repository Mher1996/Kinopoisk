package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MoviePage {
    @FindBy(css = "[class='film-rating-value styles_rootPositive__ac3xv styles_rootInDark__35e_B']")
    private WebElement movieRatingFromPage;
    @FindBy(css = "[data-tid='67e47501']")
    private WebElement movieNameFromPage;
    @FindBy(css = "[data-tid='67e47501']")
    private WebElement movieProductionYear;


    public Double getMovieRatingFromPage(){
        return Double.parseDouble(movieRatingFromPage.getText());
    }
    public String getMovieNameFromPage(){
        return movieNameFromPage.getText().split("[(]")[0].strip();
    }
    public Integer getMovieProductionYear(){
        String movieYearText=movieProductionYear.getText().split("[(]")[1];
        movieYearText=movieYearText.split("[)]")[0];
        return Integer.parseInt(movieYearText);
    }

}
