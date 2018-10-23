

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class GetAddCost
 */
@WebServlet("/GetAddCost")
public class AddCost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id=request.getParameter("id");
		String cost=request.getParameter("cost");
		int i=Integer.parseInt(cost);
		String phone=request.getParameter("phone");
		String dbName = System.getProperty("RDS_DB_NAME");
		  String userName = System.getProperty("RDS_USERNAME");
		  String password = System.getProperty("RDS_PASSWORD");
		  String hostname = System.getProperty("RDS_HOSTNAME");
		  String port = System.getProperty("RDS_PORT");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
		  Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection conn = DriverManager.getConnection(jdbcUrl);
			int oldCost=0;
			java.sql.PreparedStatement pd2=conn.prepareStatement("SELECT investedCost FROM FIELDS WHERE id=? ");
			pd2.setInt(1, Integer.parseInt(id));
			ResultSet rs=pd2.executeQuery();
			if(rs.next())
				oldCost=rs.getInt(1);
			pd2.executeQuery();
			java.sql.PreparedStatement pd=conn.prepareStatement("update FIELDS SET investedCost=? where id=?");
			pd.setString(1, id);
			pd.setInt(2, i+oldCost);
			int f=pd.executeUpdate();
			JSONObject json=new JSONObject();
			if(f==1)
				json.put("status", "0");
			else
				json.put("status", "1");
			response.getWriter().println(json.toString());
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
