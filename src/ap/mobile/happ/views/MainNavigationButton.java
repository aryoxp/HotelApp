package ap.mobile.happ.views;

import android.content.Context;
import android.util.AttributeSet;

public class MainNavigationButton extends NavigationButton {
	
	public MainNavigationButton(Context context) {
		super(context, null);
	}
	
	public MainNavigationButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public MainNavigationButton(Context context, String id, String label, int iconResourceId) {
		super(context, id, label, iconResourceId, 96);
	}
	
	public MainNavigationButton(Context context, AttributeSet attrs,
			String id, String label, int iconResourceId) {
		super(context, attrs, id, label, iconResourceId, 96);
	}
	
}
