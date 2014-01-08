package ap.mobile.happ;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class TVActivity extends Activity {
	
	private Typeface fontNormal;
	private Typeface fontBold;
	private Typeface fontItalic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_tv);
		
		fontNormal = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondLight.ttf");
        fontBold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondBold.ttf"); 
        fontItalic = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondLightItalic.ttf"); 
        
        TextView welcomeText = (TextView)this.findViewById(R.id.welcomeText);
        welcomeText.setTypeface(fontNormal);
        
	}

}
