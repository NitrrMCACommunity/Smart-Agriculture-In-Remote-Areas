

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowTables
 */
@WebServlet("/ShowTables")
public class ShowTables extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTables() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String dbName = System.getProperty("RDS_DB_NAME");
			  String userName = System.getProperty("RDS_USERNAME");
			  String password = System.getProperty("RDS_PASSWORD");
			  String hostname = System.getProperty("RDS_HOSTNAME");
			  String port = System.getProperty("RDS_PORT");
			 String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
			 Class.forName("com.mysql.jdbc.Driver");
			 java.sql.Connection conn =DriverManager.getConnection(jdbcUrl);
			 java.sql.PreparedStatement pt=conn.prepareStatement("SHOW TABLES");
			 ResultSet rs=pt.executeQuery();
			 
			 while(rs.next())
			 {
				 response.getWriter().print("<tr>");
				 response.getWriter().print("<br/><td>");
				response.getWriter().print(rs.getString(1));
				response.getWriter().print("</td></tr>");
				  
			 }
			 
			 conn.close();
	}catch(Exception e)
		{
		response.getWriter().println(e);
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
