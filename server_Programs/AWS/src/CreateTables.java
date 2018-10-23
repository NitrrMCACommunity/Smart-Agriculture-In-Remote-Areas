

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class CreateTables
 */
@WebServlet("/CreateTables")
public class CreateTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTables() {
        super();
     // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbName = System.getProperty("RDS_DB_NAME");
		  String userName = System.getProperty("RDS_USERNAME");
		  String password = System.getProperty("RDS_PASSWORD");
		  String hostname = System.getProperty("RDS_HOSTNAME");
		  String port = System.getProperty("RDS_PORT");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
		    port + "/" + dbName + "?user=" + userName + "&password=" + password;
		 // response.getWriter().print(jdbcUrl);
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
		    java.sql.Statement st=conn.createStatement();
		    String sql = "CREATE TABLE FIELDS " +
	                   "(id INTEGER not NULL, " +
	                   " label VARCHAR(255), " + 
	                   " currentCrop VARCHAR(255), " + 
	                   " investedCost INTEGER, " + 
	                   " PRIMARY KEY ( id ))"; 
		    int i=st.executeUpdate(sql);
		    if(i>0)
		    	response.getWriter().println("table Created");
		    else
		    	response.getWriter().println("Problem  :: Table doesn't created");
		    conn.close();
		  }catch(Exception e)
		  {
			  response.getWriter().println(e);
		  }
		  finally
		  {
			  if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
		  }

		
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
