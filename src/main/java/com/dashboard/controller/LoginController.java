package com.dashboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.dashboard.model.InstanceModel;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private AmazonEC2  ec2;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		//int i=0;
		
		String username="";
		if(null==request.getSession().getAttribute("username"))
		{
			username=request.getParameter("username");
		}
		else
		{
			username=(String) request.getSession().getAttribute("username");
		}
		request.getParameter("username");
		Properties prop = new Properties();
		prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("AwsCredentials.properties"));
		 ArrayList<InstanceModel> listIds=new ArrayList<InstanceModel>();
		String ec2Ids[]=prop.getProperty(username).split(",");
		 if (ec2 == null) {
		        AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();
		        ec2    = new AmazonEC2Client(credentialsProvider);
		        ec2.setEndpoint("https://ec2.ap-northeast-1.amazonaws.com");
		      
		    }
		
		 for (Reservation reservation : ec2.describeInstances().getReservations()) {
			 InstanceModel instanceModel =new InstanceModel();
			 for (Instance instance : reservation.getInstances()) {
				for(int i=0;i<ec2Ids.length;i++)
				{
					
					if(instance.getInstanceId().equalsIgnoreCase(ec2Ids[i]))
					{
						instanceModel.setId(instance.getInstanceId());
						instanceModel.setSubnetId(instance.getSubnetId());
						instanceModel.setInstanceType(instance.getInstanceType());
						instanceModel.setPublicIP(instance.getPublicIpAddress());
						if(instance.getState().getCode()==16)
						{
							instanceModel.setStarted(true);
						}
						if(instance.getState().getCode()==80)
						{
							instanceModel.setStarted(false);
						}
					}
				}
			 }
			 listIds.add(instanceModel);
		 }
		/* InstanceModel instanceModel1 =new InstanceModel();
		 InstanceModel instanceModel2 =new InstanceModel();
		 instanceModel1.setId("a");
			instanceModel1.setSubnetId("b");
			instanceModel1.setInstanceType("c");
			instanceModel1.setPublicIP("d");
			
				instanceModel1.setStarted(true);
			
				
				 instanceModel2.setId("a");
					instanceModel2.setSubnetId("b");
					instanceModel2.setInstanceType("c");
					instanceModel2.setPublicIP("d");
					
						instanceModel2.setStarted(false);
		 listIds.add(instanceModel1);
		 listIds.add(instanceModel2);*/
		System.out.println(username);
		if(null==request.getSession().getAttribute("username"))
		{
		request.getSession().setAttribute("username", username);
		}
		request.setAttribute("instanceList", listIds);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
		//response.getWriter().append("Served at: ").append(prop.getProperty(request.getParameter("username")));
	}

}
