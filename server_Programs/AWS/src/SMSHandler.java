import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SMSHandler {

	public static int  SendSMS(String mobile,String msg)
	{
		//Your authentication key
        String authkey = "201657AfuCb8TedT5aa590cb";
        //Multiple mobiles numbers separated by comma
        String mobiles = mobile;
        //Sender ID,While using route4 sender id should be 6 characters long.
        String senderId = "inmjkd";
        //Your message to send, Add URL encoding here.
        String message = msg;
        //define route
        String route="4";
        //Prepare Url
        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;
        //encoding message
        String encoded_message=URLEncoder.encode(message);
        //Send SMS API
        String mainUrl="http://my.msgwow.com/api/balance.php?";

        //Prepare parameter string
        StringBuilder sbPostData= new StringBuilder(mainUrl);
        sbPostData.append("authkey="+authkey);
        sbPostData.append("&mobiles="+mobiles);
        sbPostData.append("&message="+encoded_message);
        sbPostData.append("&route="+route);
        sbPostData.append("&sender="+senderId);
        sbPostData.append("&country=91");

        //final string
        mainUrl = sbPostData.toString();
        try
        {
            //prepare connection
            myURL = new URL(mainUrl);
            System.out.println("Testing");
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            //reading response
            String response;
            while ((response = reader.readLine()) != null)
            //print response
            System.out.println(response);

            //finally close connection
            reader.close();
            return 1;
        }
        catch (IOException e)
        {
                e.printStackTrace();
                return 0;
        }
		
	}
	
	
	
	
	
	public static int  SendSMSR(String mobile,String msg,StringBuffer strResponse)
	{
		String authkey = "201657AfuCb8TedT5aa590cb";
		String  sRequestURL;
		String  sData;
		int nResult = -1; 
		sRequestURL ="http://my.msgwow.com/api/balance.php?";

		try
		{		
	
			sData=("authkey="+authkey);
			sData+=("&mobiles="+mobile);
			sData+=("&message="+msg);
			sData+=("&route=4");
			 sData+=("&sender=SMARGR");
			 sData+=("&country=91");
			URL urlObject = new URL(sRequestURL); 
			
			HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
			con.setRequestMethod("POST");
			con.setDoInput (true);
                        con.setDoOutput (true);


			DataOutputStream out;
    		        out = new DataOutputStream(con.getOutputStream());
    		        out.writeBytes (sData);
			out.flush();
			out.close();
    		
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 			
			String inputLine; 
			StringBuffer responseBuffer = new StringBuffer(); 
			while ((inputLine = in.readLine()) != null)
			{
                  responseBuffer = responseBuffer.append(inputLine);
			      responseBuffer = responseBuffer.append("\n\n\n");
			}
		
			strResponse.replace(0,0,responseBuffer.toString());
			System.out.print("Test1");
			String sResultCode = strResponse.substring(0,4);
			nResult = Integer.parseInt(sResultCode);
			in.close();
			return 1;
		}
		catch (Exception e)
		{
			System.out.println("Exception caught sending SMS\n"+e); 
			nResult = -2;
		}
		return nResult;
	}
	
}



