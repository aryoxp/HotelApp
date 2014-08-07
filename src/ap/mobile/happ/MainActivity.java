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
	private SidebarButton buttonSetting;
	private SidebarButton buttonLanguage;
	
	private Sidebar sidebar;
	private TextView pageTitleText;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		this.pageTitleText = (TextView) this.findViewById(R.id.mainPageTitle);
		
		this.sidebar = (Sidebar) this.findViewById(R.id.mainSidebar);
		
		this.buttonHome = new SidebarButton(this, "mainButtonHome", "Home", R.drawable.bt_main_nav_home);
		this.buttonTV = new SidebarButton(this, "mainButtonTV", "TV", R.drawable.bt_main_nav_tv);
        this.buttonRadio = new SidebarButton(this, "mainButtonRadio", "Radio", R.drawable.bt_main_nav_radio);
        this.buttonVod = new SidebarButton(this, "mainButtonVod", "Video on Demand", R.drawable.bt_main_nav_video);
        this.buttonInternet = new SidebarButton(this, "mainButtonInternet", "Internet", R.drawable.bt_main_nav_internet);
        this.buttonInfo = new SidebarButton(this, "mainButtonInfo", "Information", R.drawable.bt_main_nav_info);
        this.buttonSetting = new SidebarButton(this, "mainButtonSetting", "Setting", R.drawable.bt_main_nav_setting);
        this.buttonLanguage = new SidebarButton(this, "mainButtonLanguage", "Language", R.drawable.bt_main_nav_language);
                
        this.sidebar.addButton(this.buttonHome);
        this.sidebar.addButton(this.buttonTV);
        this.sidebar.addButton(this.buttonRadio);
        this.sidebar.addButton(this.buttonVod);
        this.sidebar.addButton(this.buttonInternet);
        this.sidebar.addButton(this.buttonInfo);
        this.sidebar.addButton(this.buttonSetting);
        this.sidebar.addButton(this.buttonLanguage);
        
        this.sidebar.setButtonsClickListener(this);
        
        Intent intent = this.getIntent();
        String menu = intent.getStringExtra("menu");
        if(menu != null) {
        	if(menu.equals(this.buttonRadio.getButtonId())) {
        		this.changePage(STBPage.Radio);
        		return;
        	}
        }
        this.changePage(STBPage.TV);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		STBPageFragment fragment = (STBPageFragment) this.getSupportFragmentManager()
				.findFragmentById(R.id.mainBrowseContainer);
		switch(fragment.getPageId()) {
		case TV:
			this.buttonTV.requestFocus();
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
			if(tag.equals("mainButtonTV")) {
				this.changePage(STBPage.TV);
				return;
			}
			if(tag.equals("mainButtonRadio")) {
				this.changePage(STBPage.Radio);
				return;
			}
		}
		
		Toast.makeText(getApplicationContext(), "Not implemented.", Toast.LENGTH_LONG).show();
	}

	@Override
	public void setPageTitle(String pageTitle) {
		this.pageTitleText.setText(pageTitle);
	}

	@Override
	public void changePage(STBPage page) {
		STBPageFragment fragment;
		switch(page) {
			case Radio:
				fragment = BrowseRadioFragment.getInstance(this);
				break;
			case TV:
			default:
				fragment = BrowseTVFragment.getInstance(this);
				break;
			
		}
		fragment.setMainActivityInterface(this);
		getSupportFragmentManager().beginTransaction().replace(R.id.mainBrowseContainer, fragment)
		.commit();
	}
}
