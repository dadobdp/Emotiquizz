package android.listeners.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

public class NextStageListener implements DialogInterface.OnClickListener {
	
	private Context context;
	
	
	public NextStageListener(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		
			Activity activity = (Activity) context;
			activity.finish();
		
	}

}
