package android.utils;

import com.emotiquiz.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class HintVisualizer {
	
	private Context context;
	private String hint;
	private int LHint;
	
	public HintVisualizer(Context context, String hint) {
		super();
		this.context = context;
		this.hint = hint;
	}
	
	public void showHint(){
		
		Dialog dialog1 = new Dialog(context);
		dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		dialog1.setContentView(R.layout.dialog_hint_layout);
		TextView textHint = (TextView) dialog1.findViewById(R.id.hintText);
		textHint.setBackgroundResource(R.drawable.hint_background);
		LHint = hint.length();
		
		Activity activity = (Activity) context;
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width=dm.widthPixels;
		int height=dm.heightPixels;
		int dens=dm.densityDpi;
		
		double wi=(double)width/(double)dens;
		double hi=(double)height/(double)dens;
		double x = Math.pow(wi,2);
		double y = Math.pow(hi,2);
		double screenInches = Math.sqrt(x+y);
		
		if (LHint > 100 && screenInches < 7) {
			textHint.setTextSize(12);
		}
		textHint.setText(hint);	
		dialog1.show();
	}
}
