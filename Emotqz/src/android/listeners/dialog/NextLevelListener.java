package android.listeners.dialog;

import gestori.GestoreInput;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidUI.LevelActivity;

public class NextLevelListener implements DialogInterface.OnClickListener {
	
	private GestoreInput gestoreInput = GestoreInput.getGestore();
	
	private Context context;
	private int categoria;
	private int stage;
	private int livello;
	
	public NextLevelListener(Context context,int categoria, int stage, int livello) {
		super();
		this.context = context;
		this.categoria = categoria;
		this.stage = stage;
		this.livello = livello;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		livelloSuccessivo();
	}
	
	private void livelloSuccessivo() {

		int livelloSuccessivo;
		
		if (livello == gestoreInput.getCategorie().get(categoria).getStages()
				.get(stage).getLivelli().size() - 1) {
			livelloSuccessivo = 0;
		} else {
			livelloSuccessivo = livello + 1;
		}
		Intent intent = new Intent(context, LevelActivity.class);
		intent.putExtra("STAGE", stage);
		intent.putExtra("CATEGORIA", categoria);
		intent.putExtra("LIVELLO", livelloSuccessivo);

		context.startActivity(intent);
	}
}
