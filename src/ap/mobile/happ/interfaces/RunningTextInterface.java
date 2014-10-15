package ap.mobile.happ.interfaces;

import java.util.ArrayList;

import ap.mobile.happ.base.RunningText;

public interface RunningTextInterface {
	public void onRunningTextLoaded(ArrayList<RunningText> result);
	public void onPreLoading();
	public void onRunningTextError(String error);
}
