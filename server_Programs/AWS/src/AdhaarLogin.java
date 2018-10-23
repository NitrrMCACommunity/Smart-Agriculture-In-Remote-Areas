

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class AdhaarLogin
 */
@WebServlet("/AdhaarLogin")
public class AdhaarLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdhaarLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json =new JSONObject();
		try {
		String adhar=request.getParameter("Adhar");
		PrintWriter out=response.getWriter();
		response.setContentType("/Application/json");
		String dbName = System.getProperty("RDS_DB_NAME");
		  String userName = System.getProperty("RDS_USERNAME");
		  String password = System.getProperty("RDS_PASSWORD");
		  String hostname = System.getProperty("RDS_HOSTNAME");
		  String port = System.getProperty("RDS_PORT");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
		 Class.forName("com.mysql.jdbc.Driver");
		 java.sql.Connection conn =DriverManager.getConnection(jdbcUrl);
		 PreparedStatement pt1=conn.prepareStatement("SELECT * FROM user WHERE adhar=?");
		 pt1.setString(1, adhar);
		 ResultSet rs=pt1.executeQuery();
		 if(!rs.next()) {
		 PreparedStatement pt=conn.prepareStatement("INSERT INTO user (adhar) VALUES (?)");
		 pt.setString(1, adhar);
		 pt.executeUpdate();
		 }
		 json.put("status", "1");
		 response.getWriter().println(json.toString());
		 conn.close();
	}catch(Exception e)
		{
			response.getWriter().println("{status:0}");
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
