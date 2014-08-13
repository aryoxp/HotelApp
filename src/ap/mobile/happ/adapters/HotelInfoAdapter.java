package ap.mobile.happ.adapters;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.base.HotelInfo;

public class HotelInfoAdapter extends BaseAdapter {
	
	private ArrayList<HotelInfo> hotelInfoList;
	private Context context;

	public HotelInfoAdapter(Context context, ArrayList<HotelInfo> hotelInfoList) {
		this.context = context;
		this.hotelInfoList = hotelInfoList;
	}
	
	@Override
	public int getCount() {
		if(this.hotelInfoList != null)
			return this.hotelInfoList.size();
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return this.hotelInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		public TextView label;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null) {
			
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.item_hotel_info, parent, false);
			ViewHolder vh = new ViewHolder(); 
			vh.label = (TextView) convertView.findViewById(R.id.itemHotelInfoTextView);
			convertView.setTag(vh);
		}
		
		ViewHolder vh = (ViewHolder) convertView.getTag();
		
		HotelInfo info = this.hotelInfoList.get(position);
		vh.label.setText(info.title);
		return convertView;
		
	}

}
