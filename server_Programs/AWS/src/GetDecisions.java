

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class GetDecesion
 */
@WebServlet("/GetDecisions")
public class GetDecisions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDecisions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		 java.sql.PreparedStatement pt=conn.prepareStatement("SELECT * FROM DECISIONS WHERE id=?");
		 pt.setString(1, id);
		ResultSet rs= pt.executeQuery();
		Decisions dn=new Decisions();
		String str=null;
		if(rs.next())
		{
			String b=rs.getString(2);
			if(b.equals("true"))
				dn.setbestTimeOfIrregration(true);
			else
				dn.setbestTimeOfIrregration(false);
			dn.setbestTimeOfSowing(rs.getString(4));
			dn.setbestTypeOfCrop(rs.getString(3));
			dn.setid(rs.getInt(1));
			Field f=Field.getField(dn.getid(), conn);
			dn.setinvestmentTillNow(f.getInvest());
			str=new Gson().toJson(dn);
		}
		else
		{
			JSONObject js=new JSONObject();
			js.put("status", "field not found");
			str=js.toString();
		}
		response.getWriter().println(str); 
		}
		catch(Exception e)
		{
			response.getWriter().println(e);
		}
		
	}

}
