package ap.mobile.happ.views;

import android.app.Service;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import ap.mobile.happ.R;
import ap.mobile.happ.interfaces.NavigationButtonInterface;

public class MainNavigationButton extends LinearLayout implements android.view.View.OnFocusChangeListener {

	private ImageView iconView;
	private int iconResource = R.drawable.antenna2;
	private String id;
	private String label;
	private NavigationButtonInterface navigationButtonInterface;

	public MainNavigationButton(Context context, String id, String label, int iconResourceId) {
		this(context);
		initialize(id, label, iconResourceId);
	}

	private void initialize(String id, String label, int iconResourceId) {
		this.iconResource = iconResourceId;
		this.iconView.setImageDrawable(getResources().getDrawable(iconResourceId));
		this.id = id;
		this.label = label;
		this.iconView.setTag(id);
	}
	
	public MainNavigationButton(Context context) {
		this(context, null);
	}
	
	public MainNavigationButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_navigation_button, this, true);
		this.iconView = (ImageView) this.findViewById(R.id.navigationButtonIcon);
		this.iconView.setImageDrawable(getResources().getDrawable(this.iconResource));
		this.iconView.setOnFocusChangeListener(this);
	}
	
	public MainNavigationButton(Context context, AttributeSet attrs,
			String id, String label, int iconResourceId) {
		this(context, attrs);
		initialize(id, label, iconResourceId);
	}

	public String getButtonId() {
		return this.id;
	}
	
	public String getButtonLabel() {
		return this.label;
	}
	
	@Override
	public void setOnClickListener(OnClickListener l) {
		this.iconView.setOnClickListener(l);
	}
	
	@Override
	public Object getTag() {
		return this.iconView.getTag();
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if(hasFocus)
			this.navigationButtonInterface.setLabel(this.label);
	}

	public void setFocusChangeListener(NavigationButtonInterface navigationButtonInterface) {
		this.navigationButtonInterface = navigationButtonInterface;	
	}
	
	@Override
	public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
		return this.iconView.requestFocus();
	}

}
