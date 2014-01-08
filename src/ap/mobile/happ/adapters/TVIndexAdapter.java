package ap.mobile.happ.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.base.TVMedia;

public class TVIndexAdapter extends BaseAdapter {

	private ArrayList<TVMedia> TVMedias = new ArrayList<TVMedia>();
	private Context context;
	
	public TVIndexAdapter(Context context, ArrayList<TVMedia> TVMedias) {
		this.TVMedias = TVMedias;
		this.context = context;
	}
	 
	@Override
	public int getCount() {
		return this.TVMedias.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.TVMedias.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return this.TVMedias.indexOf(getItem(arg0));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater)
	            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.item_tv, null);
		TextView label = (TextView) itemView.findViewById(R.id.label);
		label.setText(this.TVMedias.get(position).name);
		return itemView;
	}

}
