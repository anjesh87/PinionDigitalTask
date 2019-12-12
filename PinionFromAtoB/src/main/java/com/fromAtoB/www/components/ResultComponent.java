package main.java.com.fromAtoB.www.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class ResultComponent {
	final WebDriver driver;

	//Deutsche Bahn best deal
	@FindBy(xpath ="(//*[@id=\"Search\"]//div[@class=\"summary__header-logos\"]//img[@alt=\"Deutsche Bahn\"])[1]/ancestor::div[@class=\"summary\"]/following-sibling::div//button")
	public WebElement chooseDBFirstResult;


	public ResultComponent(WebDriver driver){
		this.driver = driver;
	}

}
