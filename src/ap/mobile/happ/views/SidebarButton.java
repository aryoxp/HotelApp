package ap.mobile.happ.views;

import android.app.Service;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import ap.mobile.happ.R;

public class SidebarButton extends LinearLayout {

	private ImageView iconView;
	private int iconResource = R.drawable.antenna2;
	private String id;
	
	public SidebarButton(Context context, String id, int iconResourceId) {
		this(context);
		initialize(id, iconResourceId);
	}

	public SidebarButton(Context context, AttributeSet attrs, String id, int iconResourceId) {
		this(context, attrs);
		initialize(id, iconResourceId);
	}

	private void initialize(String id, int iconResourceId) {
		this.iconResource = iconResourceId;
		this.iconView.setImageDrawable(getResources().getDrawable(iconResourceId));
		this.id = id;
	}
	
	public SidebarButton(Context context) {
		this(context, null);
	}
	
	public SidebarButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_sidebar_button, this, true);
		this.iconView = (ImageView) this.findViewById(R.id.sidebarButtonIcon);
		this.iconView.setImageDrawable(getResources().getDrawable(this.iconResource));
	}
	
	public String getButtonId() {
		return this.id;
	}
	
	public void setClickListener(View.OnClickListener clickListener) {
		this.setOnClickListener(clickListener);
	}

}
