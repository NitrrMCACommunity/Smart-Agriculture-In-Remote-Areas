

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class TodayTemprature
 */
@WebServlet("/TodayTemprature")
public class TodayTemprature extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodayTemprature() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adhar=request.getParameter("adhar");
		try
		{
			String dbName = System.getProperty("RDS_DB_NAME");
			  String userName = System.getProperty("RDS_USERNAME");
			  String password = System.getProperty("RDS_PASSWORD");
			  String hostname = System.getProperty("RDS_HOSTNAME");
			  String port = System.getProperty("RDS_PORT");
			  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
			 Class.forName("com.mysql.jdbc.Driver");
			 java.sql.Connection conn = DriverManager.getConnection(jdbcUrl);
			 PreparedStatement pt=conn.prepareStatement("SELECT id FROM FIELDS WHERE adhar=?");
			 pt.setString(1, adhar);
			 ResultSet rs=pt.executeQuery();
			 int id=0;
			 JSONObject json=new JSONObject();
			 if(rs.next())
			 {
				 id=rs.getInt(1);
				 PreparedStatement pt1=conn.prepareStatement("SELECT t,h FROM FIELDSDATA WHERE ID=?");
				 pt1.setInt(1, id);
				 ResultSet rs1=pt1.executeQuery();
				 if(rs1.next()) {
				 json.put("Temperature", +rs1.getInt(1));
				 json.put("Humidity", rs1.getInt(2));
				 }
				 else
					 json.put("status", "Data Not Found");
			 }
			 else
			 {
				 json.put("status", "user not valid");
			 }
			 response.getWriter().println(json.toString());
				 
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
