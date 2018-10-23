import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.sql.*;

public class TimePrediction {
	static String timePrediction(Field f,Connection con)throws Exception{
		String besttime="";
		String state=f.getState();
		state=state.trim();
		Month month=Month.from(LocalDate.now());
		String mname=month.name();
		PreparedStatement pt=con.prepareStatement("SELECT * FROM cropcalendar where state=? AND perio=?");
		pt.setString(1, state);
		pt.setString(2, "sowing");
		ResultSet rs=pt.executeQuery();
		while(rs.next())
		{
			if(month.getValue()>=DateTest.getMonth(rs.getString(4))&& month.getValue()<=DateTest.getMonth(rs.getString(4)))
			besttime=besttime+rs.getString(2)+",";
		}
		if(besttime.equals(""))
			besttime="Not Available";
		return besttime;
	}

	
}
