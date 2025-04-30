package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstarctComponent;

public class CheckOutPage extends AbstarctComponent {
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//driver.findElement(By.cssSelector("[placeholder='Select Country']")
	 @FindBy(css="[placeholder='Select Country']")
	 WebElement country;
	 
	 //driver.findElement(By.cssSelector(".action__submit")).click();
	 @FindBy(css=".action__submit")
	 WebElement submit;
	 
	 //driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	 @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	 WebElement selectCountry;
	 
	 By countryList = By.cssSelector(".ta-results");
	 By submitAction = By.cssSelector(".action__submit");
	 
	 //Action methods
	 public void selectCountry(String countryName) {
		 Actions a = new Actions(driver);
		 //a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Ind").build().perform();
		 a.sendKeys(country, countryName).build().perform();
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		 waitForElementTOAppear(countryList);
		 //driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		 selectCountry.click();
	 }
	 
	 public void schrollDown() {
			// Page scroll down
				JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollTo(0, 800)");
	 }
	 
	 public ConfirmationPage submitOrder() {
		 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
		 schrollDown();
		 waitForElementTOAppear(submitAction);
		 //driver.findElement(By.cssSelector(".action__submit")).click();
		 submit.click();
		 return new ConfirmationPage(driver);
	 }
	 
	 
	
	
	
}
