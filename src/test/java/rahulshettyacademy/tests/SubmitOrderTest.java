package rahulshettyacademy.tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		  
		//Creating object of Page object classes
		LandingPage landingPage = new LandingPage(driver);
		
		// driver.get("https://rahulshettyacademy.com/client");  on behalf of this line we use below "goTo()" method
		landingPage.goTo();
		
		//1) Login Code
		/*
		driver.findElement(By.id("userEmail")).sendKeys("shubhampach@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shubham@111");
		driver.findElement(By.id("login")).click();
		*/
		ProductCatalogue productcatalogue = landingPage.LoginApplication("shubhampach@gmail.com", "Shubham@111");
		
		
		//2) Product selection code
		// For Below 2 lines code we added in "AbstarctComponent.java" class for re-use
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));*/
		
		// Getting product List
		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		//ProductCatalogue productcatalogue = new ProductCatalogue(driver);// Remove this we added inside "LandingPage"
		productcatalogue.getProductList();
		
		// Product Filtering step 
		/* For below 2 lines we add a method in ProductCatalogue => getProductByName()
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);*/
		//productcatalogue.getProductByName(productName); 
		
		// Product add to cart
		/* For below 1 lines we add a method in ProductCatalogue => addProductToCart()
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();*/
		productcatalogue.addProductToCart(productName);// No need to call getProductByName() method becasue this method we call inside addProductToCart() method
		
		/*3) Waiting code also we added inside addProductToCart() method
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".overlay-container")))); */
		
		//4) Click on Cart. We add below line code inside "AbstarctComponet.java" beacsue "cart" Icon is common for all pages 
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		CartPage cartPage = productcatalogue.goToCartPage();
		
		//5) Confirming the cart product is our selected product or not
		/* Below 2 line code added in "CartPage.Java"
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));*/
		//CartPage cartPage = new CartPage(driver); // remove this we create object in "goToCartPage()"
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match); // Do not add Assertion in Page object class i.e "CartPage.java"
		
		//6) Click on checkout button
		/* Below 1 line code added in "CartPage.Java" 
		driver.findElement(By.cssSelector(".totalRow button")).click();*/
		cartPage.checkOut();
		
		//7) In Select country section select India
		/* Below 4 line code added in CheckOutPage
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();*/
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		checkOutPage.selectCountry("India");
		
		// Page scroll down
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 800)");
        
        //8) Click on Place order
        /* Below 2 line code added in CheckOutPage
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))); // wait till button is visible
		driver.findElement(By.cssSelector(".action__submit")).click();*/
        ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		
		//9) Check the final text got after placing the order
        /*Below 1 line code added in ConfirmationPage
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();*/
        String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		//driver.close();
		
		// For GITHUB
		System.out.println("End to end process completed");
		System.out.println("End to end process completed");
		System.out.println("End to end process completed");
		System.out.println("End to end process completed");
		
	}

}
