
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.machinelearning.model.PredictResult;
import com.amazonaws.services.machinelearning.model.Prediction;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String str[]= {};
		//BuildModel.main1(str);
		//String arg[]= {"var1=1","var2=wheat","var3=34","var4=32","var5=34"};
		//	Prediction p=RealtimePredict.main(arg);
		//response.getWriter().print(p);
		/*response.getWriter().println("Good here");
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJYE5NDYBZFHDNNVA", "lCWAHVyQV3DEzrT72Zna3RndhV2MO5Vqwvbf4Q8q");
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
		                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
		                        .build();
		Region region = Region.getRegion(Regions.EU_WEST_2);
	    s3Client.setRegion(region);
		AndroidRealtimePrediction p=new AndroidRealtimePrediction("ml-mgaflal4cjA ",awsCreds);
		java.util.Map<String,String> hm=new HashMap<String,String>();
		hm.put("s.no", "40");
		hm.put("ct", "wheat");
		hm.put("td", "30");
		hm.put("sm", "302");
		hm.put("tm", "32");
		hm.put, "32");("hm"
		response.getWriter().println("Good here");
		PredictResult pr=p.predict(hm);
		response.getWriter().print(pr.getPrediction());*/
		/*String str[]={"s.no=40","ct=wheat","td=30","sm=302","tm=32","hm=32"};
		Prediction p=RealtimePredict.main(str);
		response.getWriter().print(p.getPredictedValue());*/
		//StringBuffer str=new StringBuffer();
		//SMSHandler.SendSMS("8630321244", "HelloTest",str);
		///System.out.println(str);
		//String args[]={"Testing","k"};
		//SMSHandler.SendSMS("918630321244", "Test");
		//request.HitXmlData.main1(args);
		/*FieldData f=new FieldData();
		f.setH(32);
		f.setS(800);
		f.setT(34);
		f.setW(0);
		String cropType="Wheat";
		int days=DateTest.differenceDate("23/12/2017");
		String record[]= {"34",cropType,""+days,""+f.S,""+f.T,""+f.H};
		Boolean b=IrrigationPrediction.isIrrigationRequired(record);
		response.getWriter().println(b);
		*/
		//HttpUrlConnectionExample urr=new HttpUrlConnectionExample();
		StringBuffer str=new StringBuffer();
		SMSHandler.SendSMSR("918630321244", "Hello Test", str);
		response.getWriter().println(str);
		//IrrigationPrediction.isIrrigationRequired(record)
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
