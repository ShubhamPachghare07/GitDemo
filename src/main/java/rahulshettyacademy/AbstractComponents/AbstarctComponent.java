package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstarctComponent {
	
	WebDriver driver;
	
	public AbstarctComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']") //for click on "Orders" option
	WebElement orderHeader;
	
	public void waitForElementTOAppear(By FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy)); // we pass FindBy on the position of "By.cssSelector(".mb-3")"
	}
	
	// This added for ErrorValidations
	public void waitForWebElementTOAppear(WebElement FindBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		// Sometime this disappear code taking extra 4 second wait still popup is gone to overcome this 
		Thread.sleep(1000); // we add this line and remove below lines. it is very fast
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));*/
		//"driver.findElement(By.cssSelector(".overlay-container"))" This is not FindBy this is WebElement so in parameter we have to pass the WebElement
	}

}
