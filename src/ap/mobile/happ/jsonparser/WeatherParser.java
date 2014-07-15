package ap.mobile.happ.jsonparser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ap.mobile.happ.base.Weather;
/* sample data:
 * 
 * 
{ "base" : "cmc stations",
  "clouds" : { "all" : 88 },
  "cod" : 200,
  "coord" : { "lat" : -7.9800000000000004,
      "lon" : 112.63
    },
  "dt" : 1389403141,
  "id" : 1636722,
  "main" : { "grnd_level" : 960.45000000000005,
      "humidity" : 98,
      "pressure" : 960.45000000000005,
      "sea_level" : 1024.9000000000001,
      "temp" : 22.783999999999999,
      "temp_max" : 22.783999999999999,
      "temp_min" : 22.783999999999999
    },
  "name" : "Malang",
  "rain" : { "3h" : 0.5 },
  "sys" : { "country" : "ID",
      "message" : 0.013100000000000001,
      "sunrise" : 1389392455,
      "sunset" : 1389437640
    },
  "weather" : [ { "description" : "light rain",
        "icon" : "10d",
        "id" : 500,
        "main" : "Rain"
      } ],
  "wind" : { "deg" : 58.000700000000002,
      "speed" : 1.22
    }
}

 */
public class WeatherParser {
	public static Weather Parse(String JSONString){
		
		try {
			JSONObject object = new JSONObject(JSONString);
			
			JSONObject main = object.getJSONObject("main");
			JSONArray weatherArray = object.getJSONArray("weather");
			JSONObject weather = (JSONObject)weatherArray.get(0);
			
			Weather w = new Weather();
			w.temperature = main.getDouble("temp");
			w.city = object.getString("name");
			w.main = weather.getString("main");
			w.description = weather.getString("description");
			w.id = weather.getInt("id");

			return w;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
