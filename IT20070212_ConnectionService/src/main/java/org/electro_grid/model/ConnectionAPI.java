package org.electro_grid.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//for map
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ConnectionAPI
 */
@WebServlet("/ConnectionAPI")
public class ConnectionAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connections conObj = new Connections();

	//convert request parameters to a map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");

			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {

		}

		return map;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sending values to insert function
		String output = conObj.insertConnection(request.getParameter("accountNo"),
											request.getParameter("connectionName"),
											request.getParameter("serviceId"),
											request.getParameter("customerId"));
											
		//sending the output to client
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter map
		Map paras = getParasMap(request);

		//getting values from the map and sending to update function
		String output = conObj.updateConnection(paras.get("hidConnectionIDSave").toString(),
											paras.get("accountNo").toString(),
											paras.get("connectionName").toString(),
				                            paras.get("serviceId").toString(),
				                            paras.get("connectionId").toString(),
		                                    paras.get("connectionDate").toString(),
                                            paras.get("connectionStatus").toString());
								
		//sending the output to client
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		String x = request.getParameter("connectionId");
		System.out.println(x);
		*/
		
		
		
		//parameter map
		Map paras = getParasMap(request);
		System.out.println(paras.get("connectionId").toString());
		
		//getting values from the map and sending to delete function
		String output = conObj.deleteConnection(paras.get("connectionId").toString());
		
		//sending the output to client
		response.getWriter().write(output);
		
	}

}