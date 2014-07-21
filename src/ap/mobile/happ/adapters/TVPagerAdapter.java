package ap.mobile.happ.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import ap.mobile.happ.TVItemFragment;
import ap.mobile.happ.base.TVMedia;

public class TVPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<TVMedia> tvMedias;
	private Context context;
	private int perpage = 10;
	
	public TVPagerAdapter(FragmentManager fm, ArrayList<TVMedia> TVMedias, Context context) {
		super(fm);
		this.tvMedias = TVMedias;
		this.setContext(context);
	}
	
	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}

	@Override
	public Fragment getItem(int position) {
		return TVItemFragment.newInstance(tvMedias.get(position));
	}

	@Override
	public int getCount() {
		double count = this.tvMedias.size();
		int size = (int) Math.ceil(count/(double)this.perpage);
		return size;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return "Page " + (position+1);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
