package ap.mobile.happ;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class TVActivity extends FragmentActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_main);
		
		View mainHomeButton = this.findViewById(R.id.mainButtonHome);
		mainHomeButton.setFocusable(true);
		mainHomeButton.requestFocus();
		mainHomeButton.setOnClickListener(this);

		BrowseTVFragment fragment = BrowseTVFragment.getInstance(this);
		getSupportFragmentManager().beginTransaction().replace(R.id.mainBrowseContainer, fragment)
			.commit();
		
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
	}
}
