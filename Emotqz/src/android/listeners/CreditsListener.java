 package android.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidUI.CreditsActivity;

public class CreditsListener implements OnClickListener{
	
	private Context context;
	
	public CreditsListener(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, CreditsActivity.class);
		context.startActivity(intent);
	}

}
