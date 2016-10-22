package utils;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class SeleniumUtils {

	private static WebDriver driver;

	public static WebDriver openBrowser() {
		ChromeDriverManager.getInstance().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		// options.addArguments("--test-type");
		options.addArguments("-incognito");
		// options.addArguments("excludeSwitches", "ignore-certificate-errors");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
		capabilities.setCapability("chrome.binary", "C:/Program Files (x86)/Google/Chrome/Application/chrome");
		capabilities.setCapability("chrome.switches", Arrays.asList("--disable-javascript"));
		return SeleniumUtils.driver = FacebookUtils.driver = new ChromeDriver(capabilities);
	}

	public static void setText(String cssSelector, String text) {
		setText(findElement(cssSelector), text);
	}
	
	public static void setText(WebElement element, CharSequence...  text) {
		element.sendKeys(text);
	}

	public static void clickElement(WebElement element) {
		element.click();
	}

	public static WebElement clickElement(By by) {
		WebElement e = driver.findElement(by);
		e.click();
		return e;
	}

	public static WebElement findElement(String cssSelector) {
		return driver.findElement(By.cssSelector(Constants.Selctors.POST_TEXT_AREA));
	}

	public static List<WebElement> findElements(String cssSelector) {
		return driver.findElements(By.cssSelector(Constants.Selctors.POST_TEXT_AREA));
	}

	public static WebElement findElement(By by) {
		return driver.findElement(by);
	}

	public static WebElement clickElement(String cssSelector) {
		WebElement element = findElements(cssSelector).get(0);
		element.click();

		return element;
	}

	public static void navigateToUrl(String url) {
		driver.get(url);
	}

	public static void waitForElement(String cssSelector) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
	}
	
	public static WebDriver getDriver(){
		return driver;
	}
}