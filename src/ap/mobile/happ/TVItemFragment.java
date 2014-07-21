package ap.mobile.happ;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import ap.mobile.happ.base.TVMedia;

public class TVItemFragment extends Fragment {
	
	private TVMedia media;
	
	public static TVItemFragment newInstance(TVMedia media) {
		TVItemFragment fragment = new TVItemFragment();
		fragment.setMedia(media);
		return fragment;
	}

	private void setMedia(TVMedia media) {
		this.media = media;
	}
	
	public TVMedia getMedia() {
		return this.media;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.item_tv, container, false);
		return v;
	}
}
