package ap.mobile.happ.jsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ap.mobile.happ.base.DefaultContent;

public class DefaultContentParser {
	public static DefaultContent Parse(String JSONString){
		try {
			JSONObject object = (new JSONArray(JSONString)).getJSONObject(0);
			String id = object.getString("id");
			String hotelName = object.getString("hotel_name");
			String welcomeText = object.getString("welcome_txt");
			String city = object.getString("city");
			String wallpaper = object.getString("wallpaper");
			String language = object.getString("language");
			
			DefaultContent content = new DefaultContent();
			content.id = id;
			content.backgroundUrl = wallpaper;
			content.cityName = city;
			content.hotelName = hotelName;
			content.welcomeText = welcomeText;
			content.language = language;
			
			return content;
			
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
}
