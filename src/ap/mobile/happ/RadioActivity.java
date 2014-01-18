package ap.mobile.happ;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.widget.TextView;
import ap.mobile.happ.tasks.RadioIndexTask;

public class RadioActivity extends FragmentActivity {
	
	private Typeface fontNormal;
	private Typeface fontBold;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		
		fontNormal = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondLight.ttf");
        fontBold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondBold.ttf"); 
        
        TextView welcomeText = (TextView)this.findViewById(R.id.welcomeText);
        TextView loadingText = (TextView)this.findViewById(R.id.loadingText);
        
        welcomeText.setTypeface(fontNormal);
        loadingText.setTypeface(fontBold);
		
		ViewPager vPager = (ViewPager) this.findViewById(R.id.viewpager);
		RadioIndexTask radioIndexTask = new RadioIndexTask(this, vPager);
		radioIndexTask.execute("http://175.45.187.246/iptv/index.php/service/index/tv");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.radio, menu);
		return true;
	}

}
