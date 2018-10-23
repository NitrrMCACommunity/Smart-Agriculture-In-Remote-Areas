import java.sql.DriverManager;
import java.text.SimpleDateFormat;

import org.joda.time.DateTime;

import java.sql.*;
public class ProcessData {
	FieldData fd;
	
	ProcessData(FieldData fd)
	{
		this.fd=fd;
	}
	public Decisions predict()throws Exception
	{
		Connection con=this.getDbConnection();
		Field f=Field.getField(fd.getid(), con);
		String cdate=f.getDate();
		int days=DateTest.differenceDate(cdate);
		String cropType=f.getCrop();
		String record[]= {"34",cropType,""+days,""+fd.S,""+fd.T,""+fd.H};
		Boolean irrigation=IrrigationPrediction.isIrrigationRequired(record);
		String bestCrop="flowers";//CropPrediction.bestCrop(f,con);
		String timeCrop="26 March";//TimePrediction.timePrediction(f,con);
		Decisions d=new Decisions();
		d.setbestTimeOfIrregration(irrigation);
		d.setbestTimeOfSowing(timeCrop);
		d.setbestTypeOfCrop(bestCrop);
		d.setinvestmentTillNow(f.getInvest());
		d.setid(f.getId());
		d.deletePre(con);
		d.insert(con);
		if(d.irrigationRequired)
		{
			String msg="Your Field "+f.getLabel()+" with id "+f.getId()+" required irrigation  "+
							" for more details open your Android application\n @SmartAgriculture Nitrr";
			SMSHandler.SendSMS(f.getPhone(), msg);
		}
		con.close();
		return d;
	}
	
	
	
	Connection getDbConnection()
	{
		try {
		String dbName = System.getProperty("RDS_DB_NAME");
		  String userName = System.getProperty("RDS_USERNAME");
		  String password = System.getProperty("RDS_PASSWORD");
		  String hostname = System.getProperty("RDS_HOSTNAME");
		  String port = System.getProperty("RDS_PORT");
		  String jdbcUrl = "jdbc:mysql://" + hostname + ":" +port + "/" + dbName + "?user=" + userName + "&password=" + password;
		 Class.forName("com.mysql.jdbc.Driver");
		 java.sql.Connection conn =DriverManager.getConnection(jdbcUrl);
		 return conn;
		}catch(Exception e)
		{
			return null;
		}
	
	}

}
