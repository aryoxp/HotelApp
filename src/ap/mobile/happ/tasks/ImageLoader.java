package ap.mobile.happ.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import ap.mobile.utility.RestClient;

public class ImageLoader extends AsyncTask<Object, Integer, Bitmap> {

	private ImageView container;
	
	@Override
	protected Bitmap doInBackground(Object... params) {
		
		this.container = (ImageView)params[0];
		String url = (String)params[1];
		
		Bitmap image = RestClient.getBitmapFromURL(url);
		return image;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if(!result.equals(null) && !this.container.equals(null))
			this.container.setImageBitmap(result);
		// TODO: else display broken image indicator.
	}
}
