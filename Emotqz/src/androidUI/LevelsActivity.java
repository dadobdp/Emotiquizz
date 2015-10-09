package androidUI;

import gestori.GestoreInput;

import java.util.ArrayList;

import javaUtils.Livello;
import android.app.Activity;
import android.graphics.Point;
import android.listeners.StartLevelListener;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.emotqz.R;

public class LevelsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_levels);

		// Prendo la larghezza del display
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;

		int stage = getIntent().getIntExtra("STAGE", 0);
		int categoria = getIntent().getIntExtra("CATEGORIA", 0);
		
		TextView textView = (TextView)findViewById(R.id.stageNumber);
		textView.setText("Stage: "+(stage+1));
		
		GridLayout griglia = (GridLayout) findViewById(R.id.griglia);
		GestoreInput gestoreInput = GestoreInput.getGestore();

		ArrayList<Livello> livelli = gestoreInput.getCategorie().get(categoria)
				.getStages().get(stage).getLivelli();

		for (int i = 0; i < livelli.size(); i++) {
			Button button = new Button(this);
			button.setText("" + (i + 1));
			button.setTextSize(35);
			if (livelli.get(i).isCompletato()) {
				button.setBackgroundResource(R.drawable.emot_button_risolto);

			} else {
				button.setBackgroundResource(R.drawable.emot_button);
			}
			button.setOnClickListener(new StartLevelListener(categoria, stage,
					i, this));
			// setto la larghezza dei bottoni in base alla larghezza del display
			griglia.addView(button, (width / 3), (width / 3));
		}
	}

	@Override
	protected void onRestart() {
		recreate();
		super.onRestart();
	}
	
}
