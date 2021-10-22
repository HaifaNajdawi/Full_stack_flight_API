package com.skillstorm.air_flight.beans;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Flight {
	
	private int id;
	private int flight_number;
	private String airline_name;
	private String airport;
	private String tail_number;
	private int distance;
	private String departure_city;
	private String arrival_city;
	private Date date;
	private Time departure_time;
	private Time arrival;
	
	
	
	
	public Flight() {
		super();
	}
	
	
	
	
	public Flight(int id, int flight_number, String airline_name, String airport, String tail_number, int distance,
			String departure_city, String arrival_city, Date date, Time departure_time, Time arrival) {
		super();
		this.id = id;
		this.flight_number = flight_number;
		this.airline_name = airline_name;
		this.airport = airport;
		this.tail_number = tail_number;
		this.distance = distance;
		this.departure_city = departure_city;
		this.arrival_city = arrival_city;
		this.date = date;
		this.departure_time = departure_time;
		this.arrival = arrival;
	}




	public Flight(int flight_number, String airline_name, String airport, String tail_number, int distance,
			String departure_city, String arrival_city, Date date, Time departure_time, Time arrival) {
		super();
		this.flight_number = flight_number;
		this.airline_name = airline_name;
		this.airport = airport;
		this.tail_number = tail_number;
		this.distance = distance;
		this.departure_city = departure_city;
		this.arrival_city = arrival_city;
		this.date = date;
		this.departure_time = departure_time;
		this.arrival = arrival;
	}
	
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(int flight_number) {
		this.flight_number = flight_number;
	}
	public String getAirline_name() {
		return airline_name;
	}
	public void setAirline_name(String airline_name) {
		this.airline_name = airline_name;
	}
	public String getAirport() {
		return airport;
	}
	public void setAirport(String airport) {
		this.airport = airport;
	}
	public String getTail_number() {
		return tail_number;
	}
	public void setTail_number(String tail_number) {
		this.tail_number = tail_number;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public String getDeparture_city() {
		return departure_city;
	}
	public void setDeparture_city(String departure_city) {
		this.departure_city = departure_city;
	}
	public String getArrival_city() {
		return arrival_city;
	}
	public void setArrival_city(String arrival_city) {
		this.arrival_city = arrival_city;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}
	public Time getArrival() {
		return arrival;
	}
	public void setArrival(Time arrival) {
		this.arrival = arrival;
	}

	
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(airline_name, airport, arrival, arrival_city, date, departure_city, departure_time,
				distance, flight_number, tail_number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		return Objects.equals(airline_name, other.airline_name) && Objects.equals(airport, other.airport)
				&& Objects.equals(arrival, other.arrival) && Objects.equals(arrival_city, other.arrival_city)
				&& Objects.equals(date, other.date) && Objects.equals(departure_city, other.departure_city)
				&& Objects.equals(departure_time, other.departure_time) && distance == other.distance
				&& flight_number == other.flight_number && Objects.equals(tail_number, other.tail_number);
	}

	@Override
	public String toString() {
		return "Flight [flight_number=" + flight_number + ", airline_name=" + airline_name + ", airport=" + airport
				+ ", tail_number=" + tail_number + ", distance=" + distance + ", departure_city=" + departure_city
				+ ", arrival_city=" + arrival_city + ", date=" + date + ", departure_time=" + departure_time
				+ ", arrival=" + arrival + "]";
	}
	

	
	
	

}
