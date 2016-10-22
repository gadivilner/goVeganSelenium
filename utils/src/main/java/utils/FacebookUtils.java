package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FacebookUtils {

	static WebDriver driver;

	public static void navigateToFacebook() {
		SeleniumUtils.navigateToUrl("http://www.facebook.com/");
	}
	
	public static void login(String user, String password) {
		WebElement emailElement = driver.findElement(By.cssSelector(Constants.Selctors.FACEBOOK_EMAIL_INPUT));
		WebElement passwordElement = driver.findElement(By.cssSelector(Constants.Selctors.FACEBOOK_PASSWORD_INPUT));
//		WebElement loginButton = driver.findElement(By.cssSelector(Constants.Selctors.LOGIN_BUTTON));
		SeleniumUtils.setText(emailElement, user);
		SeleniumUtils.setText(passwordElement, password);
		passwordElement.submit();
	}
}
