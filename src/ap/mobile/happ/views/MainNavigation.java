package ap.mobile.happ.views;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import ap.mobile.happ.R;
import ap.mobile.happ.interfaces.NavigationButtonInterface;


public class MainNavigation extends LinearLayout implements NavigationButtonInterface {

	private GridLayout mainNavigationButtonContainer;
	private ArrayList<MainNavigationButton> buttons = new ArrayList<MainNavigationButton>();
	private AttributeSet attrs;
	private TextView selectedMenuText;
	
	
	public MainNavigation(Context context) {
		this(context, null);
	}
	
	public MainNavigation(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater) context
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_navigation, this, true);
		this.mainNavigationButtonContainer = (GridLayout) this.findViewById(R.id.mainNavigationButtonContainer);
		this.selectedMenuText = (TextView) this.findViewById(R.id.selectedMenuText);
		this.attrs = attrs;
	}
		
	public void addButton(String id, String label, int iconResourceId) {
		MainNavigationButton button = new MainNavigationButton(getContext(), this.attrs, id, label, iconResourceId);
		this.addButton(button);
	}

	public void addButton(MainNavigationButton button) {
		this.buttons.add(button);
		button.setFocusChangeListener(this);
		this.mainNavigationButtonContainer.addView(button);
	}
	
	public void selectButton(int index) {
		this.buttons.get(index).requestFocus();
	}
	
	@Override
	public void setLabel(String label) {
		this.selectedMenuText.setText(label);
	}
	
	
}
