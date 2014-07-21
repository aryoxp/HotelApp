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

public class NavigationButton extends LinearLayout implements android.view.View.OnFocusChangeListener {
	
	private ImageView iconView;
	private int iconResource = R.drawable.antenna2;
	private String id;
	private String label;
	private NavigationButtonInterface navigationButtonInterface;
	private int size = 96;
		
	private void initialize(String id, String label, int iconResourceId, int size) {
		this.iconResource = iconResourceId;
		this.iconView.setImageDrawable(getResources().getDrawable(iconResourceId));
		this.id = id;
		this.label = label;
		this.iconView.setTag(id);
		this.size = size;
		
		final float scale = getContext().getResources().getDisplayMetrics().density;
		int pixels = (int) (this.size * scale + 0.5f);
		LayoutParams params = (LayoutParams) this.iconView.getLayoutParams();
		params.width=pixels;
		params.height=pixels;
		this.iconView.setLayoutParams(params);
		
	}
	
	public NavigationButton(Context context) {
		this(context, null);
	}
	
	public NavigationButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_navigation_button, this, true);
		this.iconView = (ImageView) this.findViewById(R.id.navigationButtonIcon);
		this.iconView.setImageDrawable(getResources().getDrawable(this.iconResource));
		this.iconView.setOnFocusChangeListener(this);
		
	}
	
	public NavigationButton(Context context, AttributeSet attrs,
			String id, String label, int iconResourceId, int size) {
		this(context, attrs);
		initialize(id, label, iconResourceId, size);
	}
	
	public NavigationButton(Context context, 
			String id, String label, int iconResourceId, int size) {
		this(context);
		initialize(id, label, iconResourceId, size);
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
		if(hasFocus && this.navigationButtonInterface != null)
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
