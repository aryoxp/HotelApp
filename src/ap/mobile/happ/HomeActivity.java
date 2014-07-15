package ap.mobile.happ;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import ap.mobile.happ.tasks.WeatherTask;

public class HomeActivity extends Activity {

	private Context context;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.activity_home);
        TextView welcomeText = (TextView)this.findViewById(R.id.welcomeText);
        TextView selectedMenuText = (TextView) this.findViewById(R.id.selectedMenuText);
        
        this.context = this;
        
        View menuTV = (View) this.findViewById(R.id.menuTv);
        menuTV.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, TVActivity.class);
				context.startActivity(i);
			}
		});
        
        View menuRadio = (View) this.findViewById(R.id.menuRadio);
        menuRadio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, RadioActivity.class);
				context.startActivity(i);
			}
		});
        		
        Typeface helveticaCondensed = Typeface.createFromAsset(getAssets(), "fonts/Helvetica-Condensed.ttf");
        
        welcomeText.setTypeface(helveticaCondensed);
        selectedMenuText.setTypeface(helveticaCondensed);
        
        ImageView iconView = (ImageView) this.findViewById(R.id.weatherIcon);
        //TextView cityView = (TextView) this.findViewById(R.id.cityName);
        TextView weatherView = (TextView) this.findViewById(R.id.weatherText);
        //TextView weatherDescriptionText = (TextView) this.findViewById(R.id.weatherDescription);        
        //cityView.setTypeface(helveticaCondensed);
        
        WeatherTask weatherTask = new WeatherTask(this, iconView, null, weatherView, null);
        weatherTask.execute("http://api.openweathermap.org/data/2.5/weather?q=Malang,id&units=metric");
    }
}
