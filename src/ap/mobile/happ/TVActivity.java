package ap.mobile.happ;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import ap.mobile.happ.tasks.TVIndexTask;

public class TVActivity extends Activity {
	
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
        TextView loadingText = (TextView)this.findViewById(R.id.textLoading);
        GridView gv = (GridView)this.findViewById(R.id.GridView1);
        
        welcomeText.setTypeface(fontNormal);
        loadingText.setTypeface(fontBold);
        
        TVIndexTask indexTask = new TVIndexTask(this, gv);
        indexTask.execute("http://localhost/iptv/index.php/service/index/tv");
	}
}
