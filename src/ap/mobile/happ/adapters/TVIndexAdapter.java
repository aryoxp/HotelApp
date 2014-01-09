package ap.mobile.happ.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.base.TVMedia;
import ap.mobile.happ.tasks.ImageLoader;

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

	private class TVItemViewHolder {
		TextView label;
		ImageView logo;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Typeface fontNormal = Typeface.createFromAsset(((Activity)this.context).getAssets(), "fonts/Helvetica-Medium-Condensed.ttf"); 
		TVItemViewHolder vh = null;
		LayoutInflater inflater = (LayoutInflater)
	            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null) {			
			convertView = inflater.inflate(R.layout.item_tv, null);			
			vh = new TVItemViewHolder();
			vh.label = (TextView) convertView.findViewById(R.id.label);
			vh.label.setTypeface(fontNormal);
			vh.logo = (ImageView) convertView.findViewById(R.id.logo);
			convertView.setTag(vh);			
		} else {
			vh = (TVItemViewHolder) convertView.getTag();
		}

		TVMedia media = this.TVMedias.get(position);
		vh.label.setText(media.name);
		
		String logoPath = media.logo;
		ImageLoader iLoader = new ImageLoader();
		iLoader.execute(vh.logo, "http://175.45.187.246/"+logoPath);
		
		return convertView;
	}

}
