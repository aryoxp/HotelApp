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
import ap.mobile.happ.adapters.TVPagerAdapter;
import ap.mobile.happ.base.TVMedia;
import ap.mobile.happ.jsonparser.TVParser;
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
		
		if(!result.equals(null)) {
			ArrayList<TVMedia> TVMedias = TVParser.ParseIndex(result);
			
			FragmentActivity activity = (FragmentActivity)this.context;
			
			TVPagerAdapter tvPagerAdapter = new TVPagerAdapter(activity.getSupportFragmentManager(), TVMedias, context);
		
			ViewPager vPager = (ViewPager) this.view;
			vPager.setAdapter(tvPagerAdapter);

		}
		
		TextView loadingText = (TextView) ((Activity)this.context).findViewById(R.id.loadingText);
		loadingText.setVisibility(View.INVISIBLE);
		
	}

}
