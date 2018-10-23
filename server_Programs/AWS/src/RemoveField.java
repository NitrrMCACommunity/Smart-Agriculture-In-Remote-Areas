

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


/**
 * Servlet implementation class RemoveField
 */
@WebServlet("/RemoveField")
public class RemoveField extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveField() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		JSONObject jd=new JSONObject();
		try
		{
		String id=request.getParameter("id");
		String dbName = System.getProperty("RDS_DB_NAME");
		  String userName = System.getProperty("RDS_USERNAME");

		  String password = System.getProperty("RDS_PASSWORD");
		  String hostname = System.getProperty("RDS_HOSTNAME");
		  String port = System.getProperty("RDS_PORT");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
		  Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection(jdbcUrl);
			java.sql.PreparedStatement pd=conn.prepareStatement("DELETE FROM FIELDS WHERE id=?");
			pd.setString(1, id);
			int f=pd.executeUpdate();
				jd.put("status", "1");
			response.getWriter().println(jd.toString());
	}
		catch(Exception e)
		{
			response.getWriter().println("{statu:0");
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
