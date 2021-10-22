package com.skillstorm.air_flight.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;

import com.skillstorm.air_flight.beans.Flight;

public class FlightDAO {

	private static final String url = "jdbc:mysql://localhost:3306/flight-api";
	private static final String username = "root";
	private static final String password = "haifa3012";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
	}

	public Flight insert(Flight flight) throws SQLException {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "INSERT INTO flight (flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival) values (?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, flight.getFlight_number());
			stmt.setString(2, flight.getAirport());
			stmt.setString(3, flight.getAirline_name());
			stmt.setString(4, flight.getTail_number());
			stmt.setInt(5, flight.getDistance());
			stmt.setString(6, flight.getDeparture_city());
			stmt.setString(7, flight.getArrival_city());
			stmt.setDate(8, flight.getDate());
			stmt.setTime(9, flight.getDeparture_time());
			stmt.setTime(10, flight.getArrival());

			stmt.executeUpdate();

			ResultSet return_AI = stmt.getGeneratedKeys();
			return_AI.next();
			int setId = return_AI.getInt(1);
			flight.setId(setId);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flight;

	}

	public List<Flight> findAll() {

		List<Flight> allFlights = new LinkedList<>();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT id,flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival FROM flight";
			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				int idFlight = rs.getInt(1);
				int flightNumber = rs.getInt(2);
				String airport = rs.getString(3);
				String airlineName = rs.getString(4);
				String tailNumber = rs.getString(5);
				int distance = rs.getInt(6);
				String departureCity = rs.getString(7);
				String arrivalCity = rs.getString(8);
				Date date = rs.getDate(9);
				Time departureTime = rs.getTime(10);
				Time arrivalTime = rs.getTime(11);

				Flight loadedData = new Flight(idFlight, flightNumber, airport, airlineName, tailNumber, distance,
						departureCity, arrivalCity, date, departureTime, arrivalTime);

				allFlights.add(loadedData);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allFlights;
	}

	public Flight findById(int id) {

		Flight flight = new Flight();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival FROM flight WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet returnedData = stmt.executeQuery();
			if (returnedData.next()) {

				int flightNumber = returnedData.getInt(1);
				String airport = returnedData.getString(2);
				String airlineName = returnedData.getString(3);
				String tailNumber = returnedData.getString(4);
				int distance = returnedData.getInt(5);
				String departureCity = returnedData.getString(6);
				String arrivalCity = returnedData.getString(7);
				Date date = returnedData.getDate(8);
				Time departureTime = returnedData.getTime(9);
				Time arrivalTime = returnedData.getTime(10);

				flight.setFlight_number(flightNumber);
				flight.setAirport(airport);
				flight.setAirline_name(airlineName);
				flight.setTail_number(tailNumber);
				flight.setDistance(distance);
				flight.setDeparture_city(departureCity);
				flight.setArrival_city(arrivalCity);
				flight.setDate(date);
				flight.setDeparture_time(departureTime);
				flight.setArrival(arrivalTime);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return flight;
	}
	
	public Flight findByFlightNumber(String flightNumberParam) {

		Flight flight = null;

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival FROM flight WHERE flight_number=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, flightNumberParam);

			ResultSet returnedData = stmt.executeQuery();
			if (returnedData.next()) {
				flight = new Flight();

				int flightNumber = returnedData.getInt(1);
				String airport = returnedData.getString(2);
				String airlineName = returnedData.getString(3);
				String tailNumber = returnedData.getString(4);
				int distance = returnedData.getInt(5);
				String departureCity = returnedData.getString(6);
				String arrivalCity = returnedData.getString(7);
				Date date = returnedData.getDate(8);
				Time departureTime = returnedData.getTime(9);
				Time arrivalTime = returnedData.getTime(10);

				flight.setFlight_number(flightNumber);
				flight.setAirport(airport);
				flight.setAirline_name(airlineName);
				flight.setTail_number(tailNumber);
				flight.setDistance(distance);
				flight.setDeparture_city(departureCity);
				flight.setArrival_city(arrivalCity);
				flight.setDate(date);
				flight.setDeparture_time(departureTime);
				flight.setArrival(arrivalTime);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return flight;
	}

	public Flight findByAirport(String airport_name) {

		Flight flight = new Flight();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival FROM flight WHERE airport=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, airport_name);

			ResultSet returnedData = stmt.executeQuery();
			if (returnedData.next()) {

				int flightNumber = returnedData.getInt(1);
				String airport = returnedData.getString(2);
				String airlineName = returnedData.getString(3);
				String tailNumber = returnedData.getString(4);
				int distance = returnedData.getInt(5);
				String departureCity = returnedData.getString(6);
				String arrivalCity = returnedData.getString(7);
				Date date = returnedData.getDate(8);
				Time departureTime = returnedData.getTime(9);
				Time arrivalTime = returnedData.getTime(10);

				flight.setFlight_number(flightNumber);
				flight.setAirport(airport);
				flight.setAirline_name(airlineName);
				flight.setTail_number(tailNumber);
				flight.setDistance(distance);
				flight.setDeparture_city(departureCity);
				flight.setArrival_city(arrivalCity);
				flight.setDate(date);
				flight.setDeparture_time(departureTime);
				flight.setArrival(arrivalTime);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return flight;
	}

	public Flight findByDeptCity(String departureCityName) {

		Flight flight = new Flight();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival FROM flight WHERE departure_city=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, departureCityName);

			ResultSet returnedData = stmt.executeQuery();
			if (returnedData.next()) {

				int flightNumber = returnedData.getInt(1);
				String airport = returnedData.getString(2);
				String airlineName = returnedData.getString(3);
				String tailNumber = returnedData.getString(4);
				int distance = returnedData.getInt(5);
				String departureCity = returnedData.getString(6);
				String arrivalCity = returnedData.getString(7);
				Date date = returnedData.getDate(8);
				Time departureTime = returnedData.getTime(9);
				Time arrivalTime = returnedData.getTime(10);

				flight.setFlight_number(flightNumber);
				flight.setAirport(airport);
				flight.setAirline_name(airlineName);
				flight.setTail_number(tailNumber);
				flight.setDistance(distance);
				flight.setDeparture_city(departureCity);
				flight.setArrival_city(arrivalCity);
				flight.setDate(date);
				flight.setDeparture_time(departureTime);
				flight.setArrival(arrivalTime);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return flight;
	}

	public Flight findByArrivalCity(String ArrivalCityName) {

		Flight flight = new Flight();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival FROM flight WHERE arrival_city=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, ArrivalCityName);

			ResultSet returnedData = stmt.executeQuery();
			if (returnedData.next()) {

				int flightNumber = returnedData.getInt(1);
				String airport = returnedData.getString(2);
				String airlineName = returnedData.getString(3);
				String tailNumber = returnedData.getString(4);
				int distance = returnedData.getInt(5);
				String departureCity = returnedData.getString(6);
				String arrivalCity = returnedData.getString(7);
				Date date = returnedData.getDate(8);
				Time departureTime = returnedData.getTime(9);
				Time arrivalTime = returnedData.getTime(10);

				flight.setFlight_number(flightNumber);
				flight.setAirport(airport);
				flight.setAirline_name(airlineName);
				flight.setTail_number(tailNumber);
				flight.setDistance(distance);
				flight.setDeparture_city(departureCity);
				flight.setArrival_city(arrivalCity);
				flight.setDate(date);
				flight.setDeparture_time(departureTime);
				flight.setArrival(arrivalTime);

			}

		} catch (SQLException e) {
		
			e.printStackTrace();
		}

		return flight;
	}

	public Flight findByDate(Date date0) {

		Flight flight = new Flight();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT flight_number,airport,airline_name,tail_number,distance,departure_city,arrival_city,date,departure_time,arrival FROM flight WHERE date=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDate(1, date0);

			ResultSet returnedData = stmt.executeQuery();
			if (returnedData.next()) {

				int flightNumber = returnedData.getInt(1);
				String airport = returnedData.getString(2);
				String airlineName = returnedData.getString(3);
				String tailNumber = returnedData.getString(4);
				int distance = returnedData.getInt(5);
				String departureCity = returnedData.getString(6);
				String arrivalCity = returnedData.getString(7);
				Date date = returnedData.getDate(8);
				Time departureTime = returnedData.getTime(9);
				Time arrivalTime = returnedData.getTime(10);

				flight.setFlight_number(flightNumber);
				flight.setAirport(airport);
				flight.setAirline_name(airlineName);
				flight.setTail_number(tailNumber);
				flight.setDistance(distance);
				flight.setDeparture_city(departureCity);
				flight.setArrival_city(arrivalCity);
				flight.setDate(date);
				flight.setDeparture_time(departureTime);
				flight.setArrival(arrivalTime);

			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		return flight;
	}

	public void update(Flight flight) throws SQLException {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "UPDATE flight SET  airport = ?, airline_name =? ,tail_number =? ,distance = ? ,departure_city= ?,arrival_city= ?,date =? ,departure_time =? ,arrival =? WHERE flight_number=?";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, flight.getAirport());
			stmt.setString(2, flight.getAirline_name());
			stmt.setString(3, flight.getTail_number());
			stmt.setInt(4, flight.getDistance());
			stmt.setString(5, flight.getDeparture_city());
			stmt.setString(6, flight.getArrival_city());
			stmt.setDate(7, flight.getDate());
			stmt.setTime(8, flight.getDeparture_time());
			stmt.setTime(9, flight.getArrival());
			stmt.setInt(10, flight.getFlight_number());

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			throw e;
		}

	}

	public void delete(String flightNumber) throws SQLException {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "DELETE FROM flight WHERE flight_number=?";

			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, flightNumber);

			stmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			throw e;
		}

	}

}
