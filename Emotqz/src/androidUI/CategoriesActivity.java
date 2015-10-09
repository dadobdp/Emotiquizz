package androidUI;

import gestori.GestoreInput;

import java.util.ArrayList;

import utilities_image.ElementsAdapter;
import utilities_image.ImageElement;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.listeners.StartStagesListener;
import android.os.Bundle;
import android.widget.ListView;

import com.emotqz.R;

@SuppressLint("DefaultLocale")
public class CategoriesActivity extends Activity {

	private GestoreInput gestoreInput = GestoreInput.getGestore();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_categories);

		ListView listView = (ListView) findViewById(R.id.categoryList);
		ArrayList<ImageElement> imageElements = new ArrayList<ImageElement>();
		
		for (int i = 0; i < gestoreInput.getCategorie().size(); i++) {
			
			String nomeCat = gestoreInput.getCategorie().get(i).getNome();
			
			if (nomeCat.equalsIgnoreCase("film")) {
				ImageElement element = new ImageElement("Film", R.drawable.film_button_shade);
				imageElements.add(element);
			}else if(nomeCat.equalsIgnoreCase("music")){
				ImageElement element = new ImageElement("Musica", R.drawable.music_button_shade);
				imageElements.add(element);
			}else if(nomeCat.equalsIgnoreCase("serie")){
				ImageElement element = new ImageElement("Serie Tv", R.drawable.serie_button_shade);
				imageElements.add(element);
			}			
		}
		
		ElementsAdapter adapter = new ElementsAdapter(this, R.layout.category_list, imageElements);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new StartStagesListener());
		
	}

	@Override
	protected void onRestart() {
		recreate();
		super.onRestart();
	}
}