package ap.mobile.happ;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import ap.mobile.happ.tasks.WeatherTask;
import ap.mobile.happ.views.MainNavigation;
import ap.mobile.happ.views.MainNavigationButton;

public class HomeActivity extends Activity implements OnClickListener {

	private MainNavigation mainNavigation;
	private MainNavigationButton buttonTV;
	private MainNavigationButton buttonRadio;
    private MainNavigationButton buttonVod;
    private MainNavigationButton buttonInfo;
	private MainNavigationButton buttonInternet;
	private MainNavigationButton buttonLanguage;
	private MainNavigationButton buttonSetting;
	
	private Handler clockHandler;
	private TextView clockText;
	private TextView welcomeText;
	
	
	private Runnable clockRunnable = new Runnable() {
		
		private SimpleDateFormat clockSdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
		
		@Override
		public void run() {
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			clockText.setText(clockSdf.format(calendar.getTime()));
			clockHandler.postDelayed(this, 500);
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.activity_home);
        this.welcomeText = (TextView)this.findViewById(R.id.welcomeText);
        this.clockText = (TextView) this.findViewById(R.id.mainClockText);
        
        this.welcomeText.setText("Welcome to UB Hotel");
        
        this.mainNavigation = (MainNavigation) this.findViewById(R.id.mainNavigation);
        
        this.buttonTV = new MainNavigationButton(this, "mainButtonTV", "TV", R.drawable.bt_main_nav_tv);
        this.buttonRadio = new MainNavigationButton(this, "mainButtonRadio", "Radio", R.drawable.bt_main_nav_radio);
        this.buttonVod = new MainNavigationButton(this, "mainButtonVod", "Video on Demand", R.drawable.bt_main_nav_video);
        this.buttonInternet = new MainNavigationButton(this, "mainButtonInternet", "Internet", R.drawable.bt_main_nav_internet);
        this.buttonInfo = new MainNavigationButton(this, "mainButtonInfo", "Information", R.drawable.bt_main_nav_info);
        this.buttonSetting = new MainNavigationButton(this, "mainButtonSetting", "Setting", R.drawable.bt_main_nav_setting);
        this.buttonLanguage = new MainNavigationButton(this, "mainButtonLanguage", "Language", R.drawable.bt_main_nav_language);
        
        this.buttonTV.setOnClickListener(this);
        
        this.mainNavigation.addButton(this.buttonTV);
        this.mainNavigation.addButton(this.buttonRadio);
        this.mainNavigation.addButton(this.buttonVod);
        this.mainNavigation.addButton(this.buttonInternet);
        this.mainNavigation.addButton(this.buttonInfo);
        this.mainNavigation.addButton(this.buttonSetting);
        this.mainNavigation.addButton(this.buttonLanguage);
        
        
        /*
        Typeface helveticaCondensed = Typeface.createFromAsset(getAssets(), "fonts/Helvetica-Condensed.ttf");
        welcomeText.setTypeface(helveticaCondensed);
        selectedMenuText.setTypeface(helveticaCondensed);
        */
        
        ImageView iconView = (ImageView) this.findViewById(R.id.weatherIcon);
        TextView weatherView = (TextView) this.findViewById(R.id.weatherText);
       
        WeatherTask weatherTask = new WeatherTask(this, iconView, null, weatherView, null);
        weatherTask.execute("http://api.openweathermap.org/data/2.5/weather?q=Malang,id&units=metric");
        
        this.clockHandler = new Handler();
    }
    
    @Override
    protected void onResume() {
    	this.mainNavigation.selectButton(0);
    	this.clockHandler.post(this.clockRunnable);
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	this.clockHandler.removeCallbacks(this.clockRunnable);
    	super.onPause();
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
