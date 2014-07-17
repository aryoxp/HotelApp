package ap.mobile.happ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ap.mobile.happ.tasks.WeatherTask;
import ap.mobile.happ.views.MainNavigation;
import ap.mobile.happ.views.MainNavigationButton;

public class HomeActivity extends Activity implements OnClickListener {

	MainNavigation mainNavigation;
	MainNavigationButton buttonTV;
    MainNavigationButton buttonRadio;
    MainNavigationButton buttonVod;
    MainNavigationButton buttonInfo;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.activity_home);
        TextView welcomeText = (TextView)this.findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome to UB Hotel");
        
        this.mainNavigation = (MainNavigation) this.findViewById(R.id.mainNavigation);
        
        this.buttonTV = new MainNavigationButton(this, "mainButtonTV", "TV", R.drawable.monitor);
        this.buttonRadio = new MainNavigationButton(this, "mainButtonRadio", "Radio", R.drawable.antenna2);
        this.buttonVod = new MainNavigationButton(this, "mainButtonVod", "Video on Demand", R.drawable.film_reel);
        this.buttonInfo = new MainNavigationButton(this, "mainButtonInfo", "Information", R.drawable.info);
        
        this.buttonTV.setOnClickListener(this);
        
        this.mainNavigation.addButton(this.buttonTV);
        this.mainNavigation.addButton(this.buttonRadio);
        this.mainNavigation.addButton(this.buttonVod);
        this.mainNavigation.addButton(this.buttonInfo);
        
        
        /*
        Typeface helveticaCondensed = Typeface.createFromAsset(getAssets(), "fonts/Helvetica-Condensed.ttf");
        welcomeText.setTypeface(helveticaCondensed);
        selectedMenuText.setTypeface(helveticaCondensed);
        */
        
        ImageView iconView = (ImageView) this.findViewById(R.id.weatherIcon);
        TextView weatherView = (TextView) this.findViewById(R.id.weatherText);
       
        WeatherTask weatherTask = new WeatherTask(this, iconView, null, weatherView, null);
        weatherTask.execute("http://api.openweathermap.org/data/2.5/weather?q=Malang,id&units=metric");
    }
    
    @Override
    protected void onResume() {
    	this.mainNavigation.selectButton(0);
    	super.onResume();
    }

	@Override
	public void onClick(View v) {
		//Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();
		String tag = (String) v.getTag();
		if(tag != null) { 
			if(tag.equals(this.buttonTV.getButtonId())) {
				Intent intent = new Intent(this, MainActivity.class);
				this.startActivity(intent);
			}
		} else {
			Toast.makeText(this, "Error: Button tag is NULL", Toast.LENGTH_SHORT).show();
		}
	}
}
