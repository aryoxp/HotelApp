package ap.mobile.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RestClient {
	
	// Source: http://www.vogella.com/tutorials/AndroidNetworking/article.html
	// with modification
	
	public static String GET (String Url) {
		// Somewhere in your code this is called
		// in a thread which is not the user interface
		// thread
		
		try {
		  URL url = new URL(Url);
		  HttpURLConnection con = (HttpURLConnection) url
		    .openConnection();
		  return readStream(con.getInputStream());
		  } catch (Exception e) {
		  e.printStackTrace();
		}
		return "";		
	}
	
	private static String readStream(InputStream in) {
		BufferedReader reader = null;
		String result = "";
		try {
		    reader = new BufferedReader(new InputStreamReader(in));
		    String line = "";
		    while ((line = reader.readLine()) != null) {
		    	//System.out.println(line);
		    	result = result + line;
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (reader != null) {
		      try {
		        reader.close();
		      } catch (IOException e) {
		        e.printStackTrace();
		        }
		    }
		}
		return result;
	} 
	
	// http://stackoverflow.com/questions/8992964/android-load-from-url-to-bitmap
	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.setConnectTimeout(30000);
	        connection.setReadTimeout(30000);
	        connection.setInstanceFollowRedirects(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
