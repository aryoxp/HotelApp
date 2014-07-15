package ap.mobile.happ;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TVActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		BrowseTVFragment fragment = BrowseTVFragment.getInstance(getApplicationContext());
		getSupportFragmentManager().beginTransaction().replace(R.id.mainBrowseContainer, fragment)
			.commit();
		
	}
}
