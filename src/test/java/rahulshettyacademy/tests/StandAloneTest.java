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

public class StandAloneTest {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");    
		
		//1) Login Code
		driver.findElement(By.id("userEmail")).sendKeys("shubhampach@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Shubham@111");
		driver.findElement(By.id("login")).click();
		
		//2) Product selection code
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));// waiting till 5 seconds
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); // Waiting till products load
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		// Prod return Element value or Null
		// findFirst() => used to select first element
		// orElse(null) => used to return NULL
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//3) Waiting code
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // we put this line above
		// Now provide the expectations
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // waiting till pop-up is load
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".overlay-container")));//waiting till pop-up is disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".overlay-container")))); // This line added for better performance
		
		//4) Click on Cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		//5) Confirming the cart product is our selected product or not
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));// ".anyMatch()" it search any match of product name
		Assert.assertTrue(match);
		
		//6) Click on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//7) In Select country section select India
		// Here we are using Action class
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		//8) Click on Place order
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 800)"); // Used for scroll Down
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit"))); // wait till button is visible
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		//9) Check the final text got after placing the order
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		driver.close();
		
		
		
		
		
	}

}
