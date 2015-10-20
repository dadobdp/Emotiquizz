package android.listeners;

import gestori.GestoreInput;
import javaUtils.Livello;
import javaUtils.Stage;

import com.emotiquiz.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.listeners.dialog.NextLevelListener;
import android.listeners.dialog.NextStageListener;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
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
			
			boolean prima = false;
			
			if(this.stage < gestoreInput.getCategorie().get(categoria).getStages().size()-1){
				prima = gestoreInput.getCategorie().get(categoria).getStages().get(stage+1).isBloccato();
			}
			
			Stage stg = gestoreInput.getCategorie().get(categoria).getStages()
					.get(stage);
			
			Livello liv = stg.getLivelli().get(livello);
		
			if (!liv.isCompletato()) {
				//INCREMENTA i coins di 100
				gestoreInput.setCoins(100);
			
				gestoreInput.getDbManager().saveCoins(gestoreInput.getCoins());
				liv.setCompletato(true);
			
				gestoreInput.getDbManager().saveLevelCompleted(categoria, stage, livello);
				
				gestoreInput.setStageBloccati();
				
				refreshCoins();
				
							
				if (!stg.isCompletato()) {
					
					if (stg.getLivelliCompletati() == stg.getLivelli().size()) {
						gestoreInput.setCoins(100);
					
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
		
		boolean dopo = false;
		
		if(this.stage < gestoreInput.getCategorie().get(categoria).getStages().size()-1){
			
			dopo = gestoreInput.getCategorie().get(categoria).getStages().get(stage+1).isBloccato();
		}
			
		MediaPlayer suonoRisposta = MediaPlayer.create(context, R.raw.applause);

		AlertDialog.Builder miaAlert = new AlertDialog.Builder(context);
		miaAlert.setTitle("Livello Completato");
		
		if (prima!=dopo) {
			miaAlert.setMessage("Complimenti! Hai sbloccato lo stage successivo!");
			miaAlert.setIcon(R.drawable.strong);
		}else{
			miaAlert.setMessage("Complimenti! Hai superato il livello!");
			miaAlert.setIcon(R.drawable.applause);
		}
		
		miaAlert.setPositiveButton("Passa al livello successivo", new NextLevelListener(context,categoria, stage, livello));
		
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
		miaAlert.setIcon(R.drawable.top);
		AlertDialog alert = miaAlert.create();
		alert.show();
		
		suonoRisposta.start();
	}
	
	
	@SuppressLint("DefaultLocale")
	private boolean almost(String inserita){
		
		boolean almost = false;
		boolean toCheck = false;
		
		int limite=0;
		
		int l1 = soluzione.length();
		int l2 = inserita.length();
		
		int str_minore = l1;
		if (l1 > l2) {
			str_minore = l2;
		}
		
		int distanza = Math.abs(l1 - l2);
		
		if(  ((l1 < 9) && distanza < 2)  ){
			toCheck = true;
			limite = 2;
		}if (((l1 >= 9) && distanza < 5) ) {
			toCheck = true;
			limite = 5;
		}
	
		if (toCheck) {
				
			String soluzione_invertita = new StringBuffer(soluzione).reverse().toString();
			String inserita_invertita = new StringBuffer(inserita).reverse().toString();
			
			boolean forward_control = checkAlmost(limite, str_minore, soluzione, inserita);
			boolean backward_control = checkAlmost(limite, str_minore, soluzione_invertita, inserita_invertita);
			
			if (backward_control || forward_control) {
				almost = true;
			}	
		}
		return almost;
	}
	
	//controlla se la stringa è simile alla soluzione in base al limite passato.
	private boolean checkAlmost(int limite, int str_minore, String s1, String s2){
		
		int count = 0;
		
		for (int i = 0; i < str_minore; i++) {
			if( s1.toLowerCase().charAt(i) != s2.toLowerCase().charAt(i) ){
				count++;
			}
		}
		Log.i("TEST", "count = "+count);
		
		if (count < limite) {
			return true;
		}else{
			return false;
		}	
	}
		
	private void refreshCoins(){	
		Activity activity = (Activity) context;
		
		TextView n_coins = (TextView) activity.findViewById(R.id.Coins);
		n_coins.setText(""+gestoreInput.getCoins());		
	}
}
