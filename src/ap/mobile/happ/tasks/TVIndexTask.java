/**
 * 
 */
package ap.mobile.happ.tasks;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.GridView;
import ap.mobile.happ.adapters.TVIndexAdapter;
import ap.mobile.happ.base.TVMedia;
import ap.mobile.utility.RestClient;

/**
 * @author Aryo Pinandito
 *
 */
public class TVIndexTask extends AsyncTask<String, Integer, String> {

	private View view;
	private Context context;
	
	public TVIndexTask(Context context, View view) {
		this.view = view;
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... params) {
		if(!params[0].equals(null))
			return RestClient.GET(params[0]);
		return null;
	}
	
	@Override
	protected void onPostExecute(String result) {
		GridView gv = (GridView) this.view;
		ArrayList<TVMedia> TVMedias = new ArrayList<TVMedia>();
		// TODO: Parse result to JSON Object, then JSON Object to array of TV media.
		TVMedias.add(new TVMedia("RCTI", "Dummy Description", "Dummy file URL", "Dummy Stream"));
		TVMedias.add(new TVMedia("Fuji TV", "Dummy Description", "Dummy file URL", "Dummy Stream"));
		TVIndexAdapter adapter = new TVIndexAdapter(this.context, TVMedias);
		gv.setAdapter(adapter);
	}

}
