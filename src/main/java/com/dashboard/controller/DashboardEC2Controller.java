package com.dashboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;

/**
 * Servlet implementation class DashboardEC2Controller
 */
public class DashboardEC2Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private AmazonEC2  ec2;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardEC2Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
	        ec2    = new AmazonEC2Client(credentialsProvider);
	        ec2.setEndpoint("https://ec2.ap-northeast-1.amazonaws.com");

	        String instanceId=request.getParameter("instanceid");
	        String state=request.getParameter("state");
	        
	        if(state.equalsIgnoreCase("false"))
	        {
		StartInstancesRequest requestStart = new StartInstancesRequest()
		    .withInstanceIds(instanceId);

		ec2.startInstances(requestStart);
	        }
		else
		{
		StopInstancesRequest requestStop = new StopInstancesRequest()
			    .withInstanceIds(instanceId);

			ec2.stopInstances(requestStop);
		}
	        System.out.println(state);
	       
			request.getRequestDispatcher("LoginController").forward(request, response);
	}

}
