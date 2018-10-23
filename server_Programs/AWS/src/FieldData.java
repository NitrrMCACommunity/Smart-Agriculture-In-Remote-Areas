import com.mysql.jdbc.PreparedStatement;
import com.sun.corba.se.pept.transport.Connection;
import java.sql.*;
public class  FieldData {
	int id;
	int  S;
	int H;
	int T;
	int W;
	public FieldData()
	{
		
	}
	void setid(int id1)
	{
		this.id=id1;
	}
	void setS(int s1)
	{
		this.S=s1;
	}
	void setH(int h1)
	{
		this.H=h1;
	}
	void setT(int t1)
	{
		this.T=t1;
	}
	void setW(int w1)
	{
		this.W=w1;
	}
	int getid()
	{
		return id;
	}
	int getS()
	{
		return S;
	}
	int getH()
	{
		return H;
	}
	int getT()
	{
		return T;
	}
	int getW()
	{
		return W;
	}
	static FieldData getFieldData(java.sql.Connection con,int id)throws Exception
	{
		java.sql.PreparedStatement pt=con.prepareStatement("SELECT * FROM FIELDSDATA WHERE id=?");
		pt.setInt(1, id);
		ResultSet rs=pt.executeQuery();
		FieldData fd=null;
		if(rs.next())
		{
			fd=new FieldData();
			fd.setid(id);
			fd.setS(rs.getInt(2));
			fd.setH(rs.getInt(3));
			fd.setT(rs.getInt(4));
			fd.setW(0);
		}
		return fd;
	}
	boolean insert(java.sql.Connection con)
	{
		try
		{
			java.sql.PreparedStatement ps= con.prepareStatement("INSERT INTO FIELDSDATA (id,S,h,t,w) VALUES (?,?,?,?,?);");
			ps.setInt(1, id);
			ps.setInt(2, S);
			ps.setInt(3, H);
			ps.setInt(4, T);
			ps.setInt(5, W);
			int f=ps.executeUpdate();
			con.close();
			if(f>0)
				return true;
			else
			return false;	
			
		}
		catch(Exception e)
		{
			
			System.out.println(e);
			return false;
		}
	}
	void deletePre(java.sql.Connection con)throws Exception
	{
		java.sql.PreparedStatement pt=con.prepareStatement("DELETE FROM FIELDSDATA WHERE id=?");
		pt.setInt(1, id);
		pt.executeUpdate();
	}
	public String toString()
	{
		return String.format("["+id+" "+S+" "+H+" "+T+"]");
	}

}