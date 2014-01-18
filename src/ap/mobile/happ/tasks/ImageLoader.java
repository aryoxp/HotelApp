package ap.mobile.happ.tasks;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import ap.mobile.utility.RestClient;

public class ImageLoader extends AsyncTask<Object, Integer, Bitmap> {

	private ImageView container;
	private ScaleType scale = ScaleType.FIT_CENTER;
	
	@Override
	protected Bitmap doInBackground(Object... params) {
		try {
			this.container = (ImageView)params[0];
			String url = (String)params[1];
			if(params.length>2) {
				ScaleType scale = (ScaleType)params[2];
				if(scale != null) 
					this.scale = scale;
			}
		
			Bitmap image = RestClient.getBitmapFromURL(url);
			return image;
		} catch(Exception e) {
			Log.e("Bitmap.err", e.getMessage());
		}
		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		if(!result.equals(null) && !this.container.equals(null))
			this.container.setImageBitmap(result);
			this.container.setScaleType(this.scale);
		// TODO: else display broken image indicator.
	}
}
