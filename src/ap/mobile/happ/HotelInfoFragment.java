package ap.mobile.happ;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import ap.mobile.happ.base.HotelInfo;
import ap.mobile.happ.base.STBPage;
import ap.mobile.happ.base.STBPageFragment;
import ap.mobile.happ.interfaces.HotelInfoInterface;
import ap.mobile.happ.tasks.HotelInfoTask;

public class HotelInfoFragment extends STBPageFragment implements HotelInfoInterface, OnItemClickListener {
	
	@SuppressWarnings("unused")
	private Context context;
	private View v;
	private String pageTitle = "Hotel Information";
	private TextView statusText;
	private View statusContainer;
	private ProgressBar progress;
	private ListView infoHotelListView;
	
	private ArrayList<HotelInfo> hotelInfoList = new ArrayList<HotelInfo>();
	private LinearLayout infoHotelContentContainer;
	
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
		this.infoHotelListView = (ListView) v.findViewById(R.id.infoHotelListView);
		this.infoHotelContentContainer = (LinearLayout) v.findViewById(R.id.infoHotelContentContainer);
		this.statusText = (TextView) v.findViewById(R.id.infoHotelStatusText);
		this.statusContainer = v.findViewById(R.id.infoHotelStatusContainer);
		this.progress = (ProgressBar) v.findViewById(R.id.infoHotelProgress);
		
		this.infoHotelListView.setOnItemClickListener(this);
		
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
		
		HotelInfoTask hotelInfoTask = new HotelInfoTask(this.getActivity(), this);
		hotelInfoTask.execute("http://ubcreative.net/apps/hotel/json/info");
		
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

	@Override
	public void onHotelInfoLoadingStarted() {}

	@Override
	public void onHotelInfoProgress(String progressText) {
		this.statusText.setText(progressText);
	}

	@Override
	public void onHotelInfoLoaded(ArrayList<HotelInfo> hotelInfoList) {
		this.hotelInfoList = hotelInfoList;
		this.statusContainer.setVisibility(View.GONE);
		String[] infoList = new String[hotelInfoList.size()];
		int i = 0;
		for(HotelInfo info: hotelInfoList) {
			infoList[i] = info.title;
			i++;
		}
		
		ArrayAdapter<String> infoListAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, infoList);
		this.infoHotelListView.setAdapter(infoListAdapter);
	}

	@Override
	public void onHotelInfoError(String errorText) {
		this.statusText.setText(errorText);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(this.hotelInfoList != null && this.hotelInfoList.size() > 0) {
			HotelInfo hotelInfo = this.hotelInfoList.get(position);
			LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			
			View hotelInfoView = inflater.inflate(R.layout.item_hotel_info, this.infoHotelContentContainer, false);
			
			
			
			TextView hotelInfoTitleTextView = (TextView) hotelInfoView.findViewById(R.id.hotelInfoTitle);
			TextView hotelInfoDescriptionTextView = (TextView) hotelInfoView.findViewById(R.id.hotelInfoDescription);
			
			hotelInfoTitleTextView.setText(hotelInfo.title);
			hotelInfoDescriptionTextView.setText(Html.fromHtml(hotelInfo.description));
			this.infoHotelContentContainer.removeAllViews();
			this.infoHotelContentContainer.addView(hotelInfoView, 0);
			
		}
	}

}
