/**
 * 
 */
package ap.mobile.happ.tasks;

import java.util.ArrayList;

import android.os.AsyncTask;
import ap.mobile.happ.base.RadioMedia;
import ap.mobile.happ.interfaces.MediaRadioIndexInterface;
import ap.mobile.happ.jsonparser.RadioParser;
import ap.mobile.utility.RestClient;

/**
 * @author Aryo Pinandito
 *
 */
public class RadioIndexTask extends AsyncTask<String, Integer, ArrayList<RadioMedia>> {

	private MediaRadioIndexInterface radioIndexInterface;
	
	public RadioIndexTask(MediaRadioIndexInterface radioIndexInterface) {
		this.radioIndexInterface = radioIndexInterface;
	}
	
	@Override
	protected ArrayList<RadioMedia> doInBackground(String... params) {
		if(!params[0].equals(null)) {
			String result = RestClient.GET(params[0]);
			ArrayList<RadioMedia> medias = RadioParser.ParseIndex(result);
			return medias;
		} return null;
	}
	
	@Override
	protected void onPostExecute(ArrayList<RadioMedia> medias) {
		this.radioIndexInterface.onMediaLoaded(medias);
	}

}
