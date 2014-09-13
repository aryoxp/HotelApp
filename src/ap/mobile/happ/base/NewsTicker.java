package ap.mobile.happ.base;

import java.util.ArrayList;

public class NewsTicker {
	public static ArrayList<News> getNews() {
		ArrayList<News> news = new ArrayList<News>();
		news.add(new News("Lorem ipsum dolor sit amet..."));
		news.add(new News("Consectetir sit amet...\nyang ini agak panjang sedikit dan lebih panjang lagi terus bagaimana?"));
		return news;
	}
}
