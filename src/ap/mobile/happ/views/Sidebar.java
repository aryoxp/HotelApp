package ap.mobile.happ.views;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import ap.mobile.happ.R;


public class Sidebar extends LinearLayout {

	private LinearLayout mainVerticalNavigationBar;
	private ArrayList<SidebarButton> buttons = new ArrayList<SidebarButton>();
	private AttributeSet attrs;
	
	public Sidebar(Context context) {
		this(context, null);
	}
	
	public Sidebar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_sidebar, this, true);
		this.mainVerticalNavigationBar = (LinearLayout) this.findViewById(R.id.mainVerticalNavigationBar);
		this.attrs = attrs;
	}
		
	public void addButton(String id, int iconResourceId) {
		SidebarButton button = new SidebarButton(getContext(), this.attrs, id, iconResourceId);
		this.buttons.add(button);
		this.mainVerticalNavigationBar.addView(button);
	}
	
	public void addButton(SidebarButton button) {
		this.buttons.add(button);
		this.mainVerticalNavigationBar.addView(button);
	}
	
}
