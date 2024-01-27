package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.LoggedMyAccount;
import pages.SearchResult;

import java.util.List;

public class SearchTests extends BaseTests {
    @Test
    public void isCorrectResultsDisplayedTest() {
        Header header = new Header();
        SearchResult searchResult = new SearchResult();

        String keyword = "dress";

        header
                .setSearchInput(keyword)
                .clickSearchButton();

        Assert.assertTrue(searchResult.getSearchTitleText().contains(keyword.toUpperCase()),
                "Title doesn't contain keyword");
        Assert.assertTrue(searchResult.getSearchSubText().contains(keyword),
                "Subtitle doesn't contain keyword");
        Assert.assertTrue(isAllResultsContainKeyword(searchResult.getArticlesTitles(), keyword),
                "Not all result contains keyword");
    }

    @Test
    public void searchWithEmptyKeywordFieldTest() {
        Header header = new Header();
        SearchResult searchResult = new SearchResult();

        header
                .setSearchInput("")
                .clickSearchButton();

        Assert.assertTrue(searchResult.getSearchSubText().
                equalsIgnoreCase("Search:"), "Subtitle isn't empty");
        Assert.assertTrue(searchResult.getSearchTitleText().
                equalsIgnoreCase("Search Results for:"), "Title isn't empty");
        Assert.assertTrue(searchResult.getArticlesTitles().get(0).contains("Checkout"),
                "Wrong article is displayed");
    }

    @Test
    public void searchWithKeywordWhichDoesNotFoundTest() {
        Header header = new Header();
        SearchResult searchResult = new SearchResult();
        LoggedMyAccount loggedMyAccount = new LoggedMyAccount();
        String keyword = "test123";

        header
                .setSearchInput(keyword)
                .clickSearchButton();

        Assert.assertTrue(searchResult.getSearchTitleText().equalsIgnoreCase("Nothing Found"),
                "Title is incorrect");
        Assert.assertTrue(searchResult.getSearchSubText().contains(keyword),
                "Subtitle doesn't contain keyword");
    }

    private boolean isAllResultsContainKeyword(List<String> articlesTitles, String keyword) {
        for (String s : articlesTitles)
            if (!s.toUpperCase().contains(keyword.toUpperCase()))
                return false;
        return true;
    }
}
