package org.electro_grid.com;

import org.electro_grid.model.Connections;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Connections")
public class ConnectionService {
	
	    //create an object
		Connections ConneObj = new Connections();
		
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		
		public String readCustomer()
		{
			return ConneObj.readConnection();
		}
		
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String insertConnection(@FormParam("accountNo") String accountNo,
						@FormParam("connectionName") String connectionName, @FormParam("serviceId") String serviceId, @FormParam("customerId") String customerId)
		{
			String output = ConneObj.insertConnection(accountNo, connectionName, serviceId, customerId);
			return output;
		}
		
		
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String updateConnection(String connectionData)
		{
			//Convert the input string to a JSON object
			JsonObject ConneObject = new JsonParser().parse(connectionData).getAsJsonObject();
			
			//Read the values from the JSON object
			String connectionId = ConneObject.get("connectionId").getAsString();
			String accountNo = ConneObject.get("accountNo").getAsString();
			String connectionName = ConneObject.get("connectionName").getAsString();
			String serviceId = ConneObject.get("serviceId").getAsString();
			String customerId = ConneObject.get("customerId").getAsString();
			String connectionDate= ConneObject.get("connectionDate").getAsString();
			String connectionStatus = ConneObject.get("connectionStatus").getAsString();
			String output = ConneObj.updateConnection(connectionId, accountNo,connectionName,serviceId, customerId,connectionDate,connectionStatus);
			
			return output;
		}
		
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteConnection(String connectionData)
		{
			//Convert the input string to an XML document
			Document doc = Jsoup.parse(connectionData, "", Parser.xmlParser());
			
			//Read the value from the element <ConnetionId>
			String connectionId = doc.select("connectionId").text();
			String output = ConneObj.deleteConnection(connectionId);
			
			return output;
		}

}
