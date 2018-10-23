import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
 
public class EncodeUrl {
 
  public static void main(String args[]) {
 
    try {
 
	String url = "http://my.msgwow.com/api/sendhttp.php?authkey=201657AWiB0ruSqV5aa126cb&mobiles=918630321244&message=hello&sender=Abc&route=4&country=91&flash=0&unicode=1";
 
	String encodedUrl = URLEncoder.encode(url, "UTF-8");
 
	System.out.println("Encoded URL " + encodedUrl);
 

	} catch (UnsupportedEncodingException e) {
 
		System.err.println(e);
 
	}
    }
}