package ap.mobile.happ.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ap.mobile.happ.TVFragment;
import ap.mobile.happ.base.TVMedia;

public class TVPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<TVMedia> TVMedias;
	private Context context;
	private int perpage = 10;
	
	public TVPagerAdapter(FragmentManager fm, ArrayList<TVMedia> TVMedias, Context context) {
		super(fm);
		this.TVMedias = TVMedias;
		this.context = context;
	}
	
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}

	@Override
	public Fragment getItem(int position) {
		TVFragment fragment = new TVFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("page", position + 1);
		fragment.setArguments(bundle);
		fragment.setContext(this.context);
		fragment.setMedias(this.TVMedias);
		return fragment;
	}

	@Override
	public int getCount() {
		double count = this.TVMedias.size();
		int size = (int) Math.ceil(count/(double)this.perpage);
		return size;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return "Page " + (position+1);
	}

}
