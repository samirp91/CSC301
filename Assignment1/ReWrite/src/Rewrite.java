import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class Rewrite {

	public static void main(String[] args){
		final String foodie = "https://www.dropbox.com/s/te1ue9iyfj8vhpq/RestaurantsName.txt?dl=1";
		final URL[] url = {null};
		try{
		url[0] = new URL(foodie);
        URLConnection con = null;
        con = url[0].openConnection();
        InputStream is = null;
        is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        int count = 1;
        while ((line = br.readLine()) != null) {
        System.out.println(count + "," +line);
        count++;
        }
    }
    catch (Exception e){}
	}
}
