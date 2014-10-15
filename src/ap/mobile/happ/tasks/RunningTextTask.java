package ap.mobile.happ.tasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import ap.mobile.happ.base.RunningText;
import ap.mobile.happ.interfaces.RunningTextInterface;
import ap.mobile.utility.RestClient;

public class RunningTextTask extends AsyncTask<String, Void, ArrayList<RunningText>> {

	private RunningTextInterface runningTextInterface;
	private String error;
	
	public RunningTextTask(RunningTextInterface runningTextInterface) {
		this.runningTextInterface = runningTextInterface;
	}
	
	@Override
	protected void onPreExecute() {
		this.runningTextInterface.onPreLoading();
	}

	@Override
	protected ArrayList<RunningText> doInBackground(String... params) {
		ArrayList<RunningText> runningTexts = new ArrayList<RunningText>();
		
		try {
			String result = RestClient.GET(params[0]);
			JSONArray runningTextJSONArray = new JSONArray(result);
			for(int i = 0; i<runningTextJSONArray.length(); i++) {
				JSONObject object = (JSONObject) runningTextJSONArray.get(i);
				RunningText rt = new RunningText();
				rt.headline = object.getString("headline");
				runningTexts.add(rt);
			}
		} catch(Exception e) {
			this.error = e.getMessage();
			e.printStackTrace();
		}
		
		return runningTexts;
	}
	
	@Override
	protected void onPostExecute(ArrayList<RunningText> result) {
		if(this.error != null)
			this.runningTextInterface.onRunningTextError(this.error);
		this.runningTextInterface.onRunningTextLoaded(result);
	}

}
