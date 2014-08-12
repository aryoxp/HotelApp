package ap.mobile.happ.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.base.AppConfig;
import ap.mobile.happ.base.RadioMedia;
import ap.mobile.happ.tasks.ImageLoader;

public class RadioIndexAdapter extends BaseAdapter {

	private ArrayList<RadioMedia> RadioMedias = new ArrayList<RadioMedia>();
	private Context context;
	
	public RadioIndexAdapter(Context context, ArrayList<RadioMedia> RadioMedias) {
		this.RadioMedias = RadioMedias;
		this.context = context;
	}
	 
	@Override
	public int getCount() {
		return this.RadioMedias.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.RadioMedias.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return this.RadioMedias.indexOf(getItem(arg0));
	}

	private class TVItemViewHolder {
		TextView label;
		ImageView logo;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TVItemViewHolder vh = null;
		LayoutInflater inflater = (LayoutInflater)
	            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null) {			
			convertView = inflater.inflate(R.layout.item_radio, parent, false);			
			vh = new TVItemViewHolder();
			vh.label = (TextView) convertView.findViewById(R.id.label);
			vh.logo = (ImageView) convertView.findViewById(R.id.logo);
			convertView.setTag(vh);			
		} else {
			vh = (TVItemViewHolder) convertView.getTag();
		}

		RadioMedia media = this.RadioMedias.get(position);
		vh.label.setText(media.name);
		try {
			String logoPath = media.logo;
			if(!logoPath.equals(null)) {
				ImageLoader iLoader = new ImageLoader();
				iLoader.execute(vh.logo, AppConfig.baseUrl + "image/"+logoPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

}
