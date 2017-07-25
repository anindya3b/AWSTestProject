<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.dashboard.model.InstanceModel"%>
<%@ page import="java.util.ArrayList"%>



<%
ArrayList<InstanceModel> listIds=null;
if(request.getAttribute("instanceList")!=null)
{
	listIds=(ArrayList<InstanceModel>)request.getAttribute("instanceList");
}
%>

<!DOCTYPE html>
<html>
<%-- <head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <title>Hello AWS Web World!</title>
    <link rel="stylesheet" href="styles/styles.css" type="text/css" media="screen">
       <script src="js/jquery.min.js"></script>

</head>
<body>
    <div id="content" class="container">
        <div class="section grid grid5 s3">
            <h2>Amazon S3 Buckets:</h2>
            <ul>
            <% for (Bucket bucket : s3.listBuckets()) { %>
               <li> <%= bucket.getName() %> </li>
            <% } %>
            </ul>
        </div>

        <div class="section grid grid5 sdb">
            <h2>Amazon DynamoDB Tables:</h2>
            <ul>
            <% for (String tableName : dynamo.listTables().getTableNames()) { %>
               <li> <%= tableName %></li>
            <% } %>
            </ul>
        </div>

          <div class="section grid grid5 gridlast ec2">
            <h2>Amazon EC2 Instances:</h2>
           <table>
           
               
           
           </table>
        </div>
       
    </div>
</body> --%>

<head>


<base href="/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">



<link rel="stylesheet" href="/DashboardEC2/styles/login.css">
<link rel="stylesheet" href="/DashboardEC2/styles/custom.css">
<link rel="stylesheet" href="/DashboardEC2/styles/bootstrap.min.css">
<script type='application/javascript'
	src="/DashboardEC2/js/jquery.min.js"></script>
<script type='application/javascript'
	src="/DashboardEC2/js/bootstrap.min.js"></script>




<title>Insert title here</title>
</head>
<body class="app"
	style="background: url('/DashboardEC2/images/Loginbackgroundimage.jpg')">


	<div class="col-sm-12 no-padding infoLayout" style="margin-top: 7px">
		<!-- Main Listing div -->
		<div class="col-sm-12 no-padding tableLayout">
			<table class="tableDetailedView" width="100%"
				style="margin-bottom: 0px;">
				<tr>
					<th colspan="5" width="100%"
						style="border-radius: 6px 0px 0px 0px; text-align: left; padding-left: 10px; font-weight: 50px"><h1>
							<b>On Demand</b>
						</h1></th>
				</tr>

				<tr>
					<th style="padding: 10px">Instance Id</th>
					<th style="padding: 10px">Subnet Id</th>
					<th style="padding: 10px">Instance Type</th>
					<th style="padding: 10px">Public IP</th>
					<th style="padding: 10px"></th>
				</tr>
				<% for (InstanceModel instance : listIds) { %>
				<tr>
					<td style="text-align: center"><%= instance.getId() %></td>
					<td style="text-align: center"><%= instance.getSubnetId() %></td>
					<td style="text-align: center"><%= instance.getInstanceType() %>
					</td>
					<td style="text-align: center"><%= instance.getPublicIP() %></td>
					<% if(instance.isStarted()){ %>
					<td><form action="/DashboardEC2/DashboardEC2Controller"
							method="post">
							<input type="hidden" value="<%= instance.getId() %>"
								id="instanceid" name="instanceid"> <input type="hidden"
								value="<%= instance.isStarted() %>" id="state" name="state">
							<button type="submit" class="buttonStop" value="Stop">Stop</button>
						</form></td>
					<%}
                   else
                {
                %>
					<td><form action="/DashboardEC2/DashboardEC2Controller"
							method="post">
							<input type="hidden" value="<%= instance.getId() %>"
								id="instanceid" name="instanceid"> <input type="hidden"
								value="<%= instance.isStarted() %>" id="state" name="state">
							<button type="submit" class="buttonStart" value="Start">Start</button>
						</form></td>
					<% }%>
				</tr>
				<%  } %>
			</table>
		</div>
	</div>

	<div class="col-sm-12 no-padding infoLayout" style="margin-top: 7px">
		<!-- Main Listing div -->
		<div class="col-sm-12 no-padding tableLayout">
			<table class="tableDetailedView" width="100%"
				style="margin-bottom: 0px;">
				<tr>
					<th width="100%"
						style="border-radius: 6px 0px 0px 0px; text-align: left; padding-left: 10px; font-weight: 50px"><h1>
							<b>Schedule</b>
						</h1></th>

				</tr>
			</table>
		</div>
	</div>
</body>
</html>