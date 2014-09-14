package ap.mobile.happ;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.view.ViewGroup;
import android.widget.TextView;
import ap.mobile.happ.interfaces.LanguageDialogInterface;


public class LanguageDialogFragment extends DialogFragment implements OnClickListener, OnFocusChangeListener {
	
	LanguageDialogInterface dialogInterface;
	View layout;
	View okButton;
	TextView dialogTextView;
	
	private TextView dialogTitleTextView;
	private String dialogText;
	private View idButton;
	
	private String language;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRetainInstance(true);
	}
	
	public void setDialogCallback(LanguageDialogInterface dialogInterface) {
		this.dialogInterface = dialogInterface;
	}
	
	public void setLanguage(String language) {
		this.language = language;
		if(language.equals("en"))
			this.dialogTextView.setText("English");
		else this.dialogTextView.setText("Bahasa Indonesia");
	}
	
	public void setButtonText(String buttonText) {
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.layout = inflater.inflate(R.layout.view_alert_dialog, container);
		this.dialogTextView = (TextView) this.layout.findViewById(R.id.languageDialogText);
		if(this.dialogTextView != null)
			this.dialogTextView.setText(this.dialogText);
		this.dialogTitleTextView = (TextView) this.layout.findViewById(R.id.languageDialogTitleText);
		this.okButton = this.layout.findViewById(R.id.languageDialogEnButton);
		this.okButton.setOnClickListener(this);
		this.idButton = this.layout.findViewById(R.id.languageDialogIdButton);
		this.idButton.setOnClickListener(this);
		this.layout.setBackgroundColor(Color.TRANSPARENT);
		this.dialogTitleTextView.setText("Select Language");
		this.okButton.setOnFocusChangeListener(this);
		this.idButton.setOnFocusChangeListener(this);
		
		return this.layout;
	}
		
	@Override
	public void onStart() {
		
		Window window = getDialog().getWindow();
	    window.setBackgroundDrawableResource(R.drawable.bg_popup);
	    this.dialogInterface.onDialogDisplayed();
	    super.onStart();
	}

	@Override
	public void onClick(View v) {
		String language = "en";
		switch (v.getId()) {
		case R.id.languageDialogEnButton:
			language = "en";
			break;
		case R.id.languageDialogIdButton:
			language = "id";
			break;
		default:
			language = "en";
			break;
		}
		this.language = language;
		if(this.dialogInterface != null)
			this.dialogInterface.onLanguageSelected(language);
		this.dismiss();
	}
	
	public String getLanguage() {
		return this.language;
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if(hasFocus) {
			switch(v.getId()) {
			case R.id.languageDialogEnButton:
				this.dialogTextView.setText("English");
				break;
			case R.id.languageDialogIdButton:
				this.dialogTextView.setText("Bahasa Indonesia");
				break;
			}
		}
	}
}
