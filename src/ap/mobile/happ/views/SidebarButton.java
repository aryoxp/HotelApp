package ap.mobile.happ.views;

import android.content.Context;
import android.util.AttributeSet;

public class SidebarButton extends NavigationButton {
	
	public SidebarButton(Context context) {
		super(context, null);
	}
	
	public SidebarButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public SidebarButton(Context context, String id, String label, int iconResourceId) {
		super(context, id, label, iconResourceId, 72);
	}
	
	public SidebarButton(Context context, AttributeSet attrs,
			String id, String label, int iconResourceId) {
		super(context, attrs, id, label, iconResourceId, 72);
	}

}
