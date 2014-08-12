package ap.mobile.happ.tasks;

import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import ap.mobile.happ.base.AppConfig;
import ap.mobile.happ.base.DefaultContent;
import ap.mobile.happ.interfaces.DefaultContentInterface;
import ap.mobile.happ.jsonparser.DefaultContentParser;
import ap.mobile.utility.RestClient;

public class DefaultTask extends AsyncTask<String, Void, String> {	
	
	private DefaultContentInterface defaultContentInterface;
	private String progressText;
	private String errorText;
	private Bitmap background;
	private String welcomeText;
	private String cityName;
	private String language;
	private String hotelName;

	public DefaultTask(Context context, DefaultContentInterface defaultContentInterface){
		this.defaultContentInterface = defaultContentInterface;
	}
	
	@Override
	protected String doInBackground(String... params) {
		try {
			this.progressText = "Getting Information From Server...";
			this.publishProgress();
			String url = params[0];
			String result = RestClient.GET(url);
			this.progressText = "Processing Content...";
			this.publishProgress();
			DefaultContent content = DefaultContentParser.Parse(result);
			URL bitmapUrl = new URL(AppConfig.baseUrl + "image/"+content.backgroundUrl);
			this.background = BitmapFactory.decodeStream(bitmapUrl.openConnection().getInputStream());
			this.cityName = content.cityName;
			this.welcomeText = content.welcomeText;
			this.language = content.language;
			this.hotelName = content.hotelName;
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			this.errorText = e.getMessage();
		}
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		this.defaultContentInterface.onDefaultContentProgress(this.progressText);
	}
	
	@Override
	protected void onPostExecute(String result) {
		if(result == null) {
			this.defaultContentInterface.onDefaultContentError(this.errorText);
			return;
		}
		this.defaultContentInterface.onDefaultContentLoaded(this.background, this.hotelName, this.welcomeText, this.cityName, this.language);
	}

}
