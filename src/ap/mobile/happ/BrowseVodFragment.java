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
import ap.mobile.happ.adapters.VodIndexAdapter;
import ap.mobile.happ.base.AppConfig;
import ap.mobile.happ.base.STBPage;
import ap.mobile.happ.base.STBPageFragment;
import ap.mobile.happ.base.VodMedia;
import ap.mobile.happ.interfaces.MediaVodIndexInterface;
import ap.mobile.happ.tasks.VodIndexTask;

public class BrowseVodFragment extends STBPageFragment implements MediaVodIndexInterface {
	
	private Context context;
	private ArrayList<VodMedia> vodMedias = new ArrayList<VodMedia>();
	//private int perpage = 10;
	//private int page = 0;
	
	private View v;
	private GridView gv;
	private String pageTitle = "Video on Demand";
	private TextView statusText;
	private View statusContainer;
	private VodIndexTask indexTask;
	private ProgressBar progress;
	
	public static BrowseVodFragment getInstance(Context context) {
		BrowseVodFragment fragment = new BrowseVodFragment();
		fragment.setContext(context);
		return fragment;
	}
	
	private void setContext(Context context){
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.v = inflater.inflate(R.layout.fragment_index_tv, container, false);
		this.gv = (GridView) v.findViewById(R.id.indexTvGrid);
		this.statusText = (TextView) v.findViewById(R.id.indexTvStatusText);
		this.statusContainer = v.findViewById(R.id.indexTvStatusContainer);
		this.progress = (ProgressBar) v.findViewById(R.id.indexTvProgress);
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
		
		this.indexTask = new VodIndexTask(this);
		this.indexTask.execute(AppConfig.baseUrl + "json/video");
		this.statusContainer.setVisibility(View.VISIBLE);
		this.progress.setVisibility(View.VISIBLE);
		this.statusText.setText("Loading Media...");
	}
	
	public void refreshMedia() {
		this.loadMedia();
	}
	
	@Override
	public void onMediaLoaded(ArrayList<VodMedia> medias) {
		if(medias == null) 
		{
			this.statusText.setText("Error: Unable to load media.");
			this.progress.setVisibility(View.INVISIBLE);
			return;
		}
		this.statusContainer.setVisibility(View.INVISIBLE);
		this.vodMedias = medias;
		VodIndexAdapter adapter = new VodIndexAdapter(this.context, medias);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				try {
					VodIndexAdapter adapter = (VodIndexAdapter) parent.getAdapter();
					VodMedia media = (VodMedia) adapter.getItem(position);
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
	
	public ArrayList<VodMedia> getMedias() {
		return this.vodMedias;
	}
	
	@Override
	public String getPageTitle() {
		return this.pageTitle;
	}

	@Override
	public STBPage getPageId() {
		return STBPage.VideoOnDemand;
	}

}
