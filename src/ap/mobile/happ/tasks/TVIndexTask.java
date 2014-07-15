/**
 * 
 */
package ap.mobile.happ.tasks;

import java.util.ArrayList;

import android.os.AsyncTask;
import ap.mobile.happ.base.TVMedia;
import ap.mobile.happ.interfaces.MediaTVIndexInterface;
import ap.mobile.happ.jsonparser.TVParser;
import ap.mobile.utility.RestClient;

/**
 * @author Aryo Pinandito
 *
 */
public class TVIndexTask extends AsyncTask<String, Integer, ArrayList<TVMedia>> {

	private MediaTVIndexInterface tvIndexInterface;
	
	public TVIndexTask(MediaTVIndexInterface tvIndexInterface) {
		this.tvIndexInterface = tvIndexInterface;
	}
	
	@Override
	protected ArrayList<TVMedia> doInBackground(String... params) {
		if(!params[0].equals(null)) {
			String result = RestClient.GET(params[0]);
			ArrayList<TVMedia> medias = TVParser.ParseIndex(result);
			return medias;
		} return null;
	}
	
	@Override
	protected void onPostExecute(ArrayList<TVMedia> medias) {
		this.tvIndexInterface.onMediaLoaded(medias);
	}

}
