import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest{
	
 static int differenceDate(String ds1)
 {
	 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	 Date d1 = null;
	 Date d2=new Date();
	 try {
    	 d1 = format.parse(ds1);
         int diffInDays = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
         System.out.print(diffInDays);
         return diffInDays;
    }catch(Exception e)
    {
    	return 0;
    }
 }
 static int getMonth(String d)
 {
	 if(d.equals("january"))
		 return 1;
	 
	 if(d.equals("february"))
		 return 1;
	 
	 if(d.equals("march"))
		 return 1;
	
	 if(d.equals("april"))
		 return 1;
	
	 if(d.equals("may"))
		 return 1;
	
	 if(d.equals("june"))
		 return 1;
	
	 if(d.equals("july"))
		 return 1;
	
	 if(d.equals("august"))
		 return 1;
	
	 if(d.equals("september"))
		 return 1;
	
	 if(d.equals("october"))
		 return 1;
	
	 if(d.equals("november"))
		 return 1;
	
	 if(d.equals("december"))
		 return 1;
	 return 0;
	
 }
}