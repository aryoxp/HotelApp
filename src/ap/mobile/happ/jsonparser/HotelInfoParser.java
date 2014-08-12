package ap.mobile.happ.jsonparser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ap.mobile.happ.base.HotelInfo;

public class HotelInfoParser {
	
	public static ArrayList<HotelInfo> Parse(String JSONString){
		try {
			JSONObject object = new JSONObject(JSONString);
			JSONArray infoArray = object.getJSONArray("info");
			
			ArrayList<HotelInfo> hotelInfoList = new ArrayList<HotelInfo>();
			
			for(int i=0; i<infoArray.length(); i++) {
				JSONObject infoObject = infoArray.getJSONObject(i);
				HotelInfo info = new HotelInfo();
				info.id = infoObject.getString("info_id");
				info.title = infoObject.getString("title");
				info.description = infoObject.getString("description");
				info.imageUrl = infoObject.getString("image");
				hotelInfoList.add(info);
			}
			
			return hotelInfoList;
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
