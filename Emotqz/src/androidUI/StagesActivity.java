package androidUI;

import gestori.GestoreInput;

import java.util.ArrayList;

import utilities_image.ElementsAdapter;
import utilities_image.ImageElement;
import android.app.Activity;
import android.listeners.StartLevelsListener;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.emotqz.R;

public class StagesActivity extends Activity {

	private GestoreInput gestoreInput = GestoreInput.getGestore();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stages);

		int categoria = getIntent().getIntExtra("CATEGORIE", 0);
		
		
		TextView textView = (TextView)findViewById(R.id.categoryNumber);
		textView.setText("Categoria: "+gestoreInput.getCategorie().get(categoria).getNome());
		
		ListView listView = (ListView) findViewById(R.id.categoryList);
	
		ArrayList<ImageElement> imageElements = new ArrayList<ImageElement>();
		
		for (int i = 0; i < gestoreInput.getCategorie().get(categoria).getStages().size(); i++) {
			
			if (gestoreInput.getCategorie().get(categoria).getStages().get(i).isBloccato()) {
				imageElements.add(new ImageElement("Stage" + (i + 1), R.drawable.stage_button_locked_shade));
			}else{
				imageElements.add(new ImageElement("Stage" + (i + 1), R.drawable.stage_button_shade));
			}		
		}
		
		ElementsAdapter adapter = new ElementsAdapter(this, R.layout.category_list, imageElements);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new StartLevelsListener(categoria));
	}
	
	@Override
	protected void onRestart() {
		recreate();
		super.onRestart();
	}
}