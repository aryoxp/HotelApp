package ap.mobile.happ.interfaces;

import android.graphics.Bitmap;

public interface DefaultContentInterface {
	public void onDefaultContentLoadingStarted();
	public void onDefaultContentProgress(String progressText);
	public void onDefaultContentLoaded(Bitmap background, String hotelName, String welcomeText, String cityName, String language);
	public void onDefaultContentError(String errorText);
}
