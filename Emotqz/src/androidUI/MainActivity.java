package androidUI;

import android.app.Activity;
import android.graphics.Point;
import android.listeners.OptionsListener;
import android.listeners.PlayListener;
import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.emotqz.R;

public class MainActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Button playButton = (Button) findViewById(R.id.playButton);
		Button optionsButton = (Button) findViewById(R.id.optionsButton);
		LinearLayout titolo_image = (LinearLayout) findViewById(R.id.TitleLayout);
		LinearLayout bottoni = (LinearLayout) findViewById(R.id.titleButtonLayout);
		ImageView image = (ImageView) findViewById(R.id.mainImage);
		ImageView title = (ImageView) findViewById(R.id.title);

		playButton.setOnClickListener(new PlayListener(this));
		optionsButton.setOnClickListener(new OptionsListener(this));
		
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		
		int width = size.x;
		int height = size.y;
		
		//Sets Width and Height of title
		LayoutParams titleLayoutParams = new LayoutParams(width,width);
		LayoutParams titleParams = new LayoutParams(width,width/3);
		LayoutParams imageParams = new LayoutParams((width*2)/3,(width*2)/3);
		//Set Width and Height of the layout which contains the 2 buttons play and options
		LayoutParams buttonsLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT,(height-height/3));
		//Set Width and Height of a single button
		LayoutParams buttonParams = new LayoutParams(width/2,width/6);
		
		//Set params
		image.setLayoutParams(imageParams);
		title.setLayoutParams(titleParams);
		titolo_image.setLayoutParams(titleLayoutParams);
		bottoni.setLayoutParams(buttonsLayoutParam);
		playButton.setLayoutParams(buttonParams);
		optionsButton.setLayoutParams(buttonParams);
	
	}
}
