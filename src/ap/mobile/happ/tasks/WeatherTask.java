package ap.mobile.happ.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.base.Weather;
import ap.mobile.happ.jsonparser.WeatherParser;
import ap.mobile.utility.RestClient;

public class WeatherTask extends AsyncTask<String, Void, String> {

	@SuppressWarnings("unused")
	private Context context;
	private ImageView iconView;
	private TextView cityView;
	private TextView weatherView;
	private TextView weatherDescription;
	
	
	public WeatherTask(Context context, 
			ImageView iconView, TextView cityView, 
			TextView weatherView, TextView weatherDescription){
		this.context = context;
		this.iconView = iconView;
		this.cityView = cityView;
		this.weatherView = weatherView;
		this.weatherDescription = weatherDescription;
	}
	
	@Override
	protected String doInBackground(String... params) {
		try {
			String url = params[0];
			String result = RestClient.GET(url);
			return result;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		if(result != "") {
			Weather w = WeatherParser.Parse(result);
			if(!w.equals(null)) {
				int temp = (int) Math.ceil(w.temperature);
				if(this.weatherView != null)
					this.weatherView.setText(w.main + ", " + Integer.toString(temp) + (char) 0x00B0 + "C");
				if(this.weatherDescription != null)
					this.weatherDescription.setText(w.description);
				if(this.cityView != null)
					this.cityView.setText(w.city);
				
				switch(w.id){
					case 200:
					case 210:
						this.iconView.setImageResource(R.drawable.m_weather_17);
						break;
					case 201:
					case 211:
						this.iconView.setImageResource(R.drawable.m_weather_16);
						break;
					case 202:
					case 212:
					case 221:
					case 230:
					case 231:
					case 232:
						this.iconView.setImageResource(R.drawable.m_weather_15);
						break;
					case 500:
					case 520:
						this.iconView.setImageResource(R.drawable.m_weather_14);
						break;
					case 501:
					case 521:
						this.iconView.setImageResource(R.drawable.m_weather_13);
						break;
					case 502:
					case 522:
						this.iconView.setImageResource(R.drawable.m_weather_12);
						break;
					case 503:
						this.iconView.setImageResource(R.drawable.m_weather_18);
						break;
					case 504:
					case 511:
					case 531:
						this.iconView.setImageResource(R.drawable.m_weather_12a);
						break;
					case 701:
					case 711:
					case 731:
					case 741:
					case 751:
					case 761:
						this.iconView.setImageResource(R.drawable.m_weather_11);
						break;			
					case 721:
					case 762:
					case 771:
					case 781:
					default:
						this.iconView.setImageResource(R.drawable.m_weather_32b);
				}
			}
		} else {
			// TODO: show weather error icon
		}
	}

}
