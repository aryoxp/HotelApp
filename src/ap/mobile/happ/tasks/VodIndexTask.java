/**
 * 
 */
package ap.mobile.happ.tasks;

import java.util.ArrayList;

import android.os.AsyncTask;
import ap.mobile.happ.base.VodMedia;
import ap.mobile.happ.interfaces.MediaVodIndexInterface;
import ap.mobile.happ.jsonparser.VodParser;
import ap.mobile.utility.RestClient;

/**
 * @author Aryo Pinandito
 *
 */
public class VodIndexTask extends AsyncTask<String, Integer, ArrayList<VodMedia>> {

	private MediaVodIndexInterface vodIndexInterface;
	
	public VodIndexTask(MediaVodIndexInterface vodIndexInterface) {
		this.vodIndexInterface = vodIndexInterface;
	}
	
	@Override
	protected ArrayList<VodMedia> doInBackground(String... params) {
		if(!params[0].equals(null)) {
			String result = RestClient.GET(params[0]);
			ArrayList<VodMedia> medias = VodParser.ParseIndex(result);
			return medias;
		} return null;
	}
	
	@Override
	protected void onPostExecute(ArrayList<VodMedia> medias) {
		this.vodIndexInterface.onMediaLoaded(medias);
	}

}
