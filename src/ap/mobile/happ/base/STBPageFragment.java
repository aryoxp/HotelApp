package ap.mobile.happ.base;

import android.support.v4.app.Fragment;
import ap.mobile.happ.interfaces.MainActivityInterface;

public abstract class STBPageFragment extends Fragment {
	
	protected MainActivityInterface mainActivityInterface;

	public void setMainActivityInterface(MainActivityInterface mainActivity) {
		this.mainActivityInterface = mainActivity;
	}
	
	public MainActivityInterface getMainActivityInterface() {
		return this.mainActivityInterface;
	}
	
	public abstract String getPageTitle();
	public abstract STBPage getPageId();
}
