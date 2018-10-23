

import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;

/**
 * Servlet implementation class getProperties
 */
@WebServlet("/getProperties")
public class getProperties extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getProperties() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		try {
			String dbName = System.getProperty("RDS_DB_NAME");
			  String userName = System.getProperty("RDS_USERNAME");
			  String password = System.getProperty("RDS_PASSWORD");
			  String hostname = System.getProperty("RDS_HOSTNAME");
			  String port = System.getProperty("RDS_PORT");
			  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
			 Class.forName("com.mysql.jdbc.Driver");
			 java.sql.Connection conn =DriverManager.getConnection(jdbcUrl);
			 FieldData f=FieldData.getFieldData(conn, Integer.parseInt(id));
			 if(f==null)
			 {
				 JSONObject json=new JSONObject();
				 json.put("status", "No Data is available");
				 response.getWriter().println(json.toString());
			 }
			 else
			 {
				 String json=new Gson().toJson(f);
				 response.getWriter().println(json);
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
