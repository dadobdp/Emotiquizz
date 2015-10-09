package android.listeners;

import android.content.Context;
import android.utils.HintVisualizer;
import android.view.View;
import android.view.View.OnClickListener;

public class ShowHintButtonListener implements OnClickListener {

	private HintVisualizer hintVisualizer;

	public ShowHintButtonListener(String hint, Context context) {
		super();
		this.hintVisualizer = new HintVisualizer(context, hint);
	}

	@Override
	public void onClick(View v) {
		hintVisualizer.showHint();
	}

}
