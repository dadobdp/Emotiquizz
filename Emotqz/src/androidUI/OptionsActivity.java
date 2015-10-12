package androidUI;

import com.emotiquiz.R;

import android.app.Activity;
import android.listeners.CreditsListener;
import android.listeners.HowToPlayListener;
import android.listeners.RipristinaListener;
import android.os.Bundle;
import android.widget.Button;

public class OptionsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		Button howToPlayButton = (Button) findViewById(R.id.howToPlayButton);
		Button ripristinaButton = (Button) findViewById(R.id.ripristinaButton);
		Button creditsButton = (Button) findViewById(R.id.creditsButton);
		
		howToPlayButton.setOnClickListener(new HowToPlayListener(this));//TODO
		ripristinaButton.setOnClickListener(new RipristinaListener(this));
		creditsButton.setOnClickListener(new CreditsListener(this));

	}

}
