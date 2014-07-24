package ap.mobile.happ;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
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
        
        this.buttonTV.setOnClickListener(this);
        this.buttonHome.setOnClickListener(this);
        
        this.sidebar.addButton(this.buttonHome);
        this.sidebar.addButton(this.buttonTV);
        this.sidebar.addButton(this.buttonRadio);
        this.sidebar.addButton(this.buttonVod);
        this.sidebar.addButton(this.buttonInternet);
        this.sidebar.addButton(this.buttonInfo);
        this.sidebar.addButton(this.buttonSetting);
        this.sidebar.addButton(this.buttonLanguage);
        
        this.changePage(STBPage.TV);
        
	}

	@Override
	public void onClick(View v) {
		
		String tag = (String) v.getTag();
		
		if(tag != null && tag.equals("mainButtonHome"))
			this.finish();
		
		//Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
	}

	@Override
	public void setPageTitle(String pageTitle) {
		this.pageTitleText.setText(pageTitle);
	}

	@Override
	public void changePage(STBPage page) {
		STBPageFragment fragment;
		switch(page) {
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
