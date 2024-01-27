package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResult extends BasePage {
    @FindBy(css = "span[class='breadcrumb-leaf']")
    private WebElement searchSub;

    @FindBy(css = "h1[class='page-title']")
    private WebElement searchTitle;

    @FindBy(css = "article")
    private List<WebElement> listArticles;

    public String getSearchSubText() {
        return searchSub.getText();
    }

    public String getSearchTitleText() {
        return searchTitle.getText();
    }

    public List<String> getArticlesTitles() {
        return listArticles.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}