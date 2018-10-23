import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class FieldServe
 */
@WebServlet("/sendToCloud")
public class sendToCloud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendToCloud() {
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
		java.sql. Connection conn=null;
		JSONObject json=new JSONObject();
		try
		{
		String ids=request.getParameter("id");
		if(ids==null)
		{
			json.put("status", "Missing fieldID");
			response.getWriter().println(json.toString());
			return;
		}
		int id=Integer.parseInt(ids);
		String s=request.getParameter("S");
		int si=Integer.parseInt(s);
		String h=request.getParameter("H");
		int hi=Integer.parseInt(h);
		String t=request.getParameter("T");
		int ti=Integer.parseInt(t);
		//String w=request.getParameter("W");
		int wi=300;
		String dbName = System.getProperty("RDS_DB_NAME");
		  String userName = System.getProperty("RDS_USERNAME");
		  String password = System.getProperty("RDS_PASSWORD");
		  String hostname = System.getProperty("RDS_HOSTNAME");
		  String port = System.getProperty("RDS_PORT");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
		 Class.forName("com.mysql.jdbc.Driver");
		 conn = DriverManager.getConnection(jdbcUrl);
		 FieldData f=new FieldData();
		 f.id=id;
		 f.setS(si);
		 f.setH(hi);
		 f.setT(ti);
		 f.setW(wi);
		 f.deletePre(conn);
		 ProcessData p=new ProcessData(f);
		 Decisions d=p.predict();
		 if(f.insert(conn))
			 json.put("status", 1);
		 else
			 json.put("Status", 0);
		 if(!conn.isClosed())
			 conn.close();
		 response.getWriter().print(json.toString());
		}
		catch(Exception e)
		{
			response.getWriter().println(e);
			//response.getWriter().println("{ status : Exception}");
		}
		//finally {
			
		//	try {	conn.close();}catch(Exception e) {}
		//}
		}
}