import org.testng.Assert;
import org.testng.annotations.Test;

public class VideoPageTest extends BaseTest  {
    @Test
    public void videoPage_SearchForVideo_ResultIsShown() {
        VideoPage videoPage = new VideoPage(getDriver());
        videoPage.open();
        SearchPage searchPage = videoPage.searchFor("GAMEPLAY DEMO");
        Assert.assertEquals(searchPage.getSearchResultsCount(), 1);
    }

    @Test
    public void videoPage_OpenPage_SectionsAreShown() {
        VideoPage videoPage = new VideoPage(getDriver());
        videoPage.open();
        Assert.assertEquals(videoPage.getSectionsCount(), 4);
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.SHOW));
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.STORIES));
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.FEATURED));
        Assert.assertTrue(videoPage.isSectionShown(VideoPage.VideoSection.BROWSE));
    }
}
