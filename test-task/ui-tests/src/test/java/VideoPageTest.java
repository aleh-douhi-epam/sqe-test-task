import org.testng.Assert;
import org.testng.annotations.Test;

public class VideoPageTest extends BaseTest  {
    @Test
    public void videoPage_SearchForVideo_ResultIsShown() {
        // arrange
        VideoPage videoPage = new VideoPage(getDriver());
        videoPage.open();
        // act
        SearchPage searchPage = videoPage.searchFor("GAMEPLAY DEMO");
        // assert
        Assert.assertEquals(searchPage.getSearchResultsCount(), 1);
    }

    @Test
    public void videoPage_OpenPage_SectionsAreShown() {
        // arrange
        VideoPage videoPage = new VideoPage(getDriver());
        // act
        videoPage.open();
        // assert
        Assert.assertEquals(videoPage.getSectionsCount(), 4);
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.SHOW));
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.STORIES));
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.FEATURED));
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.BROWSE));
    }
}
