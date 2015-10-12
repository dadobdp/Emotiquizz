package android.listeners.dialog;

import gestori.GestoreInput;
import javaUtils.Livello;

import com.emotiquiz.R;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.listeners.HintButtonListener;
import android.listeners.ShowHintButtonListener;
import android.media.MediaPlayer;
import android.utils.HintVisualizer;
import android.widget.Button;
import android.widget.TextView;

public class ShowHintListener implements DialogInterface.OnClickListener {

	private Context context;
	private int categoria;
	private int stage;
	private int livello;
	private int nHint;

	private GestoreInput gestoreInput = GestoreInput.getGestore();

	public ShowHintListener(Context context, int categoria, int stage,
			int livello, int nHint) {
		super();
		this.context = context;
		this.categoria = categoria;
		this.stage = stage;
		this.livello = livello;
		this.nHint = nHint;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		//gestoreSalvataggi.SaveHint(categoria, stage, livello, nHint);
		//gestoreSalvataggi.SaveDbHint(categoria, stage, livello, nHint);
		gestoreInput.getDbManager().saveHintBought(categoria, stage, livello, nHint);
		
		
		Livello liv = gestoreInput.getCategorie().get(categoria).getStages()
				.get(stage).getLivelli().get(livello);
		liv.getHintSbloccati()[nHint] = 1;
			
		String hint = liv.getHints().get(nHint);
		
		//visulaizza hint
		HintVisualizer hintVisualizer = new HintVisualizer(context, hint);
		hintVisualizer.showHint();
		
		Activity activity = (Activity) context;
		
		if (nHint == 0) {
			Button button2 = (Button) activity.findViewById(R.id.hintButton2);
			button2.setOnClickListener(new HintButtonListener(activity,
					categoria, stage, livello, 1));
			Button button1 = (Button) activity.findViewById(R.id.hintButton1);
			button1.setOnClickListener(new ShowHintButtonListener(liv
					.getHints().get(0), activity));
						
			button1.setBackgroundResource(R.drawable.hint1_unlocked);
			button2.setBackgroundResource(R.drawable.hint2_tobuy);
			
			gestoreInput.setCoins(-100);
			
			suonoCoins();
					
		}
		
		if (nHint == 1) {
			Button button2 = (Button) activity.findViewById(R.id.hintButton2);
			button2.setOnClickListener(new ShowHintButtonListener(liv
					.getHints().get(1), activity));
			
			button2.setBackgroundResource(R.drawable.hint2_unlocked);
			
			gestoreInput.setCoins(-200);
			
			suonoCoins();
			
		}
		refreshCoins();
		
		//gestoreSalvataggi.saveCoins(gestoreInput.getCoins());
		//gestoreSalvataggi.saveDbCoins(gestoreInput.getCoins());
		gestoreInput.getDbManager().saveCoins(gestoreInput.getCoins());

	}

	private void refreshCoins(){
		
		Activity activity = (Activity) context;
		
		TextView n_coins = (TextView) activity.findViewById(R.id.Coins);
		n_coins.setText(""+gestoreInput.getCoins());		
	}
	
	private void suonoCoins(){
		
		MediaPlayer suonoCoins = MediaPlayer.create(context, R.raw.coin_gained);
		suonoCoins.start();
	}
}
