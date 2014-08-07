package ap.mobile.happ.views;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.interfaces.NavigationButtonInterface;


public class Sidebar extends LinearLayout implements NavigationButtonInterface {

	private LinearLayout mainVerticalNavigationBar;
	private ArrayList<SidebarButton> buttons = new ArrayList<SidebarButton>();
	private AttributeSet attrs;
	private TextView sidebarNavigationLabel;
	private OnClickListener buttonsClickListener;
	
	public Sidebar(Context context) {
		this(context, null);
	}
	
	public Sidebar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_sidebar, this, true);
		this.mainVerticalNavigationBar = (LinearLayout) this.findViewById(R.id.mainVerticalNavigationBar);
		this.sidebarNavigationLabel = (TextView) this.findViewById(R.id.sidebarNavigationLabel);
		this.attrs = attrs;
	}
		
	public void addButton(String id, String label, int iconResourceId) {
		SidebarButton button = new SidebarButton(getContext(), this.attrs, id, label, iconResourceId);
		this.addButton(button);
	}
	
	public void addButton(SidebarButton button) {
		button.setFocusChangeListener(this);
		this.buttons.add(button);
		this.mainVerticalNavigationBar.addView(button);
	}

	@Override
	public void setLabel(String label) {
		this.sidebarNavigationLabel.setText(label);
	}
	
	public void setButtonsClickListener(View.OnClickListener buttonsClickListener) {
		this.buttonsClickListener = buttonsClickListener;
		for(SidebarButton button : this.buttons) {
			button.setOnClickListener(this.buttonsClickListener);
		}
	}
	
}
