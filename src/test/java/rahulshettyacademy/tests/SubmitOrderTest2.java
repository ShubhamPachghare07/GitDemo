package rahulshettyacademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest2 extends BaseTest {
	//String productName = "ZARA COAT 3";
	
	//public static void main(String[] args) throws InterruptedException { // Removing "Main" and adding "@Test" TestNG annotation
	//public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
		/* Below 4 line code added inside "BaseTest.Java" => is called Global declaration
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();*/ 
		
		/*
		LandingPage landingPage = new LandingPage(driver);//Adding this calling inside BaseTest.java
		landingPage.goTo();*/
		//LandingPage landingPage = launchApplication();  // Remove this line and add "@BeforeTest" above on launchApplication() method
		// Below line execute because of inheritance
		//ProductCatalogue productcatalogue = landingPage.LoginApplication(email, password);
		ProductCatalogue productcatalogue = landingPage.LoginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productName"));// No need to call getProductByName() method becasue this method we call inside addProductToCart() method
		
		CartPage cartPage = productcatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		AssertJUnit.assertTrue(match); // Do not add Assertion in Page object class i.e "CartPage.java"
		//cartPage.schrollDown();
		
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.selectCountry("India");
		checkOutPage.schrollDown();
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		//driver.close(); // This code also added in BaseTest
		
	}
	
	
	//@Test(dependsOnMethods= {"submitOrder"})  // "dependsOnMethods" because of this submitOrder execute first
	public void OrderHistoryTest() {
		String productName = "ZARA COAT 3";
		// To verify the In orders list our product is displayed or not => "ZARA COAT 3"
		ProductCatalogue productcatalogue = landingPage.LoginApplication("shubhampach@gmail.com", "Shubham@111");
		OrderPage orderpage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}
	
	/*1) Getting data normally
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"shubhampach@gmail.com","Shubham@111","ZARA COAT 3"},{"SP@gmail.com","Shubham@321","ZARA COAT 3"}};
	}*/
	
	/* Getting data by using HashMap
	@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "shubhampach@gmail.com");
		map.put("password", "Shubham@111");
		map.put("productName", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "SP@gmail.com");
		map1.put("password", "Shubham@321");
		map1.put("productName", "ZARA COAT 3");
		
		
		return new Object[][] {{map},{map1}};
	}*/
	
	// Getting data from JSON file and converting into HashMap
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		System.out.println(data.get(0));
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	

}
