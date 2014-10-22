package ap.mobile.happ;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import ap.mobile.happ.base.AppConfig;
import ap.mobile.happ.base.RunningText;
import ap.mobile.happ.interfaces.DefaultContentInterface;
import ap.mobile.happ.interfaces.LanguageDialogInterface;
import ap.mobile.happ.interfaces.RunningTextInterface;
import ap.mobile.happ.tasks.DefaultTask;
import ap.mobile.happ.tasks.RunningTextTask;
import ap.mobile.happ.tasks.WeatherTask;
import ap.mobile.happ.views.MainNavigation;
import ap.mobile.happ.views.MainNavigationButton;

public class HomeActivity extends FragmentActivity 
	implements OnClickListener, DefaultContentInterface, LanguageDialogInterface,
	RunningTextInterface {

	private MainNavigation mainNavigation;
	private MainNavigationButton buttonTV;
    private MainNavigationButton buttonVod;
    private MainNavigationButton buttonInfo;
	private MainNavigationButton buttonInternet;
	private MainNavigationButton buttonLanguage;
	private MainNavigationButton buttonSetting;
	
	private Handler clockHandler;
	private TextView clockText;
	private TextView welcomeText;
	
	private boolean ready = false;
	private Handler tickerHandler = new Handler();
	private ArrayList<RunningText> runningTexts = new ArrayList<RunningText>();
	
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
	private TextView newsTickerText;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.activity_home);
        this.clockText = (TextView) this.findViewById(R.id.mainClockText);
        this.statusText = (TextView) this.findViewById(R.id.mainStatusText);
        this.hotelNameText = (TextView) this.findViewById(R.id.hotelNameText);
        this.mainProgress = (ProgressBar) this.findViewById(R.id.mainProgress);
        this.welcomeText = (TextView) this.findViewById(R.id.homeWelcomeText);
        this.newsTickerText = (TextView) this.findViewById(R.id.homeNewsTicker);
        this.rootLayout = this.findViewById(R.id.rootLayout);
        this.rootLayout.setVisibility(View.VISIBLE);
        this.mainScreenCover = this.findViewById(R.id.mainScreenCover);
        
        this.mainNavigation = (MainNavigation) this.findViewById(R.id.mainNavigation);
        
        this.buttonTV = new MainNavigationButton(this, "mainButtonTV", getString(R.string.tv), R.drawable.bt_main_nav_tv);
        this.buttonVod = new MainNavigationButton(this, "mainButtonVod", getString(R.string.vod), R.drawable.bt_main_nav_video);
        this.buttonInternet = new MainNavigationButton(this, "mainButtonInternet", getString(R.string.internet), R.drawable.bt_main_nav_internet);
        this.buttonInfo = new MainNavigationButton(this, "mainButtonInfo", getString(R.string.info_hotel), R.drawable.bt_main_nav_info);
        this.buttonSetting = new MainNavigationButton(this, "mainButtonSetting", getString(R.string.setting), R.drawable.bt_main_nav_setting);
        this.buttonLanguage = new MainNavigationButton(this, "mainButtonLanguage", getString(R.string.language), R.drawable.bt_main_nav_language);
                
        this.mainNavigation.addButton(this.buttonTV);
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
        
        RunningTextTask runningTextTask = new RunningTextTask(this);
        runningTextTask.execute("http://ubcreative.net/apps/hotel/json/runningtext");
        
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
    	if(this.runningTexts !=null && this.runningTexts.size() > 0)
    		this.tickerHandler.post(this.newsTickerRunnable);
    	else {
    		new RunningTextTask(this).execute("http://ubcreative.net/apps/hotel/json/runningtext");
    	}
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	this.clockHandler.removeCallbacks(this.clockRunnable);
    	this.tickerHandler.removeCallbacks(this.newsTickerRunnable);
    	super.onPause();
    }

	@Override
	public void onClick(View v) {
		if(!this.ready)
			return;
		//Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show();
		String tag = (String) v.getTag();
		if(tag != null) {
			if(tag.equals("mainButtonLanguage")) {
				LanguageDialogFragment languageDialog = new LanguageDialogFragment();
				languageDialog.setDialogCallback(this);
				languageDialog.show(getSupportFragmentManager(), "languageDialog");
			} else {
				Intent intent = new Intent(this, MainActivity.class);
				intent.putExtra("menu", tag);
				this.startActivity(intent);
			}
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
		this.statusText.setText(getString(R.string.complete));
		this.rootLayout.setBackground(new BitmapDrawable(getResources(),background));
		this.hotelNameText.setText(getResources().getString(R.string.welcome_to, hotelName));
		this.cityName = cityName;
		this.welcomeText.setText(Html.fromHtml(welcomeText));
		
		loadWeatherInformation();
		
		float height = (float) -this.mainScreenCover.getHeight();
		TranslateAnimation ta = new TranslateAnimation(0, 0, 0, height);
		ta.setDuration(500);
		this.mainScreenCover.startAnimation(ta);
		this.mainScreenCover.setVisibility(View.GONE);
		this.ready = true;
	}

	@Override
	public void onDefaultContentError(String errorText) {
		this.statusText.setText(getString(R.string.unable_to_process));
		this.mainProgress.setVisibility(View.GONE);
	}
	
	private Runnable newsTickerRunnable = new Runnable() {
		
		public int index = 0;
		
		@Override
		public void run() {
			
			if(newsTickerText != null) {
				newsTickerText.animate()
				.alpha(0)
				.setDuration(1000)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						super.onAnimationEnd(animation);
						newsTickerText.setText(runningTexts.get(index).headline);
						newsTickerText.animate()
						.alpha(1)
						.setDuration(1000)
						.setListener(new AnimatorListenerAdapter() {
							public void onAnimationEnd(Animator animation) {
								if(index < runningTexts.size()-1) index++;
								else index = 0;
								tickerHandler.postDelayed(newsTickerRunnable, 5000);
							};
						});
					}
				});
				
			}
			
		}
	};
	
	@Override
	public void onDialogDisplayed() {
		View decorView = getWindow().getDecorView();
    	int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    	              | View.SYSTEM_UI_FLAG_FULLSCREEN;
    	decorView.setSystemUiVisibility(uiOptions);
	}
	
	@Override
	public void onLanguageSelected(String language) {
		Toast.makeText(this, "Selected language: " + language, Toast.LENGTH_SHORT).show();
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		String lang = prefs.getString("locale_override", "in");
		if(lang.equals(language)) return;
		
		prefs.edit().putString("locale_override", language).commit();
		HotelApplication.updateLanguage(getApplicationContext(), language);
		if (Build.VERSION.SDK_INT >= 11) {
		    recreate();
		} else {
		    Intent intent = getIntent();
		    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		    finish();
		    overridePendingTransition(0, 0);
		    startActivity(intent);
		    overridePendingTransition(0, 0);
		}
	}

	@Override
	public void onRunningTextLoaded(ArrayList<RunningText> result) {
		this.runningTexts = result;
		this.tickerHandler.post(this.newsTickerRunnable);
	}

	@Override
	public void onPreLoading() {
		this.newsTickerText.setText(getString(R.string.loading));
	}

	@Override
	public void onRunningTextError(String error) {
		this.newsTickerText.setText("--");
		Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
	}
}
