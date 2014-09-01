package ap.mobile.happ;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import ap.mobile.happ.base.AppConfig;
import ap.mobile.happ.interfaces.DefaultContentInterface;
import ap.mobile.happ.tasks.DefaultTask;
import ap.mobile.happ.tasks.WeatherTask;
import ap.mobile.happ.views.MainNavigation;
import ap.mobile.happ.views.MainNavigationButton;

public class HomeActivity extends Activity implements OnClickListener, DefaultContentInterface {

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
	
	private boolean ready = false;
	
	
	private Runnable clockRunnable = new Runnable() {
		
		private SimpleDateFormat clockSdf = new SimpleDateFormat("h:mm a", Locale.getDefault());
		
		@Override
		public void run() {
			Calendar calendar = Calendar.getInstance(Locale.getDefault());
			clockText.setText(clockSdf.format(calendar.getTime()));
			clockHandler.postDelayed(this, 500);
		}
	};
	private TextView statusText;
	private ProgressBar mainProgress;
	private View mainScreenCover;
	private View rootLayout;
	private TextView hotelNameText;
	private String cityName;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.activity_home);
        this.clockText = (TextView) this.findViewById(R.id.mainClockText);
        this.statusText = (TextView) this.findViewById(R.id.mainStatusText);
        this.hotelNameText = (TextView) this.findViewById(R.id.hotelNameText);
        this.mainProgress = (ProgressBar) this.findViewById(R.id.mainProgress);
        this.welcomeText = (TextView) this.findViewById(R.id.homeWelcomeText);
        this.rootLayout = this.findViewById(R.id.rootLayout);
        this.rootLayout.setVisibility(View.VISIBLE);
        this.mainScreenCover = this.findViewById(R.id.mainScreenCover);
        this.welcomeText.setText("Welcome to UB Hotel");
        
        this.mainNavigation = (MainNavigation) this.findViewById(R.id.mainNavigation);
        
        this.buttonTV = new MainNavigationButton(this, "mainButtonTV", "TV", R.drawable.bt_main_nav_tv);
        this.buttonRadio = new MainNavigationButton(this, "mainButtonRadio", "Radio", R.drawable.bt_main_nav_radio);
        this.buttonVod = new MainNavigationButton(this, "mainButtonVod", "Video on Demand", R.drawable.bt_main_nav_video);
        this.buttonInternet = new MainNavigationButton(this, "mainButtonInternet", "Internet", R.drawable.bt_main_nav_internet);
        this.buttonInfo = new MainNavigationButton(this, "mainButtonInfo", "Hotel Information", R.drawable.bt_main_nav_info);
        this.buttonSetting = new MainNavigationButton(this, "mainButtonSetting", "Setting", R.drawable.bt_main_nav_setting);
        this.buttonLanguage = new MainNavigationButton(this, "mainButtonLanguage", "Language", R.drawable.bt_main_nav_language);
                
        this.mainNavigation.addButton(this.buttonTV);
        this.mainNavigation.addButton(this.buttonRadio);
        this.mainNavigation.addButton(this.buttonVod);
        this.mainNavigation.addButton(this.buttonInternet);
        this.mainNavigation.addButton(this.buttonInfo);
        this.mainNavigation.addButton(this.buttonSetting);
        this.mainNavigation.addButton(this.buttonLanguage);
        
        this.mainNavigation.setButtonsClickListener(this);
        
        /*
        Typeface helveticaCondensed = Typeface.createFromAsset(getAssets(), "fonts/Helvetica-Condensed.ttf");
        welcomeText.setTypeface(helveticaCondensed);
        selectedMenuText.setTypeface(helveticaCondensed);
        */
        
        DefaultTask defaultTask = new DefaultTask(this, this);
        defaultTask.execute(AppConfig.baseUrl + "json/default");
        
        
        
        
        
        this.clockHandler = new Handler();
    }

	private void loadWeatherInformation() {
		if(this.cityName == null) 
			this.cityName = "Jakarta";
		
		ImageView iconView = (ImageView) this.findViewById(R.id.weatherIcon);
        TextView weatherView = (TextView) this.findViewById(R.id.weatherText);
        TextView cityTextView = (TextView) this.findViewById(R.id.weatherCityText);
        WeatherTask weatherTask = new WeatherTask(this, iconView, cityTextView, weatherView, null);
        weatherTask.execute("http://api.openweathermap.org/data/2.5/weather?q="+this.cityName+",id&units=metric");
	}
    
    @Override
    protected void onResume() {
    	View decorView = getWindow().getDecorView();
    	// Hide both the navigation bar and the status bar.
    	// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
    	// a general rule, you should design your app to hide the status bar whenever you
    	// hide the navigation bar.
    	int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    	              | View.SYSTEM_UI_FLAG_FULLSCREEN;
    	decorView.setSystemUiVisibility(uiOptions);
    	
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
		if(!this.ready)
			return;
		//Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();
		String tag = (String) v.getTag();
		if(tag != null) {
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("menu", tag);
			this.startActivity(intent);
			return;
		} else {
			Toast.makeText(this, "Error: Button tag is NULL", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onDefaultContentLoadingStarted() {
		this.mainProgress.setVisibility(View.VISIBLE);
		this.mainScreenCover.setVisibility(View.VISIBLE);
	}

	@Override
	public void onDefaultContentProgress(String progressText) {
		this.statusText.setText(progressText);
	}

	@Override
	public void onDefaultContentLoaded(Bitmap background, String hotelName, String welcomeText,
			String cityName, String language) {
		this.statusText.setText("Complete!");
		this.rootLayout.setBackground(new BitmapDrawable(getResources(),background));
		this.hotelNameText.setText(hotelName);
		this.cityName = cityName;
		this.welcomeText.setText(Html.fromHtml(welcomeText));
		
		loadWeatherInformation();
		
		float height = (float) -this.mainScreenCover.getHeight();
		TranslateAnimation ta = new TranslateAnimation(0, 0, 0, height);
		ta.setDuration(1000);
		this.mainScreenCover.startAnimation(ta);
		this.mainScreenCover.setVisibility(View.GONE);
		this.ready = true;
	}

	@Override
	public void onDefaultContentError(String errorText) {
		this.statusText.setText("Unable to process default content data");
		this.mainProgress.setVisibility(View.GONE);
	}
}
