package ap.mobile.happ.interfaces;

import java.util.ArrayList;

import ap.mobile.happ.base.HotelInfo;

public interface HotelInfoInterface {
	public void onHotelInfoLoadingStarted();
	public void onHotelInfoProgress(String progressText);
	public void onHotelInfoLoaded(ArrayList<HotelInfo> hotelInfoList);
	public void onHotelInfoError(String errorText);
}
