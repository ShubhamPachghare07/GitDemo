package rahulshettyacademy.TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		//FileInputStream fis = new FileInputStream("G:\\MY SOFTWARES\\JAVA\\Automation Testing\\Rahul Programs\\SeleniumFrameworkDesign\\src\\main\\java\\rahushettyacademy\\resources\\GlobalData.properties");
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahushettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName  = prop.getProperty("browser");
		
		//if(browserName.equalsIgnoreCase("chrome"))
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); // used to full screen
		}
		else if(browserName.equalsIgnoreCase("edge")){
			// add edge code here
		}
		else if(browserName.equalsIgnoreCase("FireFox")){
			// add FireFox code here
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeTest(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		//LandingPage landingPage = new LandingPage(driver); // declare it above
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() { // We no need to call this method. It automatically call after @Test
		driver.close();
	}
	
	// Below method is converting JSON to HashMap String
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		// For File => import java.io.File; use this only
		File file = new File(filepath);
		String jsonContent = FileUtils.readFileToString(file,StandardCharsets.UTF_8);
		// Convert Json file String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){	
		});
		return data;
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver2) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
}
