package com.skillstorm.air_flight.servlets;

import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.skillstorm.air_flight.beans.Flight;
import com.skillstorm.air_flight.dao.FlightDAO;

@WebServlet(urlPatterns = "/api/flight")
public class FlightServlet extends HttpServlet {

	FlightDAO dao = new FlightDAO();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InputStream requestBody = req.getInputStream();

		Flight flight = new ObjectMapper().readValue(requestBody, Flight.class);

		Flight newFlight;
		try {
			newFlight = dao.insert(flight);

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
			resp.getWriter().print(mapper.writeValueAsString(newFlight));



			resp.setStatus(201);

			resp.setContentType("application/json");
			resp.addHeader("Access-Control-Allow-Origin", "*");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");

		if (req.getParameter("flight_number") != null) {

			String flightNumber = req.getParameter("flight_number");

			Flight flight = dao.findByFlightNumber(flightNumber);

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
			String result = mapper.writeValueAsString(flight);

			resp.getWriter().print(result);
			resp.setContentType("application/json");
		}
//			
//			
		else {
			List<Flight> allFlights = dao.findAll();

			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
			String result = mapper.writeValueAsString(allFlights);

			resp.getWriter().print(result);
			resp.setContentType("application/json");

		}

	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");

		InputStream readBody = req.getInputStream();

		Flight flight = new ObjectMapper().readValue(readBody, Flight.class);

		try {
			dao.update(flight);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		resp.setContentType("application/json");

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");

		if (req.getParameter("flight_number") != null) {

			String flightNumber = req.getParameter("flight_number");
			try {
				dao.delete(flightNumber);
				resp.setStatus(200);
			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

}
