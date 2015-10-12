package android.listeners;

import gestori.GestoreInput;

import com.emotiquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import androidUI.LevelsActivity;

public class StartLevelsListener implements OnItemClickListener {

	private int categoria;
	private GestoreInput gestoreInput = GestoreInput.getGestore();

	public StartLevelsListener(int categoria) {
		super();
		this.categoria = categoria;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		if (!gestoreInput.getCategorie().get(categoria).getStages()
				.get(position).isBloccato()) {
			Intent intent = new Intent(view.getContext(), LevelsActivity.class);
			intent.putExtra("STAGE", position);
			intent.putExtra("CATEGORIA", categoria);
			view.getContext().startActivity(intent);
			
			Activity activity = (Activity)view.getContext();
			activity.overridePendingTransition(R.animator.push_down_in,R.animator.push_down_out);
		}
	}
}