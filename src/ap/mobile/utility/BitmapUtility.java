package ap.mobile.utility;

import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtility {
	
	public static Bitmap getBitmapFromUrl(String url) {
		URL bitmapUrl;
		try {
			bitmapUrl = new URL(url);
			return BitmapFactory.decodeStream(bitmapUrl.openConnection().getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};
}
