package android.listeners;

import gestori.GestoreInput;
import javaUtils.Livello;
import javaUtils.Stage;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.listeners.dialog.NextLevelListener;
import android.listeners.dialog.NextStageListener;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.emotqz.R;

public class ButtonSolutionListener implements OnClickListener {

	private Context context;
	private EditText editText;
	private String soluzione;
	private int categoria;
	private int stage;
	private int livello;

	private GestoreInput gestoreInput = GestoreInput.getGestore();

	public ButtonSolutionListener(Context context, EditText editText,
			String soluzione, int categoria, int stage, int livello) {
		super();
		this.context = context;
		this.editText = editText;
		this.soluzione = soluzione;
		this.categoria = categoria;
		this.stage = stage;
		this.livello = livello;
	}

	@Override
	public void onClick(View v) {
				
		String inserita = editText.getText().toString();

		if (soluzione.equalsIgnoreCase(inserita)) {
			
			int count = 0;
			boolean prima = gestoreInput.getCategorie().get(categoria).getStages().get(stage+1).isBloccato();
					
			Stage stg = gestoreInput.getCategorie().get(categoria).getStages()
					.get(stage);
			
			Livello liv = stg.getLivelli().get(livello);
		
			if (!liv.isCompletato()) {
				//INCREMENTA i coins di 100
				gestoreInput.setCoins(100);
			
				//gestoreSalvataggi.saveCoins(gestoreInput.getCoins());
				//gestoreSalvataggi.saveDbCoins(gestoreInput.getCoins());
				gestoreInput.getDbManager().saveCoins(gestoreInput.getCoins());
				liv.setCompletato(true);
				
				//gestoreSalvataggi.SaveState(categoria, stage, livello);
				//gestoreSalvataggi.saveDbstate(categoria, stage, livello);
				gestoreInput.getDbManager().saveLevelCompleted(categoria, stage, livello);
				
				gestoreInput.setStageBloccati();
				
				refreshCoins();
				
							
				if (!stg.isCompletato()) {
					
					if (stg.getLivelliCompletati() == stg.getLivelli().size()) {
						gestoreInput.setCoins(100);
						
						//gestoreSalvataggi.saveCoins(gestoreInput.getCoins());
						//gestoreSalvataggi.saveDbCoins(gestoreInput.getCoins());
						gestoreInput.getDbManager().saveCoins(gestoreInput.getCoins());
						
						stg.setCompletato(true);
					
						refreshCoins();
						stageCompletato();
						count++;
					}	
				}
					
			}
			
			if (count == 0) {
				livelloCompletato(prima);
			}
			
			
							
		}else if (almost(inserita)) {
			
			//TODO suono ci sei quasi?
			Vibrator vibrator= (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(500);
			Toast toast = Toast.makeText(context, "Ci sei quasi!",Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 100);
			toast.show();
			
			Activity activity = (Activity)context;
			ImageView view = (ImageView) activity.findViewById(R.id.LevelCompleted);
			view.setBackgroundResource(R.drawable.almost);
			
		} else {
			
			MediaPlayer suonoRisposta = MediaPlayer.create(context, R.raw.fail);
			suonoRisposta.start();
			Vibrator vibrator= (Vibrator) this.context.getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(500);
			Toast toast = Toast.makeText(context, "Soluzione sbagliata",Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 100);
			toast.show();
		}
	}

	private void livelloCompletato(boolean prima){
		
		boolean dopo = gestoreInput.getCategorie().get(categoria).getStages().get(stage+1).isBloccato();
			
		MediaPlayer suonoRisposta = MediaPlayer.create(context, R.raw.applause);

		AlertDialog.Builder miaAlert = new AlertDialog.Builder(context);
		miaAlert.setTitle("Livello Completato");
		
		if (prima!=dopo) {
			miaAlert.setMessage("Complimenti! Hai superato il livello!\nHai sbloccato lo stage successivo!");
		}else{
			miaAlert.setMessage("Complimenti! Hai superato il livello!");
		}
		
		miaAlert.setPositiveButton("Passa al livello successivo", new NextLevelListener(context,categoria, stage, livello));
		miaAlert.setIcon(R.drawable.applause);
		AlertDialog alert = miaAlert.create();
		alert.show();
		
		suonoRisposta.start();
	}
	
	private void stageCompletato(){
		
		MediaPlayer suonoRisposta = MediaPlayer.create(context, R.raw.applause);

		AlertDialog.Builder miaAlert = new AlertDialog.Builder(context);
		miaAlert.setTitle("Stage Completato");
		miaAlert.setMessage("Complimenti! Hai superato tutti i livelli dello stage!");
		miaAlert.setPositiveButton("Ok", new NextStageListener(context));
		miaAlert.setIcon(R.drawable.applause);
		AlertDialog alert = miaAlert.create();
		alert.show();
		
		suonoRisposta.start();
	}
	
	
	@SuppressLint("DefaultLocale")
	private boolean almost(String inserita){
		
		int percentuale_inserita;
		int percentuale_soluzione;
		int count = 0;
		
		if(soluzione.length() < 7){
			
			if (Math.abs(soluzione.length() - inserita.length()) > 1) {
				return false;
			}
			
			percentuale_soluzione = soluzione.length()-1;
			
			try {
				for (int i = 0; i < percentuale_soluzione; i++) {
					if (inserita.toLowerCase().charAt(i) == soluzione.toLowerCase().charAt(i)) {	
						count ++;
					}
				}
			} catch (Exception e) {
				// nothing to catch
			}
			
			if (count == percentuale_soluzione) {
				return true;
			}else{
				return false;
			}
		}else{		
			percentuale_inserita = (int)(((float)inserita.length()/5)*4);
			percentuale_soluzione = (int)(((float)soluzione.length()/5)*4);
		}
		
		int diff = percentuale_soluzione-percentuale_inserita;
		
		if (diff == 0) {
			for (int i = 0; i < soluzione.length(); i++) {
				if (inserita.toLowerCase().charAt(i) != soluzione.toLowerCase().charAt(i)) {	
					count ++;
				}
			}
			if (count > 2) {
				return false;
			}else{
				return true;
			}
		}else{
			
			if (Math.abs(diff)>2) {
				return false;
			}

			try {
				
				for (int i = 0; i < percentuale_inserita; i++) {
					if (inserita.toLowerCase().charAt(i) != soluzione.toLowerCase().charAt(i)) {	
						count ++;
					}
				}
				
			} catch (Exception e) {
				//nothing to catch
			}
			
			if (count == 0) {
				return true;
			}else{
				return false;
			}
				
		}		
	}
		
	private void refreshCoins(){	
		Activity activity = (Activity) context;
		
		TextView n_coins = (TextView) activity.findViewById(R.id.Coins);
		n_coins.setText(""+gestoreInput.getCoins());		
	}
}
