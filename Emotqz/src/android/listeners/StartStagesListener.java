package android.listeners;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import androidUI.StagesActivity;

public class StartStagesListener implements OnItemClickListener {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int categoria,
			long id) {

		Intent intent = new Intent(view.getContext(), StagesActivity.class);
		intent.putExtra("CATEGORIE", categoria);

		view.getContext().startActivity(intent);
	}
}
