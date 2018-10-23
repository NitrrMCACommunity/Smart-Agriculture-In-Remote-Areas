

import java.io.*;
import java.net.URL;
import java.net.HttpURLConnection;

class Functions
{

	public static String hitUrl(String urlToHit, String param)
    	{
        	try
        	{
                URL url = new URL(urlToHit);
                HttpURLConnection http = (HttpURLConnection)url.openConnection();
                http.setDoOutput(true);
                http.setDoInput(true);
                http.setRequestMethod("POST");

                DataOutputStream wr = new DataOutputStream(http.getOutputStream());
                wr.writeBytes(param);
                // use wr.write(param.getBytes("UTF-8")); for unicode message's instead of wr.writeBytes(param);

                wr.flush();
                wr.close();
                http.disconnect();


                BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String inputLine;
                if ((inputLine = in.readLine()) != null)
                {
                        in.close();
                        return inputLine;
                }
                else
                {
                        in.close();
                        return "-1";
                }

            }
            catch(Exception e)
            {
                System.out.println("Exception Caught..!!!");
                e.printStackTrace();
                return "-2";
            }
        }
}

public class HitXmlData
{
	public static void main1(String[] args)
	{
    		String strUrl = "http://my.msgwow.com/api/postsms.php";
    		String xmlData = "data=<MESSAGE> <AUTHKEY>201657AWiB0ruSqV5aa126cb</AUTHKEY>"+
                                        "<ROUTE>default</ROUTE>"+
                                        "<SMS TEXT='message1 testing' FROM='AAAAAA'>"+
                                        "<ADDRESS TO='918630321244'></ADDRESS>"+
                                        "</SMS>"+
                                    "</MESSAGE>";

		String output = Functions.hitUrl(strUrl, xmlData);
		System.out.println("Output is: "+output);

	}

}

