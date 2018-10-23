import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.*;

public class Field {
	private int id;
	private String label;
	private String crop;
	private int invest;
	private String phone;
	private String email;
	private String state;
	private String date;
	public Field(int id,String label,String crop,int invest,String phone,String email,String state,String date)
	{
		this.id=id;
		this.label=label;
		this.crop=crop;
		this.invest=invest;
		this.phone=phone;
		this.email=email;
		this.state=state;
		this.date=date;
	}
	String getLabel()
	{
		return label;
	}
	int getId()
	{
		return id;
	}
	String getPhone()
	{
		return phone;
	}
	int getInvest()
	{
		return invest;
	}
	String getCrop()
	{
		return crop;
	}
	String getDate()
	{
		return date;
	}
	String getState()
	{
		return this.state;
	}
	public static Field getField(int id,Connection con)throws Exception
	{
		PreparedStatement pt=con.prepareStatement("SELECT * FROM FIELDS WHERE ID=?");
				pt.setInt(1, id);
		ResultSet rs=pt.executeQuery();
		Field f=null;
		if(rs.next())
		{
			f=new Field(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
		}
		return f;
	}
	public String toString()
	{
		    return "Fields [" + id + " " + label +
			       " " + crop+" "+invest+" "+phone+" "+email+" "+state+" "+date+"]";
	
	}
	

}
