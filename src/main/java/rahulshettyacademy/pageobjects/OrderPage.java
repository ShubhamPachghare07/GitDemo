package rahulshettyacademy.pageobjects;

import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstarctComponent;

public class OrderPage extends AbstarctComponent {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> OrderNames;
	
	public boolean verifyOrderDisplay(String productname) {
		boolean match = OrderNames.stream().anyMatch(order->order.getText().equalsIgnoreCase(productname));
		return match;
	}

}
