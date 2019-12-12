package main.java.com.pinionfromAtoB.www.pages.databean;

public class SearchFlightDataBean {

	public String Departure;
	public String Destination;
	public Boolean roundTrip;

	public String getDeparture() {
		return Departure;
	}

	public void setDeparture(String departure) {
		Departure = departure;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}

	public Boolean getRoundTrip() {
		return roundTrip;
	}
	public void setRoundTrip(Boolean roundTrip ) {
		this.roundTrip = roundTrip;
	}



}

