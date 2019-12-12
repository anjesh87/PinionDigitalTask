package main.java.com.pinionfromAtoB.www.suites;

import main.java.com.fromAtoB.www.components.ResultComponent;
import main.java.com.fromAtoB.www.components.SearchComponent;
import main.java.com.pinionfromAtoB.www.pages.ResultPage;
import main.java.com.pinionfromAtoB.www.pages.SearchPage;
import main.java.com.pinionfromAtoB.www.pages.databean.SearchFlightDataBean;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Listeners(main.java.com.pinionfromAtoB.results.TestNGCustom.class)
public class FromAtoBTripSuite {

	private WebDriver driver;
	public String TotalFare;
	public String projectPath;
	public String testName;
	private int pageloadTimeout;
	private int elementDetectionTimeout;
	public WebDriverWait wait;

	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}

	public WebDriver getDriver(){
		return driver;
	}

	@Test(priority = 1)
	public void verifyUnRegisteredUser(){
		waitForTitle("Train Tickets for Germany including € 19 Specials, Bus, Flight and Carpool in one Search | fromAtoB.com");

		//enter invalid credentials
		SearchComponent searchComponent = PageFactory.initElements(driver, SearchComponent.class);
		Reporter.log("testcase name "+testName+" is running");
		searchComponent.login.click();
		searchComponent.email.sendKeys("abcd@gmail.com");
		searchComponent.password.sendKeys("1234");
		searchComponent.logInButton.click();

	}

	@Test(priority = 2)
	public void verifyRegisteredUser() {
		waitForTitle("fromAtoB");

		//enter valid credentials
		SearchComponent searchComponent = PageFactory.initElements(driver, SearchComponent.class);
		Reporter.log("testcase name "+testName+" is running");
		driver.navigate().refresh();
		searchComponent.email.sendKeys("anjesh143@gmail.com");
		searchComponent.password.sendKeys("Welcome!123");
		searchComponent.logInButton.click();
	}

	private void waitForTitle(String expectedTitle) {
		wait= new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.titleIs(expectedTitle));
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}


	@Test(priority = 3)
	public void searchTrip() throws InterruptedException, ParseException {

		SearchComponent searchComponent = PageFactory.initElements(driver, SearchComponent.class);
		Reporter.log("testcase name "+testName+" is running");

		// Using data bean inserted value
		SearchFlightDataBean searchPageBean = new SearchFlightDataBean();

		searchPageBean.setDeparture("Hamburg, DE");
		searchPageBean.setDestination("Munich, DE");

		SearchPage searchPage = PageFactory.initElements(driver, SearchPage.class);

		searchPage.fillData(searchPageBean);

		searchComponent.date_Starting.click();
		searchPage.setDesiredDate();
		searchComponent.oneWayOnly_btn.click();
		searchComponent.travellers.click();
		searchComponent.selectNumOfPassengers(2);
		searchComponent.selectNumOfYouth(2);
		searchComponent.searchButton.click();
		Reporter.log("Searching Flights : Search Button clicked...");

		WebDriver popup=null;
		Iterator<String> windowIterator = driver.getWindowHandles()
				.iterator();
		while (windowIterator.hasNext()) {
			String windowHandle = windowIterator.next();
			popup = driver.switchTo().window(windowHandle);
			String title = popup.getTitle();
			System.out.println(title);
			if (title.contains("fromAtoB")) {
				driver.findElement(By.xpath("//button[contains(text(),'Got it')]")).click();
				Thread.sleep(20000);
				ResultComponent resultComponent = PageFactory.initElements(driver, ResultComponent.class);
				resultComponent.chooseDBFirstResult.click();
				boolean overViewPageIsDisplayed=driver.findElement(By.xpath("//header[@id='CheckoutNav']")).isDisplayed();
				Assert.assertEquals(overViewPageIsDisplayed,true);
				break;
			}
		}
	}


	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName();
		Reporter.log(testName);
	}

	@BeforeTest
	public void Setup() throws Exception {

		Properties prop = new Properties();
		InputStream input = null;
		input = new FileInputStream("config.properties");
		prop.load(input);

		String url = prop.getProperty("url");
		String browserType = prop.getProperty("browser");

		browserType = browserType.trim();
		projectPath = System.getProperty("user.dir");

		pageloadTimeout = 30;
		elementDetectionTimeout = 5;

		switch (browserType.toLowerCase()) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver",
						projectPath+"//drivers/chromedriver/chromedriver");

				//Path to your chromedriver.exe
				driver = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver",
						projectPath+"//drivers/gecodriver/geckodriver");

				driver = new FirefoxDriver();
				break;
			case "ie":
				System.setProperty("webdriver.ie.driver",
						projectPath+"//drivers/IEDriver/");

				driver = new InternetExplorerDriver();
				break;
			default:
				throw new Exception("Invalid Browser Type :: "+ browserType);
		}

		Reporter.log("Browser opening and Delete cookies");
		url = url.trim();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);

		driver.get(url);
		Reporter.log("Browser Maximize");

	}

	@AfterSuite
	public void tearDown(){
		driver.close();
		driver.quit();

	}
}
