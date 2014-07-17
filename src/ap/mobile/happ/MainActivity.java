package ap.mobile.happ;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import ap.mobile.happ.views.Sidebar;
import ap.mobile.happ.views.SidebarButton;

public class MainActivity extends FragmentActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		Sidebar sidebar = (Sidebar) this.findViewById(R.id.mainSidebar);
		
		SidebarButton buttonHome = new SidebarButton(this, "home", R.drawable.info);
		sidebar.addButton(buttonHome);
		sidebar.addButton("home", R.drawable.monitor);
		sidebar.addButton("movie", R.drawable.film_reel);

		BrowseTVFragment fragment = BrowseTVFragment.getInstance(this);
		getSupportFragmentManager().beginTransaction().replace(R.id.mainBrowseContainer, fragment)
			.commit();
		
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
	}
}
