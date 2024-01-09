package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Header;
import pages.SearchResultPage;

import java.util.List;

public class SearchTests extends BaseTests {
    private SoftAssert softAssert;
    private SearchResultPage searchResultPage;
    private Header header;

    @Test
    public void isCorrectResultsDisplayed() {
        String keyword = "dress";
        search(keyword);

        softAssert.assertTrue(searchResultPage.getSearchTitleText().contains(keyword.toUpperCase()),
                "Title doesn't contain keyword");
        softAssert.assertTrue(searchResultPage.getSearchSubText().contains(keyword),
                "Subtitle doesn't contain keyword");
        softAssert.assertTrue(isAllResultsContainKeyword(searchResultPage.getArticlesTitles(), keyword),
                "Not all result contains keyword");
        softAssert.assertAll();
    }

    @Test
    public void searchWithEmptyKeywordField() {
        search("");

        softAssert.assertTrue(searchResultPage.getSearchSubText().
                equalsIgnoreCase("Search:"), "Subtitle isn't empty");
        softAssert.assertTrue(searchResultPage.getSearchTitleText().
                equalsIgnoreCase("Search Results for:"), "Title isn't empty");
        softAssert.assertTrue(searchResultPage.getArticlesTitles().get(0).contains("Checkout"),
                "Wrong article is displayed");
        softAssert.assertAll();
    }

    @Test
    public void searchWithKeywordWhichDoesNotFound() {
        String keyword = "test123";
        search(keyword);

        softAssert.assertTrue(searchResultPage.getSearchTitleText().equalsIgnoreCase("Nothing Found"),
                "Title is incorrect");
        softAssert.assertTrue(searchResultPage.getSearchSubText().contains(keyword),
                "Subtitle doesn't contain keyword");
        softAssert.assertAll();
    }

    private boolean isAllResultsContainKeyword(List<String> articlesTitles, String keyword) {
        for (String s : articlesTitles)
            if (!s.toUpperCase().contains(keyword.toUpperCase()))
                return false;
        return true;
    }

    private void search(String keyword) {
        softAssert = new SoftAssert();
        searchResultPage = new SearchResultPage(driver);
        header = new Header(driver);
        header.setSearchInput(keyword);
        header.clickSearchButton();
    }
}
