package androidUI;

import gestori.GestoreInput;

import java.io.IOException;

import com.emotiquiz.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import db.utils.DbManager;

public class LoadingActivity extends Activity {

	private GestoreInput gestoreInput = GestoreInput.getGestore();
	//private GestoreSalvataggi gestoreSalvataggi = GestoreSalvataggi.getGestore();
	private int stoppingTime = 1;

	private DbManager manager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		manager = new DbManager(this);
		gestoreInput.setDbManager(manager);

//		ImageView imageView = (ImageView) findViewById(R.id.carica);
//		imageView.setBackgroundResource(R.drawable.caricamento);
//		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
//				.getBackground();
//		animationDrawable.start();

		new Loading(this.getApplicationContext()).execute();

	}

	private class Loading extends AsyncTask<Void, Void, Void> {

		private Context context;

		public Loading(Context context) {
			this.context = context;
		}

		@Override
		protected Void doInBackground(Void... params) {

			boolean bloccato = true;

			if (gestoreInput.getCategorie().size() == 0) {
				try {
					gestoreInput.caricaStruttura(context);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//gestoreInput.setCoins(gestoreSalvataggi.getCoins());
			//gestoreInput.setCoins(gestoreSalvataggi.getDbCoins());

			//carica salvataggi, hints e coins
			manager.caricaDb();
			
			
			// Legge dal file salvataggi
			//gestoreSalvataggi.caricaSalvataggi();
			//gestoreSalvataggi.caricaDb();
			//gestoreSalvataggi.caricaHints();
			//gestoreSalvataggi.caricaDbHints();
			

			gestoreInput.setStageBloccati();

			long tStart = System.currentTimeMillis();
			while (bloccato) {
				long tEnd = System.currentTimeMillis();
				long tDelta = tEnd - tStart;
				double elapsedSeconds = tDelta / 1000.0;
				if (elapsedSeconds > stoppingTime) {
					bloccato = false;
				}

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}

	}

}
