package ap.mobile.happ.tasks;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import ap.mobile.happ.base.HotelInfo;
import ap.mobile.happ.interfaces.HotelInfoInterface;
import ap.mobile.happ.jsonparser.HotelInfoParser;
import ap.mobile.utility.RestClient;

public class HotelInfoTask extends AsyncTask<String, Void, ArrayList<HotelInfo>> {	
	
	private HotelInfoInterface hotelInfoInterface;
	private String progressText;
	private String errorText;
	

	public HotelInfoTask(Context context, HotelInfoInterface hotelInfoInterface){
		this.hotelInfoInterface = hotelInfoInterface;
	}
	
	@Override
	protected ArrayList<HotelInfo> doInBackground(String... params) {
		try {
			this.progressText = "Getting Information From Server...";
			this.publishProgress();
			String url = params[0];
			String result = RestClient.GET(url);
			ArrayList<HotelInfo> infoList = HotelInfoParser.Parse(result);
			return infoList;
		} catch(Exception e) {
			e.printStackTrace();
			this.errorText = e.getMessage();
		}
		return null;
	}
	
	@Override
	protected void onProgressUpdate(Void... values) {
		super.onProgressUpdate(values);
		this.hotelInfoInterface.onHotelInfoProgress(this.progressText);
	}
	
	@Override
	protected void onPostExecute(ArrayList<HotelInfo> result) {
		if(result == null) {
			this.hotelInfoInterface.onHotelInfoError(this.errorText);
			return;
		}
		this.hotelInfoInterface.onHotelInfoLoaded(result);
	}

}
