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

public class RadioFragment extends Fragment {
	
	private Context context;
	private ArrayList<TVMedia> TVMedias;
	private int perpage = 10;
	private int page = 0;
	
	public RadioFragment(){}
	
	public void setContext(Context context){
		this.context = context;
	}
	
	public void setMedias(ArrayList<TVMedia> medias) {
		this.TVMedias = medias;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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
		View v = inflater.inflate(R.layout.fragment_index_radio, container, false);
		GridView gv = (GridView) v.findViewById(R.id.IndexGrid);
		
		TVIndexAdapter adapter = new TVIndexAdapter(this.context, medias);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				TVIndexAdapter adapter = (TVIndexAdapter) parent.getAdapter();
				TVMedia media = (TVMedia) adapter.getItem(position);
				//Toast.makeText(context, media.name, Toast.LENGTH_SHORT).show();
				
				String stream = media.stream;
				if(stream != null) {
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(stream));
					((Activity)context).startActivity(i);
				} else {
					Toast.makeText(context, "Media stream URL is not available", Toast.LENGTH_SHORT).show();
				}
			}
			
		});
		
		return v;
	}
}
