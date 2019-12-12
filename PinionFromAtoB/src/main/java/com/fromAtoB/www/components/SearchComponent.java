package main.java.com.fromAtoB.www.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.List;

public class SearchComponent {
	final WebDriver driver;

	Date TommoroDate;
	String TomorrowDate;
	int ageIndex;

	@FindBy(xpath = "//a[@class='navbar__menu-link navbar__menu-link--login js-navbar-link']")
	public WebElement login;

	@FindBy(xpath = "//input[@name='user-reception-email']")
	public WebElement email;

	@FindBy(xpath = "//input[@name='user-reception-password']")
	public WebElement password;

	@FindBy(xpath = "//button[@class='modal-buttons__button modal-buttons__button--sign']")
	public WebElement logInButton;

	@FindBy(xpath = "//input[@placeholder='From']")
	public WebElement textBox_Departure;

	@FindBy(xpath = "//input[@placeholder='To']")
	public WebElement textBox_Destination;

	@FindBy(xpath ="//button[@class='ssc-travellers__button']" )
	public WebElement passengerDetails;

	@FindBy(xpath = "//select[@class='travellers__age-selection']" )
	public WebElement passengerAge;

	@FindBy(xpath = "//*[@class='icon icon__check']")
	public WebElement compareResWithDB_checkBox;

	@FindBy(xpath="//button[@class='ssc-dates__confirm ssc-dates__confirm--focused']")
	public WebElement oneWayOnly_btn;

	@FindBy(xpath="//button[@class='ssc-travellers__button']")
	public WebElement travellers;

	@FindBy(xpath = "//div[@class='travellers__controller-adults']//button[@class='travellers__quantity-btn travellers__quantity-btn--add']")
	public WebElement addPassengers;

	@FindBy(xpath = "//div[@class='travellers__controller-adults']//button[@class='travellers__quantity-btn travellers__quantity-btn--remove']")
	public WebElement removePassengers;

	//No of Passengers
	@FindBy(xpath="(//div[@class='travellers__passengers-number'])[1]")
	public WebElement passengerCount;

	@FindBy(xpath="(//div[@class='travellers__passengers-number'])[2]")
	public WebElement youthCount;

	@FindBy(xpath = "//div[@class='travellers__controller-youth']//button[@class='travellers__quantity-btn travellers__quantity-btn--add']")
	public WebElement addYouth;

	@FindBy(xpath="//div[@class='travellers__controller-youth']//button[@class='travellers__quantity-btn travellers__quantity-btn--remove']")
	public WebElement removeYouth;

	@FindBy(xpath="(//div[@class='travellers__age-selection-container'])//select")
	public List<WebElement> ageSelector;

	//SearchButton_Trip
	@FindBy(xpath="//*[@class='icon icon__search']")
	public WebElement searchButton;

	//Start Date
	@FindBy(xpath="//*[@class='icon icon__calendar-departure']")
	public WebElement date_Starting;

	//Constructor
	public SearchComponent(WebDriver driver){
		this.driver = driver;
	}

	//Methods
	public void selectNumOfPassengers(int noOfPassengers){
		int countOfPassengers= Integer.valueOf(passengerCount.getText());
		if(noOfPassengers<countOfPassengers){
			for(int i=0;i<(countOfPassengers-noOfPassengers);i++){
				removePassengers.click();
			}
		}else if(noOfPassengers>countOfPassengers) {
			for(int i=0;i<(noOfPassengers-countOfPassengers);i++){
				addPassengers.click();
			}
		}
	}

	public void selectNumOfYouth(int noOfYouth){
		int countOfYouth= Integer.valueOf(youthCount.getText());
		if(noOfYouth<countOfYouth){
			for(int i=0;i<(countOfYouth-noOfYouth);i++){
				removeYouth.click();
			}
		}else if(noOfYouth>countOfYouth) {
			for(int i=0;i<(noOfYouth-countOfYouth);i++){
				addYouth.click();
			}
		}
		for(int i=0;i<noOfYouth;i++){
			Select ageSelect= new Select(ageSelector.get(i));
			int min=1,max=17;
			ageSelect.selectByIndex(i+((int)(Math.random() * ((max - min) + 1)) + min));
		}
	}


}
