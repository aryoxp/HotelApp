package ap.mobile.happ.base;

import java.util.Map;

public class Weather {
	public int id;
	public String main;
	public String description;
	public String city;
	public double temperature;
	
	public Map<Integer, String> weatherCode;
	
	@SuppressWarnings("unused")
	private void InitMap(){
		weatherCode.put(200, "thunderstorm with light rain");
		weatherCode.put(201, "thunderstorm with rain");
		weatherCode.put(202, "thunderstorm with heavy rain");
		weatherCode.put(210, "light thunderstorm");
		weatherCode.put(211, "thunderstorm");
		weatherCode.put(212, "heavy thunderstorm");
		weatherCode.put(221, "ragged thunderstorm");
		weatherCode.put(230, "thunderstorm with light drizzle");
		weatherCode.put(231, "thunderstorm with drizzle");
		weatherCode.put(232, "thunderstorm with heavy drizzle");
		weatherCode.put(300, "light intensity drizzle");
		weatherCode.put(301, "drizzle");
		weatherCode.put(302, "heavy intensity drizzle");
		weatherCode.put(310, "light intensity drizzle rain");
		weatherCode.put(311, "drizzle rain");
		weatherCode.put(312, "heavy intensity drizzle rain");
		weatherCode.put(313, "shower rain and drizzle");
		weatherCode.put(314, "heavy shower rain and drizzle");
		weatherCode.put(321, "shower drizzle");
		weatherCode.put(500, "light rain");
		weatherCode.put(501, "moderate rain");
		weatherCode.put(502, "heavy intensity rain");
		weatherCode.put(503, "very heavy rain");
		weatherCode.put(504, "extreme rain");
		weatherCode.put(511, "freezing rain	");
		weatherCode.put(520, "light intensity shower rain");
		weatherCode.put(521, "shower rain");
		weatherCode.put(522, "heavy intensity shower rain");
		weatherCode.put(531, "ragged shower rain");
		weatherCode.put(600, "light snow");
		weatherCode.put(601, "snow");
		weatherCode.put(602, "heavy snow");
		weatherCode.put(611, "sleet");
		weatherCode.put(612, "shower sleet");
		weatherCode.put(615, "light rain and snow");
		weatherCode.put(616, "rain and snow");
		weatherCode.put(620, "light shower snow");
		weatherCode.put(621, "shower snow");
		weatherCode.put(622, "heavy shower snow");
		weatherCode.put(701, "mist");
		weatherCode.put(711, "smoke");
		weatherCode.put(721, "haze");
		weatherCode.put(731, "Sand/Dust Whirls");
		weatherCode.put(741, "Fog");
		weatherCode.put(751, "sand");
		weatherCode.put(761, "dust");
		weatherCode.put(762, "VOLCANIC ASH");
		weatherCode.put(771, "SQUALLS");
		weatherCode.put(781, "TORNADO");
		weatherCode.put(800, "sky is clear");
		weatherCode.put(801, "few clouds");
		weatherCode.put(802, "scattered clouds");
		weatherCode.put(803, "broken clouds");
		weatherCode.put(804, "overcast clouds");
		weatherCode.put(900, "tornado");
		weatherCode.put(901, "tropical storm");
		weatherCode.put(902, "hurricane");
		weatherCode.put(903, "cold");
		weatherCode.put(904, "hot");
		weatherCode.put(905, "windy");
		weatherCode.put(906, "hail");
		weatherCode.put(950, "Setting");
		weatherCode.put(951, "Calm");
		weatherCode.put(952, "Light breeze");
		weatherCode.put(953, "Gentle Breeze");
		weatherCode.put(954, "Moderate breeze");
		weatherCode.put(955, "Fresh Breeze");
		weatherCode.put(956, "Strong breeze");
		weatherCode.put(957, "High wind, near gale");
		weatherCode.put(958, "Gale");
		weatherCode.put(959, "Severe Gale");
		weatherCode.put(960, "Storm");
		weatherCode.put(961, "Violent Storm");
		weatherCode.put(962, "Hurricane");
	}
	
	public Weather() {}
	
	public Weather(int id, String main, String description, String city, double temperature)
	{
		//this.InitMap();
		this.id = id;
		this.main = main;
		this.description = description;
		this.temperature = temperature;
		this.city = city;
	}
}
