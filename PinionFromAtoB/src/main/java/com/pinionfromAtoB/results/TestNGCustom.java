package main.java.com.pinionfromAtoB.results;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNGCustom extends TestListenerAdapter {
	private int Count = 0;

	//Take screen shot only for failed test case
	@Override
	public void onTestFailure(ITestResult tr) {
		ScreenShot();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		//ScreenShot();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		//ScreenShot();
	}

	private void ScreenShot() {
		try {

			String NewFileNamePath;


			//Get the dir path
			File directory = new File (".");
			//System.out.println(directory.getCanonicalPath());

			//get current date time with Date() to create unique file name
			DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");

			//get current date time with Date()
			Date date = new Date();

			//To identify the system
			InetAddress ownIP=InetAddress.getLocalHost();

			NewFileNamePath = directory.getCanonicalPath()+ "/ScreenShots/"+ dateFormat.format(date)+"_"+ownIP.getHostAddress()+ ".png";
			System.out.println(NewFileNamePath);

			//Capture the screen shot of the area of the screen defined by the rectangle
			BufferedImage image=new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(image, "png", new File(NewFileNamePath));
			Count++;//Assign each screen shot a number
			NewFileNamePath = "<a href='"+NewFileNamePath+"'>ScreenShot"+ Count + "</a>";
			//Place the reference in TestNG web report 
			Reporter.log(NewFileNamePath);


		}
		catch (AWTException a) {
			a.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
