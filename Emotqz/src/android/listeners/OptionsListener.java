package android.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidUI.OptionsActivity;

public class OptionsListener implements OnClickListener {
	
	private Context context;

	public OptionsListener(Context context) {
		super();
		this.context = context;
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, OptionsActivity.class);
		context.startActivity(intent);	
	}
}
