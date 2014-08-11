package ap.mobile.happ;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import ap.mobile.happ.base.STBPage;
import ap.mobile.happ.base.STBPageFragment;

public class HotelInfoFragment extends STBPageFragment {
	
	@SuppressWarnings("unused")
	private Context context;
	private View v;
	private String pageTitle = "Hotel Information";
	private TextView statusText;
	private View statusContainer;
	private ProgressBar progress;
	
	public static HotelInfoFragment getInstance(Context context) {
		HotelInfoFragment fragment = new HotelInfoFragment();
		fragment.setContext(context);
		return fragment;
	}
	
	private void setContext(Context context){
		this.context = context;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.v = inflater.inflate(R.layout.fragment_info_hotel, container, false);
		this.statusText = (TextView) v.findViewById(R.id.infoHotelStatusText);
		this.statusContainer = v.findViewById(R.id.infoHotelStatusContainer);
		this.progress = (ProgressBar) v.findViewById(R.id.infoHotelProgress);
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
		this.statusContainer.setVisibility(View.VISIBLE);
		this.progress.setVisibility(View.VISIBLE);
		this.statusText.setText("Loading Content...");
	}
	
	public void refreshMedia() {
		this.loadMedia();
	}
	
	@Override
	public String getPageTitle() {
		return this.pageTitle;
	}

	@Override
	public STBPage getPageId() {
		return STBPage.Info;
	}

}
