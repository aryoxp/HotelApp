package ap.mobile.happ.jsonparser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ap.mobile.happ.base.VodMedia;

public class VodParser {
	public static ArrayList<VodMedia> ParseIndex(String JSONString){
		ArrayList<VodMedia> list = new ArrayList<VodMedia>();
		try {
			JSONObject object = new JSONObject(JSONString);
			JSONArray vods = object.getJSONArray("media");
			for(int i=0; i<vods.length(); i++){
				JSONObject vodObject = vods.getJSONObject(i);
				String id = vodObject.getString("id");
				String name = vodObject.getString("name");
				String description = vodObject.getString("description");
				String stream = vodObject.getString("stream");
				String logo = vodObject.getString("logo");
				String icon = vodObject.getString("icon");
				if(logo == "null") logo = null;
				if(stream == "null") stream = null;
				if(icon == "null") icon = null;
					
				VodMedia vod = new VodMedia(id, name, description, stream, logo, icon);
				list.add(vod);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

}
