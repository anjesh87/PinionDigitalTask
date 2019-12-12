package main.java.com.pinionfromAtoB.www.pages;

import main.java.com.fromAtoB.www.components.SearchComponent;
import main.java.com.pinionfromAtoB.www.pages.databean.SearchFlightDataBean;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SearchPage {
	final  WebDriver driver;
	WebDriverWait wait;

	//TodayDate
	public  void setDesiredDate() throws ParseException {


		DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		Calendar now = Calendar.getInstance(); // gets current date
		now.add(Calendar.DATE, 14);             // add fourteen days
		String firstDate = dateFormat.format(now.getTime());


		WebElement dt = driver.findElement(By.xpath("//li[contains(@class,'"+firstDate+"')]/button"));
		dt.click();

		Reporter.log("Starting date selected");
	}

	@FindBy(xpath="//input[@placeholder='From']")
	public WebElement textBox_Departure;


	//To TextBox
	@FindBy(xpath ="//input[@placeholder='To']")
	public WebElement textBox_Destination;

	@FindBy(xpath="//div[@class='modify_widget']")
	public SearchComponent component;

	//Filling Search flight 
	public void fillData(SearchFlightDataBean SearchFlight) throws InterruptedException
	{
		if(SearchFlight.getDeparture()!=null)
			textBox_Departure.sendKeys(SearchFlight.getDeparture());
		Reporter.log("departure flight selected");
		Thread.sleep(1000);
		if(SearchFlight.getDestination()!=null)
			textBox_Destination.sendKeys(SearchFlight.getDestination());
		Thread.sleep(1000);
		Reporter.log("SearchFlight Bean data- dep. and desti. are selected");
		//Popup_flightOnly.
	}

	public boolean validateHPHeading()
	{
		return driver.findElement(By.xpath("//p[@class='section-hero__heading__subtitle']")).isDisplayed();
	}

	public SearchPage(WebDriver driver) {
		this.driver = driver;

	}

}
