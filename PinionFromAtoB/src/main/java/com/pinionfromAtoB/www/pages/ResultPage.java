package main.java.com.pinionfromAtoB.www.pages;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ResultPage {
	final WebDriver driver;

	// Get Total Amount
	public void validateAmount(String a, String b , String TotalFare){

		//Sum of Two First Amount
		/*String s = a.replace("Rs.", "");
		s = s.replace(" ", "");
		s = s.replace(",", "");
		System.out.println(s);
		String t = b.replace("Rs.", "");
		t = t.replace(" ", "");
		t = t.replace(",", "");*/
		//System.out.println(t);
		double amt1 = convert(a);
		double amt2 =  convert(b);
		double Total = amt1 + amt2;
		System.out.println("Total"+Total);
		Reporter.log("Total of both flights " +Total);

		String totalFare = TotalFare.replace("Rs.", "");
		totalFare = totalFare.replace(" ", "");
		totalFare = totalFare.replace(",", "");
		System.out.println(totalFare);
		double totalamt = convert(totalFare);

		int retval =Double.compare(totalamt, Total);

		if(retval > 0) {
			System.out.println("totalamt is greater than Total");
			Reporter.log("total amt is greater than Total");
		}
		else if(retval < 0) {
			System.out.println("totalamt is less than Total");
			Reporter.log("total amt is less than Total");
		}
		else {
			System.out.println("totalamt is equal to Total");
			Reporter.log("totalamt is equal to Total");
		}
		
	}
	
	public  double convert(String amount){
		String s = amount.replace("Rs.", "");
		s = s.replace(" ", "");
		s = s.replace(",", "");
		System.out.println(s);
		double amt = Double.valueOf(s);
		return amt;
	}

	public ResultPage(WebDriver driver){
		this.driver = driver;
	}

}
