package ap.mobile.happ;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends Activity {

	private Typeface fontNormal;
	private Typeface fontBold;
	@SuppressWarnings("unused")
	private Typeface fontItalic;
	
	private Context context;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);   
        setContentView(R.layout.activity_home);
        TextView welcomeText = (TextView)this.findViewById(R.id.welcomeText);
        TextView selectedMenuText = (TextView) this.findViewById(R.id.selectedMenuText);
        
        this.context = this;
        
        View menuTV = (View) this.findViewById(R.id.menuTv);
        menuTV.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context, TVActivity.class);
				context.startActivity(i);
			}
		});
        
        fontNormal = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondLight.ttf");
        fontBold = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondBold.ttf"); 
        fontItalic = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-CondLightItalic.ttf"); 
        
        welcomeText.setTypeface(fontNormal);
        selectedMenuText.setTypeface(fontBold);
    }
}
