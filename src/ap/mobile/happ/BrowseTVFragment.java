package ap.mobile.happ;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import ap.mobile.happ.adapters.TVIndexAdapter;
import ap.mobile.happ.base.TVMedia;
import ap.mobile.happ.interfaces.MediaTVIndexInterface;
import ap.mobile.happ.tasks.TVIndexTask;

public class BrowseTVFragment extends Fragment implements MediaTVIndexInterface {
	
	private Context context;
	private ArrayList<TVMedia> tvMedias = new ArrayList<TVMedia>();
	private int perpage = 10;
	private int page = 0;
	
	private View v;
	private GridView gv;
	
	public static BrowseTVFragment getInstance(Context context) {
		BrowseTVFragment fragment = new BrowseTVFragment();
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
		return this.v;
	}
	
	@Override
	public void onResume() {
		TVIndexTask indexTask = new TVIndexTask(this);
        //indexTask.execute("http://175.45.187.246/iptv/index.php/service/index/tv");
		indexTask.execute("http://ubcreative.net/apps/hotel/json/tv/");
		super.onResume();
	}
	
	@Override
	public void onMediaLoaded(ArrayList<TVMedia> medias) {
		this.tvMedias = medias;
		/*
		Bundle bundle = this.getArguments();
		this.page = bundle.getInt("page");
		int offset = (this.page-1)*perpage;
		int max = offset + 10;
		if(max > this.TVMedias.size()) 
			max = this.TVMedias.size();
		ArrayList<TVMedia> medias = new ArrayList<TVMedia>();
		for(int i = offset; i<max; i++) {
			TVMedia media = this.TVMedias.get(i);
			medias.add(media);
		}
		*/
		
		TVIndexAdapter adapter = new TVIndexAdapter(this.context, medias);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				try {
					TVIndexAdapter adapter = (TVIndexAdapter) parent.getAdapter();
					TVMedia media = (TVMedia) adapter.getItem(position);
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

}
