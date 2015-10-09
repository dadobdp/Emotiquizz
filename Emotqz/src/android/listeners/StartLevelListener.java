package android.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidUI.LevelActivity;

public class StartLevelListener implements OnClickListener {

	private int categoria;
	private int stage;
	private int livello;
	private Context context;

	public StartLevelListener(int categoria, int stage, int livello,
			Context context) {
		super();
		this.categoria = categoria;
		this.stage = stage;
		this.livello = livello;
		this.context = context;
	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent(context, LevelActivity.class);
		intent.putExtra("STAGE", stage);
		intent.putExtra("CATEGORIA", categoria);
		intent.putExtra("LIVELLO", livello);

		context.startActivity(intent);
	}
}
