package android.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidUI.CategoriesActivity;

public class PlayListener implements OnClickListener {

	private Context context;

	public PlayListener(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, CategoriesActivity.class);
		context.startActivity(intent);
	}

}
