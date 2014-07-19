package ap.mobile.happ;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnErrorListener;
import io.vov.vitamio.MediaPlayer.OnPreparedListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PlayActivity extends Activity implements OnCompletionListener, OnPreparedListener, OnErrorListener {
	
	private VideoView mVideoView;
	private String url;
	private ProgressBar load;
	private TextView empty;	
	private SurfaceHolder holder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_play);
		url = getIntent().getStringExtra("url");
		init();
	}

	public void init() {
		load = (ProgressBar) this.findViewById(R.id.load);
		empty = (TextView) this.findViewById(R.id.empty);
		mVideoView = (VideoView) this.findViewById(R.id.surface_view);
		
		mVideoView = (VideoView) this.findViewById(R.id.surface_view);
	    mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_HIGH);

	    holder = mVideoView.getHolder();
	    holder.setFormat(PixelFormat.RGBX_8888);
		
		mVideoView.setMediaController(new MediaController(this));
		mVideoView.setOnCompletionListener(this);
		mVideoView.setOnPreparedListener(this);
		mVideoView.setOnErrorListener(this);
		Uri videoUri = Uri.parse(url);
		mVideoView.setVideoURI(videoUri);
		mVideoView.requestFocus();
		loading();
	}

	private void loading() {
		load.setVisibility(View.VISIBLE);
		empty.setVisibility(View.GONE);
	}

	private void loadComplete(MediaPlayer arg0) {
		load.setVisibility(View.GONE);
		empty.setVisibility(View.GONE);
		mVideoView.start();
		mVideoView.resume();
	}

	private void error(String msg) {
		load.setVisibility(View.GONE);
		mVideoView.setVisibility(View.GONE);
		empty.setVisibility(View.VISIBLE);
		if (msg != null)
			empty.setText(msg);
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		Log.d("ONLINE TV", "Prepared");
		loadComplete(arg0);
	}

	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		Log.d("ONLINE TV", "Error");
		error("Unable to play this channel.");
		return false;
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		Log.d("ONLINE TV", "Complete");
	}
}
