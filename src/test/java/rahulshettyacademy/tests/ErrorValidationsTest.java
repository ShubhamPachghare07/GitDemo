package rahulshettyacademy.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		// Giving Wrong Id and Password
		landingPage.LoginApplication("shubhamp@gmail.com", "Shubham@123");
		// after giving the wrong ID and Password one popup is occured. we can get the path directly
		// Right click on popup-> select "Selectorhub" -> click on "copy relative cssselectorpath" and paste the path below
		// .ng-tns-c4-5.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
		
	}
	
	//@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingPage.LoginApplication("SP@gmail.com", "Shubham@321");
		productcatalogue.addProductToCart(productName);
		CartPage cartPage = productcatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
	
	@Test
	public void textForGIT() {
		System.out.println("Added by Asian user");
	}
	
	public void textForGIT2() {
		System.out.println("Added by US user");
	}

}
