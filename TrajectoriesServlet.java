package com.softmillennium.trajectories.web;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import aleksanderbraksator.trajectory.InputParameters;
import aleksanderbraksator.trajectory.TrajectoryCalculator;
import aleksanderbraksator.trajectory.TrajectoryPoint;
import aleksanderbraksator.trajectory.TrajectoryPointCalculatorFactory.CalculationMode;

/**
 * Servlet implementation class TrajectoriesServlet
 */
@WebServlet("/TrajectoriesServlet")
public class TrajectoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrajectoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameterMap().size() == 0) {
			response.getWriter().write("");
			return;
		}
		InputParameters inputParams = new InputParameters();
		inputParams.timeInterval = Double.valueOf(request.getParameter("timeInterval"));
		inputParams.objectDiameter = Double.valueOf(request.getParameter("objectDiameter"));
		inputParams.objectMass = Double.valueOf(request.getParameter("objectMass"));
		inputParams.spinDirection = Integer.valueOf(request.getParameter("spinDirection"));
		inputParams.objectDragCoefficient = Double.valueOf(request.getParameter("objectDragCoefficient"));
		inputParams.initialHeight = Double.valueOf(request.getParameter("initialHeight"));
		inputParams.initialDistance = Double.valueOf(request.getParameter("initialDistance"));
		inputParams.spin = Double.valueOf(request.getParameter("spin"));
		inputParams.initialVelocityX = Double.valueOf(request.getParameter("initialVelocityX"));
		inputParams.initialVelocityY= Double.valueOf(request.getParameter("initialVelocityY"));
		inputParams.bDebug = true;

		TrajectoryCalculator calculator = new TrajectoryCalculator(inputParams, CalculationMode.TRAJECTORY);
		List<TrajectoryPoint> trajectoryPoints = calculator.calculateTrajectory();
		
		//1. Convert Java object to JSON format
		ObjectMapper mapper = new ObjectMapper();
		StringWriter sw = new StringWriter();
		mapper.writeValue(sw, trajectoryPoints);
		String json = sw.toString();
		response.getWriter().write(json);
		boolean isCommited = response.isCommitted();
		if(!isCommited) {
			response.getWriter().flush();
			response.flushBuffer();
		}
	}

}
