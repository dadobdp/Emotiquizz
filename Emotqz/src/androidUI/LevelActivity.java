package androidUI;

import gestori.GestoreInput;

import java.io.IOException;
import java.io.InputStream;

import com.emotiquiz.R;

import javaUtils.Livello;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.listeners.ButtonSolutionListener;
import android.listeners.HintButtonListener;
import android.listeners.NextButtonListener;
import android.listeners.ShowHintButtonListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class LevelActivity extends Activity {

	private int stage;
	private int categoria;
	private int livello;
	
	private GestoreInput gestoreInput = GestoreInput.getGestore();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level);

		this.livello = getIntent().getIntExtra("LIVELLO", 0);
		this.stage = getIntent().getIntExtra("STAGE", 0);
		this.categoria = getIntent().getIntExtra("CATEGORIA", 0);
		
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.Emoticons);
		AssetManager assetManager = getAssets();

		Livello liv = gestoreInput.getCategorie().get(categoria).getStages()
				.get(stage).getLivelli().get(livello);
	
		setLevelTitle(liv);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		
		setLevelEmoticons(linearLayout, assetManager, liv, width);

		LinearLayout.LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, (width / 2));
		linearLayout.setLayoutParams(layoutParams);
				
		setLevelButtonListeners(liv);
		
		setCoins();
		
		setHintsButtons(liv);
		

		
	}

	private void setCoins(){
		
		Log.i("P", "pp1");
		TextView n_coins = (TextView) findViewById(R.id.Coins);
		Log.i("P", "pp1");
		n_coins.setText(""+gestoreInput.getCoins());
		Log.i("P", "pp2");
		ImageView view = (ImageView) findViewById(R.id.CoinsImage);
		view.setBackgroundResource(R.drawable.coins);
		Log.i("P", "pp3");
	}
	
	//setta i bottoni verifica, next, prev e la textfield
	private void setLevelButtonListeners(Livello liv) {
		Button verifyButton = (Button) findViewById(R.id.VerifyButton);
		EditText solution = (EditText) findViewById(R.id.Solution);
		ButtonSolutionListener solutionListener = new ButtonSolutionListener(
				this, solution, liv.getSoluzione(), categoria, stage, livello);
		verifyButton.setOnClickListener(solutionListener);

		Button nextButton = (Button) findViewById(R.id.nextButton);
		nextButton.setOnClickListener(new NextButtonListener(this, categoria,
				stage, livello, 1));
		Button prevButton = (Button) findViewById(R.id.prevButton);
		prevButton.setOnClickListener(new NextButtonListener(this, categoria,
				stage, livello, -1));
	}

	//setta i bottoni per gli hints
	private void setHintsButtons(Livello liv) {
		Button hint1Button=(Button) findViewById(R.id.hintButton1);
		Button hint2Button=(Button) findViewById(R.id.hintButton2);
		
		
		
		if (liv.getHintSbloccati()[0]==0 && liv.getHintSbloccati()[1]==0) {
			//Puoi comprare solo il primo hint
			hint1Button.setOnClickListener(new HintButtonListener(this, categoria, stage, livello, 0));
			hint1Button.setBackgroundResource(R.drawable.hint1_tobuy);
			
			hint2Button.setOnClickListener(null);
			hint2Button.setBackgroundResource(R.drawable.hint_locked);
			
		}
		if (liv.getHintSbloccati()[0]==1 && liv.getHintSbloccati()[1]==0) {
			//Bottone 1 fa vedere hint bottone 2 puoi comprare
			String hint1=liv.getHints().get(0);
			hint1Button.setOnClickListener(new ShowHintButtonListener(hint1, this));
			hint1Button.setBackgroundResource(R.drawable.hint1_unlocked);
			
			hint2Button.setOnClickListener(new HintButtonListener(this, categoria, stage, livello, 1));
			hint2Button.setBackgroundResource(R.drawable.hint2_tobuy);
			
		} else if (liv.getHintSbloccati()[0]==1 && liv.getHintSbloccati()[1]==1){
			//Caso in cui tutti e due gli hint sono gia stati sbloccati
			String hint1=liv.getHints().get(0);
			String hint2=liv.getHints().get(1);
			
			hint1Button.setOnClickListener(new ShowHintButtonListener(hint1, this));
			hint1Button.setBackgroundResource(R.drawable.hint1_unlocked);
			
			hint2Button.setOnClickListener(new ShowHintButtonListener(hint2, this));
			hint2Button.setBackgroundResource(R.drawable.hint2_unlocked);
		}
	}

	//setta le emoticons
	private void setLevelEmoticons(LinearLayout linearLayout,
			AssetManager assetManager, Livello liv, int width) {
		for (int i = 0; i < liv.getEmoticons().size(); i++) {
			ImageView imageView = new ImageView(this);
			InputStream is = null;
			String emo = "Emo/" + liv.getEmoticons().get(i) + ".png";
			try {

				is = assetManager.open(emo);
				Bitmap bitmap = null;
				bitmap = BitmapFactory.decodeStream(is);
				imageView.setImageBitmap(bitmap);
				int NOfEmo = liv.getEmoticons().size();
				int EmSize = width / NOfEmo;

				// TODO dimensione troppo piccola per due emoticon
				if (NOfEmo == 1) {

					EmSize = EmSize / 3;
					linearLayout.addView(imageView, EmSize, EmSize);

				} else if (NOfEmo == 2) {

					EmSize = EmSize / 2;
					linearLayout.addView(imageView, EmSize, EmSize);

				} else {

					linearLayout.addView(imageView, EmSize, EmSize);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
	//setta il titolo del livello e l'immagine (se completato o no)
	private void setLevelTitle(Livello liv){
		
		TextView titolo = (TextView) findViewById(R.id.LevelTitle);
		titolo.setText("Livello : " + (livello + 1));
		
		ImageView view = (ImageView) findViewById(R.id.LevelCompleted);
		
		if (liv.isCompletato()) {
			view.setBackgroundResource(R.drawable.cleared);
		}else {
			view.setBackgroundResource(R.drawable.uncleared);
		}
	}
	

	@Override
	protected void onPause() {
		finish();
		super.onPause();
	}
	
}
