package android.listeners;

import gestori.GestoreInput;
import android.app.AlertDialog;
import android.content.Context;
import android.listeners.dialog.ShowHintListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.emotqz.R;

public class HintButtonListener implements OnClickListener {
	private Context context;
	private int categoria;
	private int stage;
	private int livello;
	private int nHint;
	private GestoreInput gestoreInput=GestoreInput.getGestore();

	public HintButtonListener(Context context, int categoria, int stage,
			int livello, int nHint) {
		super();
		this.context = context;
		this.categoria = categoria;
		this.stage = stage;
		this.livello = livello;
		this.nHint = nHint;
	}

	@Override
	public void onClick(View v) {
		
		if (nHint==0) {
			if (gestoreInput.getCoins()>=100) {
				showHintAlert();
			} else {
				Toast toast=Toast.makeText(context, "Coins non sufficienti", Toast.LENGTH_SHORT);
				toast.show();
			}
		}else if(nHint==1){
			if (gestoreInput.getCoins()>=200) {
				showHintAlert();
			} else {
				Toast toast=Toast.makeText(context, "Coins non sufficienti", Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	}

	private void showHintAlert() {
		AlertDialog.Builder showHintAlert = new AlertDialog.Builder(
				context);
		showHintAlert.setTitle("Hint numero "+(nHint+1));
		String message = "Vuoi comprare un hint?\n";
		
		if (nHint==0) {
			showHintAlert.setMessage(message+"Questo hint costa 100 coins");
		}else if (nHint==1) {
			showHintAlert.setMessage(message+"Questo hint costa 200 coins");
		}
				
		showHintAlert.setPositiveButton("Compra", new ShowHintListener(
				context, categoria, stage, livello, nHint));
		showHintAlert.setNegativeButton("Annulla", null);
		showHintAlert.setIcon(R.drawable.icon);
		AlertDialog alert = showHintAlert.create();
		alert.show();
	}

}
