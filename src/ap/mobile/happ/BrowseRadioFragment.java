package ap.mobile.happ;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import ap.mobile.happ.adapters.RadioIndexAdapter;
import ap.mobile.happ.base.STBPage;
import ap.mobile.happ.base.STBPageFragment;
import ap.mobile.happ.base.RadioMedia;
import ap.mobile.happ.interfaces.MediaRadioIndexInterface;
import ap.mobile.happ.tasks.RadioIndexTask;

public class BrowseRadioFragment extends STBPageFragment implements MediaRadioIndexInterface {
	
	private Context context;
	private ArrayList<RadioMedia> RadioMedias = new ArrayList<RadioMedia>();
	//private int perpage = 10;
	//private int page = 0;
	
	private View v;
	private GridView gv;
	private String pageTitle = "Radio";
	private TextView statusText;
	private View statusContainer;
	private RadioIndexTask indexTask;
	private ProgressBar progress;
	
	public static BrowseRadioFragment getInstance(Context context) {
		BrowseRadioFragment fragment = new BrowseRadioFragment();
		fragment.setContext(context);
		return fragment;
	}
	
	private void setContext(Context context){
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.v = inflater.inflate(R.layout.fragment_index_radio, container, false);
		this.gv = (GridView) v.findViewById(R.id.indexRadioGrid);
		this.statusText = (TextView) v.findViewById(R.id.indexRadioStatusText);
		this.statusContainer = v.findViewById(R.id.indexRadioStatusContainer);
		this.progress = (ProgressBar) v.findViewById(R.id.indexRadioProgress);
		return this.v;
	}
	
	@Override
	public void onResume() {
		try {
			this.getMainActivityInterface().setPageTitle(this.pageTitle );
		} catch(Exception ex) {
			
		}
		loadMedia();
		super.onResume();
	}

	private void loadMedia() {
		if(this.indexTask != null && this.indexTask.getStatus() == Status.RUNNING)
			return;
		
		this.indexTask = new RadioIndexTask(this);
        //indexTask.execute("http://175.45.187.246/ipRadio/index.php/service/index/Radio");
		this.indexTask.execute("http://ubcreative.net/apps/hotel/json/radio/");
		this.statusContainer.setVisibility(View.VISIBLE);
		this.progress.setVisibility(View.VISIBLE);
		this.statusText.setText("Loading Media...");
	}
	
	public void refreshMedia() {
		this.loadMedia();
	}
	
	@Override
	public void onMediaLoaded(ArrayList<RadioMedia> medias) {
		if(medias == null) 
		{
			this.statusText.setText("Error: Unable to load media.");
			this.progress.setVisibility(View.INVISIBLE);
			return;
		}
		this.statusContainer.setVisibility(View.INVISIBLE);
		this.RadioMedias = medias;
		RadioIndexAdapter adapter = new RadioIndexAdapter(this.context, medias);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				try {
					RadioIndexAdapter adapter = (RadioIndexAdapter) parent.getAdapter();
					RadioMedia media = (RadioMedia) adapter.getItem(position);
					//Toast.makeText(context, media.name, Toast.LENGTH_SHORT).show();
					
					String stream = media.stream;
					if(stream != null) {
						/*
						Intent i = new Intent(Intent.ACTION_VIEW);
						i.setData(Uri.parse(stream));
						//((Fragment)context).getActivity().startActivity(i);
						((Activity)context).startActivity(i);
						*/
						Intent i = new Intent(context, PlayActivity.class);
						i.putExtra("url", stream);
						((Activity)context).startActivity(i);
					} else {
						Toast.makeText(context, "Media stream URL is not available", Toast.LENGTH_SHORT).show();
					}
				} catch(Exception ex) {
					Toast.makeText(context, "Sorry, unable to start media. " + ex.getMessage(), Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
	}
	
	public ArrayList<RadioMedia> getMedias() {
		return this.RadioMedias;
	}
	
	@Override
	public String getPageTitle() {
		return this.pageTitle;
	}

	@Override
	public STBPage getPageId() {
		return STBPage.Radio;
	}

}
