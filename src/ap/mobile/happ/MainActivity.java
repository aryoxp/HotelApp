package ap.mobile.happ;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import ap.mobile.happ.base.STBPage;
import ap.mobile.happ.base.STBPageFragment;
import ap.mobile.happ.interfaces.MainActivityInterface;
import ap.mobile.happ.views.Sidebar;
import ap.mobile.happ.views.SidebarButton;

public class MainActivity extends FragmentActivity implements OnClickListener, MainActivityInterface {
	
	private SidebarButton buttonHome;
	private SidebarButton buttonTV;
	private SidebarButton buttonRadio;
	private SidebarButton buttonVod;
	private SidebarButton buttonInternet;
	private SidebarButton buttonInfo;
	private Sidebar sidebar;
	private TextView pageTitleText;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		this.pageTitleText = (TextView) this.findViewById(R.id.mainPageTitle);
		
		this.sidebar = (Sidebar) this.findViewById(R.id.mainSidebar);
		
		this.buttonHome = new SidebarButton(this, "mainButtonHome", getString(R.string.home), R.drawable.bt_main_nav_home);
		this.buttonTV = new SidebarButton(this, "mainButtonTV", getString(R.string.tv), R.drawable.bt_main_nav_tv);
        this.buttonVod = new SidebarButton(this, "mainButtonVod", getString(R.string.vod), R.drawable.bt_main_nav_video);
        this.buttonInternet = new SidebarButton(this, "mainButtonInternet", getString(R.string.internet), R.drawable.bt_main_nav_internet);
        this.buttonInfo = new SidebarButton(this, "mainButtonInfo", getString(R.string.info_hotel), R.drawable.bt_main_nav_info);
                
        this.sidebar.addButton(this.buttonHome);
        this.sidebar.addButton(this.buttonTV);
        this.sidebar.addButton(this.buttonVod);
        this.sidebar.addButton(this.buttonInternet);
        this.sidebar.addButton(this.buttonInfo);
        
        this.sidebar.setButtonsClickListener(this);
        
        Intent intent = this.getIntent();
        String menu = intent.getStringExtra("menu");
        if(menu != null) {
        	if(menu.equals(this.buttonInfo.getButtonId())) {
        		this.changePage(STBPage.Info);
        		return;
        	}
        	if(menu.equals(this.buttonVod.getButtonId())) {
        		this.changePage(STBPage.VideoOnDemand);
        		return;
        	}
        }
        
        // default is TV
        this.changePage(STBPage.TV);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		View decorView = getWindow().getDecorView();
		// Hide both the navigation bar and the status bar.
		// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
		// a general rule, you should design your app to hide the status bar whenever you
		// hide the navigation bar.
		int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		              | View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
		STBPageFragment fragment = (STBPageFragment) this.getSupportFragmentManager()
				.findFragmentById(R.id.mainBrowseContainer);
		switch(fragment.getPageId()) {
		case TV:
			this.buttonTV.requestFocus();
			break;
		case VideoOnDemand:
			this.buttonVod.requestFocus();
			break;
		case Radio:
			this.buttonRadio.requestFocus();
			break;
		case Info:
			this.buttonInfo.requestFocus();
			break;
		default:
			break;	
		}
	}

	@Override
	public void onClick(View v) {
		
		String tag = (String) v.getTag();
		
		if(tag != null) {
			
			if(tag.equals("mainButtonHome")) {
				this.finish();
				return;
			}
			if(tag.equals("mainButtonVod")) {
				this.changePage(STBPage.VideoOnDemand);
				return;
			}
			if(tag.equals("mainButtonTV")) {
				this.changePage(STBPage.TV);
				return;
			}
			if(tag.equals("mainButtonRadio")) {
				this.changePage(STBPage.Radio);
				return;
			}
			if(tag.equals("mainButtonInfo")) {
				this.changePage(STBPage.Info);
				return;
			}
			
		}
		
		Toast.makeText(getApplicationContext(), getString(R.string.not_implemented), Toast.LENGTH_LONG).show();
	}

	@Override
	public void setPageTitle(String pageTitle) {
		this.pageTitleText.setText(pageTitle);
	}

	@Override
	public void changePage(STBPage page) {
		STBPageFragment fragment;
		switch(page) {
		case Info:
			fragment = HotelInfoFragment.getInstance(this);
			break;
		case Radio:
			fragment = BrowseRadioFragment.getInstance(this);
			break;
		case TV:
			fragment = BrowseTVFragment.getInstance(this);
			break;
		case VideoOnDemand:
			fragment = BrowseVodFragment.getInstance(this);
			break;
		default:
			fragment = BrowseTVFragment.getInstance(this);
			break;
		}
		fragment.setMainActivityInterface(this);
		getSupportFragmentManager().beginTransaction().replace(R.id.mainBrowseContainer, fragment)
		.commit();
	}
}
