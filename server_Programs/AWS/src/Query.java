

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Query")
public class Query extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Query() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String msg="";
		try {
			String sql=request.getParameter("sql");
			String dbName = System.getProperty("RDS_DB_NAME");
			  String userName = System.getProperty("RDS_USERNAME");
			  String password = System.getProperty("RDS_PASSWORD");
			  String hostname = System.getProperty("RDS_HOSTNAME");
			  String port = System.getProperty("RDS_PORT");
			  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
			 Class.forName("com.mysql.jdbc.Driver");
			 java.sql.Connection conn =DriverManager.getConnection(jdbcUrl);
			 if(sql.startsWith("select")||sql.startsWith("SELECT")||sql.startsWith("SHOW"))
			 {
				java.sql.PreparedStatement pt= conn.prepareStatement(sql);
				ResultSet rs=pt.executeQuery();
				if(rs.next())
				{
					java.sql.ResultSetMetaData mt=rs.getMetaData();
					int c=mt.getColumnCount();
					for (int i = 1; i <= c; i++) {
				       msg=msg+mt.getColumnName(i)+"  ";
					}
					msg=msg+"<br/>";
					do {
						for(int i=1;i<c;i++)
						msg=msg+rs.getString(i);
						msg=msg+"<br/>";
					}while(rs.next());
					response.sendRedirect("Database.jsp?msg="+msg);
				}
				else {
					msg="Table does Not Exist";
					response.sendRedirect("Database.jsp?msg="+msg);
				}
			 }
			 else
			 {
				java.sql. PreparedStatement pt=conn.prepareStatement(sql);
				pt.executeUpdate();
				msg="Query Success";
				response.sendRedirect("Database.jsp?msg="+msg);
			 }
		}catch(Exception e)
		{
			msg=e.getMessage();
			response.sendRedirect("Database.jsp?msg="+msg);
		}
	}

}
