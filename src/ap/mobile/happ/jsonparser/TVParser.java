package ap.mobile.happ.jsonparser;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ap.mobile.happ.base.TVMedia;

public class TVParser {
	public static ArrayList<TVMedia> ParseIndex(String JSONString){
		ArrayList<TVMedia> list = new ArrayList<TVMedia>();
		try {
			JSONObject object = new JSONObject(JSONString);
			JSONArray tvs = object.getJSONArray("media");
			for(int i=0; i<tvs.length(); i++){
				JSONObject tvObject = tvs.getJSONObject(i);
				//String id = tvObject.getString("id");
				String name = tvObject.getString("name");
				String description = "";//tvObject.getString("description");
				String file = "";//tvObject.getString("file");
				String stream = tvObject.getString("stream");
				String logo = tvObject.getString("logo");
				if(logo == "null") logo = null;
				if(stream == "null") stream = null;
				if(file == "null") file = null;
					
				TVMedia tv = new TVMedia(name, description, file, stream, logo);
				//tv.id = id;
				list.add(tv);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
