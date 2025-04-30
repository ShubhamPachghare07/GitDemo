package rahulshettyacademy.pageobjects;

import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstarctComponent;

public class ProductCatalogue extends AbstarctComponent {
	 WebDriver driver;
	 
	 public ProductCatalogue(WebDriver driver) {
		 super(driver);
		 this.driver = driver;
		 PageFactory.initElements(driver, this);//because of ".initElements()" this method "@FindBy" got the "driver" knowledge
		 //".initElements()" is used to initialize the driver to all elements
	 }
	 
	 //List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	 @FindBy(css=".mb-3")
	 List<WebElement> products;
	 
	 //driver.findElement(By.cssSelector(".overlay-container"))
	 @FindBy(css=".overlay-container")
	 WebElement spinner;
	 
	 //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	 @FindBy(css="[routerlink*='cart']")
	 WebElement cartIcon;
	 
	 //driver.findElements(By.cssSelector(".cartSection h3"))
	 @FindBy(css=".cartSection h3")
	 WebElement cartItems;
	 
	 By productsBy = By.cssSelector(".mb-3"); // This product list taking path whose return type is "By"
	 By addToCart = By.cssSelector(".card-body button:last-of-type"); // add to cart path
	 By toastMessage = By.cssSelector("#toast-container");
	 
	 
	 public List<WebElement> getProductList() {
		 waitForElementTOAppear(productsBy);
		 return products; // Before returning we have to wait 
	 }
	
	 // Product filtering code
	 public WebElement getProductByName(String productName) {
		 WebElement prod = products.stream().filter(product->
		 product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		 return prod;
	 }
	 
	 // Add to cart
	 public void addProductToCart(String productName) throws InterruptedException {
		 WebElement prod = getProductByName(productName); //above getProductByName() method called here
 		 prod.findElement(addToCart).click();
 		 
 		//wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
 		 waitForElementTOAppear(toastMessage);
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".overlay-container"))));
 		 waitForElementToDisappear(spinner);
	 }
	 
	 public void goToCart() {
		 cartIcon.click();
	 }
	 
	//5) Confirming the cart product is our selected product or not
			

	
	
}
