import java.sql.Connection;
import java.sql.PreparedStatement;

class Decisions
{
	Boolean irrigationRequired;
	String bestTypeOfCrop;
	String bestTimeOfSowing;
	int investmentTillNow;
	int id;
	void setbestTimeOfIrregration(Boolean bt)
	{
		this.irrigationRequired=bt;
	}
	void setbestTypeOfCrop(String bc)
	{
		this.bestTypeOfCrop=bc;
	}
	void setbestTimeOfSowing(String bs)
	{
		this.bestTimeOfSowing= bs;
	}
	void setinvestmentTillNow(int is)
	{
		this.investmentTillNow=is;
	}
	void setid(int i1)
	{
		this.id=i1;
	}
	Boolean getbestTimeOfIrregration()
	{
		return irrigationRequired;
	}
	String getbestTypeOfCrop()
	{
		return bestTypeOfCrop;
	}
	String getbestTimeOfSowing()
	{
		return bestTimeOfSowing;
	}
	int getinvestmentTillNow()
	{
		return investmentTillNow;
	}
	int getid()
	{
		return id;
	}
	void insert(Connection con)throws Exception
	{
		PreparedStatement pt=con.prepareStatement("INSERT INTO DECISIONS (id,irrigation,crop,time) VALUES (?,?,?,?)");
		pt.setInt(1, id);
		pt.setString(2, ""+this.irrigationRequired);
		pt.setString(3, bestTypeOfCrop);
		pt.setString(4, bestTimeOfSowing);
		pt.executeUpdate();
	}
	void deletePre(Connection con)throws Exception
	{
		PreparedStatement pt=con.prepareStatement("DELETE FROM DECISIONS WHERE id=?");
		pt.setInt(1, id);
		pt.executeUpdate();
	}
	public String toString()
	{
		return String.format("["+id+" "+irrigationRequired+" "+bestTypeOfCrop+" "+bestTimeOfSowing+" "+investmentTillNow+"]");
	}
}