/**
 * 
 */
package ap.mobile.happ.tasks;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.adapters.RadioPagerAdapter;
import ap.mobile.happ.base.TVMedia;
import ap.mobile.happ.jsonparser.TVParser;
import ap.mobile.utility.RestClient;

/**
 * @author Aryo Pinandito
 *
 */
public class RadioIndexTask extends AsyncTask<String, Integer, String> {

	private View view;
	private Context context;
	private RadioPagerAdapter radioPagerAdapter;
	
	public RadioIndexTask(Context context, View view) {
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
		//GridView gv = (GridView) this.view;
		if(!result.equals(null)) {
			ArrayList<TVMedia> TVMedias = TVParser.ParseIndex(result);
			
			
			FragmentActivity activity = (FragmentActivity)this.context;
			this.radioPagerAdapter = new RadioPagerAdapter(activity.getSupportFragmentManager(), TVMedias, context);
			ViewPager vPager = (ViewPager) this.view;
			vPager.setAdapter(this.radioPagerAdapter);
			
			// TODO: Parse result to JSON Object, then JSON Object to array of TV media.
			// TODO: Remove dummy data after
			
			//TVMedias.add(new TVMedia("RCTI", "Dummy Description", "Dummy file URL", "Dummy Stream"));
			//TVMedias.add(new TVMedia("Fuji TV", "Dummy Description", "Dummy file URL", "Dummy Stream"));
			//TVMedias.add(new TVMedia("HBO Family", "Dummy Description", "Dummy file URL", "Dummy Stream"));
			/*
			
			*/
		}
		
		TextView loadingText = (TextView) ((Activity)this.context).findViewById(R.id.loadingText);
		loadingText.setVisibility(View.INVISIBLE);
		
	}

}
