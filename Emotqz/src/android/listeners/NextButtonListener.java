package android.listeners;

import gestori.GestoreInput;

import com.emotiquiz.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import androidUI.LevelActivity;

public class NextButtonListener implements OnClickListener {

	private Context context;
	private int categoria;
	private int stage;
	private int livello;
	private int value;
	private GestoreInput gestoreInput = GestoreInput.getGestore();

	public NextButtonListener(Context context, int categoria, int stage,
			int livello, int value) {
		super();
		this.context = context;
		this.categoria = categoria;
		this.stage = stage;
		this.livello = livello;
		this.value = value;
	}

	@Override
	public void onClick(View v) {

		int livelloSuccessivo = 0;

		if (value == 1) {
			if (livello == gestoreInput.getCategorie().get(categoria)
					.getStages().get(stage).getLivelli().size() - 1) {
				livelloSuccessivo = 0;
			} else {
				livelloSuccessivo = livello + 1;
			}
		} else {
			if (value == -1) {
				if (livello == 0) {
					livelloSuccessivo = gestoreInput.getCategorie()
							.get(categoria).getStages().get(stage).getLivelli()
							.size() - 1;
				} else {
					livelloSuccessivo = livello - 1;
				}
			}
		}
		Log.i("INFO", "Livello successivo: " + livelloSuccessivo);
		Intent intent = new Intent(context, LevelActivity.class);
		intent.putExtra("STAGE", stage);
		intent.putExtra("CATEGORIA", categoria);
		intent.putExtra("LIVELLO", livelloSuccessivo);
		context.startActivity(intent);
		Activity activity = (Activity) context;
		if (value == 1 ) {
			activity.overridePendingTransition(R.animator.fade_in_right,R.animator.fade_out_right);
		} else {
			activity.overridePendingTransition(R.animator.fade_in_left, R.animator.fade_out_left);
		}

	}

}
