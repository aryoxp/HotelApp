package ap.mobile.happ.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.base.AppConfig;
import ap.mobile.happ.base.VodMedia;
import ap.mobile.happ.tasks.ImageLoader;

public class VodIndexAdapter extends BaseAdapter {

	private ArrayList<VodMedia> vodMedias = new ArrayList<VodMedia>();
	private Context context;
	
	public VodIndexAdapter(Context context, ArrayList<VodMedia> vodMedias) {
		this.vodMedias = vodMedias;
		this.context = context;
	}
	 
	@Override
	public int getCount() {
		return this.vodMedias.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.vodMedias.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return this.vodMedias.indexOf(getItem(arg0));
	}

	private class TVItemViewHolder {
		TextView label;
		TextView description;
		ImageView logo;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		TVItemViewHolder vh = null;
		LayoutInflater inflater = (LayoutInflater)
	            context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null) {			
			convertView = inflater.inflate(R.layout.item_vod, parent, false);			
			vh = new TVItemViewHolder();
			vh.label = (TextView) convertView.findViewById(R.id.label);
			vh.logo = (ImageView) convertView.findViewById(R.id.logo);
			vh.description = (TextView) convertView.findViewById(R.id.vodDescription);
			convertView.setTag(vh);			
		} else {
			vh = (TVItemViewHolder) convertView.getTag();
		}

		VodMedia media = this.vodMedias.get(position);
		vh.label.setText(media.name);
		vh.description.setText(Html.fromHtml(media.description));
		try {
			String logoPath = media.logo;
			if(!logoPath.equals(null) && !logoPath.equals("")) {
				ImageLoader iLoader = new ImageLoader();
				iLoader.execute(vh.logo, AppConfig.baseUrl + "image/"+logoPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertView;
	}

}
