package ap.mobile.happ;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import ap.mobile.happ.tasks.TVIndexTask;

public class TVActivity extends FragmentActivity {
	
	private Typeface fontNormal;
	private Typeface fontBold;
	@SuppressWarnings("unused")
	private Typeface fontItalic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tv);
		
		fontNormal = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondLight.ttf");
        fontBold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondBold.ttf"); 
        fontItalic = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondLightItalic.ttf"); 
        
        TextView welcomeText = (TextView)this.findViewById(R.id.welcomeText);
        TextView loadingText = (TextView)this.findViewById(R.id.loadingText);
        
        welcomeText.setTypeface(fontNormal);
        loadingText.setTypeface(fontBold);
        
        ViewPager vPager = (ViewPager) this.findViewById(R.id.viewpager);
        TVIndexTask indexTask = new TVIndexTask(this, vPager);
        //GridView gv = (GridView)this.findViewById(R.id.GridView1);
        //TVIndexTask indexTask = new TVIndexTask(this, gv);
        indexTask.execute("http://175.45.187.246/iptv/index.php/service/index/tv");
        
        
	}
}
