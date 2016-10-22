package goVegan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;

import utils.Constants;
import utils.FacebookUtils;
import utils.SeleniumUtils;

public class MostViewedVideos {

	private WebDriver driver;

	public static void main(String[] args) {
		MostViewedVideos mostViewedVideos = new MostViewedVideos();
		mostViewedVideos.findMostViewedVideos();
	}

	private void findMostViewedVideos() {
		List<String> urls = loadUrls();
		SeleniumUtils.openBrowser();
		FacebookUtils.navigateToFacebook();
		FacebookUtils.login(Constants.EMAIL_GADI_VILNER, Constants.PASSWORD_GADI_VILNER);
		for (String url : urls) {
			SeleniumUtils.navigateToUrl(url);
		}
	}

	private List<String> loadUrls() {
		return new ArrayList<String>(Arrays.asList("https://www.facebook.com/garytvcom/"));
	}
}
