
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class Datareturner
 */
@WebServlet("/Datareturner")
public class Datareturner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Datareturner() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("k1");
		String pass=request.getParameter("k2");
		JSONObject json=new JSONObject();
		PrintWriter out=response.getWriter();
		if(name==null|| pass==null)
		{
			try{json.put("status", "Data Missing");}catch(Exception e) {out.print(e);}
			out.print(json.toString());
			return;
		}
		String dbName = System.getProperty("RDS_DB_NAME");
		  String userName = System.getProperty("RDS_USERNAME");
		  String password = System.getProperty("RDS_PASSWORD");
		  String hostname = System.getProperty("RDS_HOSTNAME");
		  String port = System.getProperty("RDS_PORT");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
		    port + "/" + dbName + "?user=" + userName + "&password=" + password;
		  
		  // Load the JDBC driver
		  try {
		    System.out.println("Loading driver...<br/>");
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!<br/>");
		  } catch (ClassNotFoundException e) {
		    throw new RuntimeException("Cannot find the driver in the classpath!<br/>", e);
		  }

		  Connection conn = null;
		  try {
		    // Create connection to RDS DB instance
		    conn = DriverManager.getConnection(jdbcUrl);
		    PreparedStatement pt=conn.prepareStatement("insert into users(name,password) values (?,?);");
		    pt.setString(1, name);
		    pt.setString(2, pass);
		    int i=pt.executeUpdate();
		    if(i>0)
		    	json.put("status", "1");
		    else
		    	json.put("status", "0");
		    out.println(json.toString());
	}catch(Exception e)
		  {
			out.println(e);
		  }
		  finally
		  {
			  if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
		  }
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
