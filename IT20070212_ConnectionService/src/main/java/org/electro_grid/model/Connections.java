package org.electro_grid.model;

import java.sql.*;


public class Connections {
	

			//A common method to connect to the DB
			private Connection connect()
			{
				Connection con = null;
			
				try
				{
					Class.forName("com.mysql.jdbc.Driver");
					
					//Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_lab", "root", "maatha");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				
				return con;
			}
		
			
			//insert new connections
			public String insertConnection(String accountNo, String connectionName, String serviceId, String customerId)
			{
				String output = "";
			
				try
				{
					Connection con = connect();
					
					if (con == null)
					{
						return "Error while connecting to the database for inserting."; 
					}
				
			
					
				// create a prepared statement
				String query = " insert into connection (`accountNo`,`connectionName`, `serviceId`, `customerId`) values (?,?,?,?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				//preparedStmt.setInt(1, 0); 
				preparedStmt.setString(1, accountNo);
				preparedStmt.setString(2, connectionName);
				preparedStmt.setInt(3, Integer.parseInt(serviceId));
				preparedStmt.setInt(4, Integer.parseInt(customerId));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				//output = "Inserted successfully";
				 String newConnection = readConnection(); 
				 output = "{\"status\":\"success\", \"data\": \"" + 
						 newConnection + "\"}"; 
				
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the Connections.\"}";
				System.err.println(e.getMessage());
			}
			
			return output;
		}


       //retrieve connections
		public String readConnection()
		{
			String output = "";
			
			try
			{
				Connection con = connect();
				
				if (con == null)
				{
					return "Error while connecting to the database for reading."; 
				}
				
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Account No</th><th>Connection Name</th><th>Service Id</th><th>Customer Id</th><th>Connection Date</th><th>Connection Status</th><th>Update</th><th>Delete</th></tr>";
				
				String query = "select * from connection";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String connectionId = Integer.toString(rs.getInt("connectionId"));
					String accountNo = Integer.toString(rs.getInt("accountNo"));
					String connectionName = rs.getString("connectionName");
					Date connectionDate = rs.getDate("connectionDate");
					String connectionStatus = Integer.toString(rs.getInt("connectionStatus"));
					String serviceId = Integer.toString(rs.getInt("serviceId"));
					String customerId = rs.getString("customerId");
					
					// Add into the html table
					
					//output += "<tr><td><input id='hidItemIDUpdate'  name='hidItemIDUpdate' type='hidden' value='" + connectionId + "'>" + accountNo + "</td>";
					//output += "<td>" + connectionId + "</td>";
					output += "<td>" + accountNo + "</td>";
					output += "<td>" + connectionName + "</td>";
					output += "<td>" + serviceId + "</td>";
					output += "<td>" + customerId + "</td>";
					output += "<td>" + connectionDate + "</td>";
					output += "<td>" + connectionStatus + "</td>";
			
					//buttons
					output += "<td>"+ "<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary' data-connectionId ='" + connectionId + "'>"+ "</td>" + "<td>" + "<input name='btnRemove' type='button' value='Remove' class='btn btn-sm btn-danger btnRemove' data-connectionId='" + connectionId + "'>"+ "</td></tr>";
					
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the connection list.";
				System.err.println(e.getMessage());
			}
			
			return output;
			
		}
		
		
		//update connection details
		public String updateConnection(String connectionId, String accountNo, String connectionName, String serviceId, String customerId,String connectionDate, String connectionStatus )
		{
			String output = "";
			Connection con = connect();
			
			try
			{
				
				
				if (con == null)
				{
					return "Error while connecting to the database for updating."; 
				}
				
				// create a prepared statement
				String query = "UPDATE connection SET accountNo=?,connectionName=? , serviceId=?, customerId=? ,connectionDate=?, connectionStatus =? WHERE connectionId=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setString(1, accountNo);
				preparedStmt.setString(2, connectionName);
				preparedStmt.setString(3, serviceId);
				preparedStmt.setString(4, customerId);
				preparedStmt.setString(5, connectionDate);
				preparedStmt.setString(6, connectionStatus);
				preparedStmt.setString(7, connectionId);
				
				//preparedStmt.setInt(5, Integer.parseInt(connectionId));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				//output = "Updated successfully";
				 String newConnection = readConnection(); 
				 output = "{\"status\":\"success\", \"data\": \"" +  newConnection + "\"}"; 
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while updating the Connections.\"}";
				System.err.println(e.getMessage());
				
			}
			
			return output;	
		}

		

		//delete connection		
		public String deleteConnection(String connectionId)
		{
			String output = "";
			Connection con = connect();
			
			try
			{
				
				
				if (con == null)
				{
					return "Error while connecting to the database for deleting."; 
				}
				
				// create a prepared statement
				String query = "delete * from connection where connectionId=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(connectionId));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				//output = "Deleted successfully";
				String newConnection = readConnection(); 
				output = "{\"status\":\"success\", \"data\": \"" + newConnection + "\"}"; 
			}
			catch (Exception e)
			{
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the connections.\"}"; 
						 System.err.println(e.getMessage()); 

			}
			
			return output;
			
		}
		
	}
			