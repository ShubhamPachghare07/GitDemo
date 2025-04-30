package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstarctComponent;

public class LandingPage extends AbstarctComponent {
	 WebDriver driver;
	 
	 public LandingPage(WebDriver driver) {
		 super(driver);
		 this.driver = driver;
		 PageFactory.initElements(driver, this);//because of ".initElements()" this method "@FindBy" got the "driver" knowledge
		 //".initElements()" is used to initialize the driver to all elements
	 }
	 
	 /*WebElement userEmail = driver.findElement(By.id("userEmail")); // On the position of this line we can use PageFactory
	 	driver.findElement(By.id("userPassword")).sendKeys("Shubham@111");
		driver.findElement(By.id("login")).click();
		*/
	 
	 //PageFactory
	 @FindBy(id="userEmail") 
	 WebElement userEmails; // Local variable
	 
	 @FindBy(id="userPassword")
	 WebElement passwordEl; // Local variable
	 
	 @FindBy(id="login")
	 WebElement submit; // Local variable 
	 
	 @FindBy(css="[class*='flyInOut']")
	 WebElement errorMessage;
	 
	// On the position of "id" we can use "xpath","css","className",etc
	// To confirm the syntax click ctrl and click on @FindBy then FindBy.class is open 
	 
	 //Action Method  and This method we can call in our main class
	 public ProductCatalogue LoginApplication(String userEmail, String password) {
		 userEmails.sendKeys(userEmail); // Page Object do not hold the data it hold only variables
		 passwordEl.sendKeys(password);
		 submit.click();
		 ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		 return productcatalogue;
	 }
	 
	 public String getErrorMessage() {
		 waitForWebElementTOAppear(errorMessage);
		 return errorMessage.getText();
	}
	 
	 public void goTo() {
		 driver.get("https://rahulshettyacademy.com/client"); 
	 }
}
