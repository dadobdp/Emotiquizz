package androidUI;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.emotqz.R;

public class CreditsActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits);
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int height = size.y;
		
		TextView creditsTitle = (TextView)findViewById(R.id.CreditsTitle);
		LinearLayout layoutDado = (LinearLayout)findViewById(R.id.DadoCredits);
		LinearLayout layoutAlbo = (LinearLayout)findViewById(R.id.AlboCredit);
		LinearLayout layoutFede = (LinearLayout)findViewById(R.id.FedeCredit);
		
		ImageView imageDado = (ImageView)findViewById(R.id.DadoImage);
		ImageView imageAlbo = (ImageView)findViewById(R.id.AlboImage);
		ImageView imageFede = (ImageView)findViewById(R.id.FedeImage);
		
		LayoutParams layoutParams=new LayoutParams(LayoutParams.MATCH_PARENT, height/4);
		LayoutParams titleParams=new LayoutParams(LayoutParams.MATCH_PARENT, height/6);
		LayoutParams imageParams = new LayoutParams(height/4, height/4);
		
		creditsTitle.setLayoutParams(titleParams);
		creditsTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		
		layoutDado.setLayoutParams(layoutParams);
		layoutAlbo.setLayoutParams(layoutParams);
		layoutFede.setLayoutParams(layoutParams);
		
		imageDado.setLayoutParams(imageParams);
		imageAlbo.setLayoutParams(imageParams);
		imageFede.setLayoutParams(imageParams);
		
		
		
	}

}
