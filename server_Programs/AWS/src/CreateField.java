import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class CreateField
 */
@WebServlet("/CreateField")
public class CreateField extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateField() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Application/json");
		try
		{
			JSONObject json=new JSONObject();
			String id=request.getParameter("id");
			String label=request.getParameter("k1");
			String crop=request.getParameter("k2");
			String invest=request.getParameter("k3");
			String phone=request.getParameter("k5");
			String email=request.getParameter("k4");
			String state=request.getParameter("k6");
			String date=request.getParameter("k7");
			String adhar=request.getParameter("k8");
			if(id==null||label==null||crop==null||invest==null)
			{
				json.put("status","Data Missing");
				response.getWriter().println(json.toString());
				return;
			}
			int idi=Integer.parseInt(id);
			int i1=Integer.parseInt(invest);
			String dbName = System.getProperty("RDS_DB_NAME");
			  String userName = System.getProperty("RDS_USERNAME");
			  String password = System.getProperty("RDS_PASSWORD");
			  String hostname = System.getProperty("RDS_HOSTNAME");
			  String port = System.getProperty("RDS_PORT");
			  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
			 Class.forName("com.mysql.jdbc.Driver");
			 java.sql.Connection conn =DriverManager.getConnection(jdbcUrl);
			 PreparedStatement p=conn.prepareStatement("INSERT INTO FIELDS (id,label,currentCrop,investedCost,phone,email,state,cropDate,adhar ) VALUES (?,?,?,?,?,?,?,?,?);");
			 p.setInt(1, idi);
			 p.setString(2, label);
			 p.setString(3, crop);
			 p.setInt(4, i1);
			 p.setString(5, phone);
			 p.setString(6, email);
			p.setString(7, state);
			p.setString(8, date);
			p.setString(9, adhar);
			 int fi=p.executeUpdate();
			 if(fi>0)
				 json.put("status", "1");
			 else
				 json.put("staus", "0");
			 conn.close();
			 response.getWriter().println(json.toString());
		}
		catch(Exception e)
		{
			response.getWriter().println(e);
		}
	}

}