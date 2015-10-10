package android.listeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import androidUI.HowToPlayActivity;


public class HowToPlayListener implements OnClickListener{
	
	private Context context;

	public HowToPlayListener(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(context, HowToPlayActivity.class);
		context.startActivity(intent);
	}

}
