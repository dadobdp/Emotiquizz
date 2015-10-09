package gestori;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javaUtils.Categoria;
import javaUtils.Livello;
import javaUtils.Stage;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import db.utils.DbManager;

public class GestoreInput {

	// as singleton
	private static GestoreInput gestore = new GestoreInput();

	public static GestoreInput getGestore() {
		return gestore;
	}

	private GestoreInput() {
	}

	// as class

	private int coins;
	
	private DbManager dbManager;

	@SuppressLint("UseSparseArrays")
	private HashMap<String, Integer> CatToInt = new HashMap<String, Integer>();

	@SuppressLint("UseSparseArrays")
	private HashMap<Integer, String> IntToCat = new HashMap<Integer, String>();

	private ArrayList<Categoria> categorie = new ArrayList<Categoria>();

	public int caricaStruttura(Context context) throws IOException {

		File folder = new File(Environment.getExternalStorageDirectory()
				+ "/categorie");
		if (!folder.exists()) {
			folder.mkdir();
		}

		int NDati = context.getAssets().list("Dati").length;
		String[] files=context.getAssets().list("Dati");
		AssetManager assetManager = context.getAssets();

		int count = 0;
		for (int i = 0; i < NDati; i++) {

			String fileName = files[i];

			if (fileName.endsWith(".txt")) {

				StringTokenizer tokenizer = new StringTokenizer(fileName);
				String type = tokenizer.nextToken(".");
				Categoria categoria = new Categoria(type);
				try {
					System.out.println(fileName);
					InputStream inputStream = assetManager.open("Dati/"
							+ fileName);
					InputStreamReader inputStreamReader = new InputStreamReader(
							inputStream);
					BufferedReader bufferedReader = new BufferedReader(
							inputStreamReader);
					Integer Nstage = Integer
							.parseInt(bufferedReader.readLine());
					for (int j = 0; j < Nstage; j++) {

						InputStream inputStreamStage = assetManager
								.open("Dati/stage" + (j + 1) + "." + type);
						Stage stage = caricaStage(inputStreamStage);
						categoria.aggiungiStage(stage);
					}

					bufferedReader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				categorie.add(categoria);
				CatToInt.put(categoria.getNome(), count);
				IntToCat.put(count, categoria.getNome());
				count++;
			}

		}

		return count;
	}

	private Stage caricaStage(InputStream inputStream) {

		Stage stage = new Stage();

		try {
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			while (bufferedReader.ready()) {

				String line = bufferedReader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line);

				String emoticonsString = tokenizer.nextToken("/");
				String soluzione = tokenizer.nextToken("/");
				String hintsString = tokenizer.nextToken("\n").substring(1);

				ArrayList<String> emoticonsCodes = codiciTokenizer(emoticonsString);
				ArrayList<String> hints = hintsTokenizer(hintsString);

				Livello livello = new Livello(emoticonsCodes, soluzione, hints);

				stage.aggiungiLivello(livello);
			}

			bufferedReader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stage;
	}

	private ArrayList<String> hintsTokenizer(String hintsString) {

		ArrayList<String> hints = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(hintsString);

		String string = "";

		while (tokenizer.hasMoreTokens()) {
			string = (tokenizer.nextToken("-"));
			hints.add(string);
		}

		return hints;

	}

	private ArrayList<String> codiciTokenizer(String ids) {

		StringTokenizer tokenizer = new StringTokenizer(ids);
		ArrayList<String> codici = new ArrayList<String>();

		String string = "";

		while (tokenizer.hasMoreTokens()) {
			string = (tokenizer.nextToken("-"));
			codici.add(string);
		}
		return codici;
	}

	public ArrayList<Categoria> getCategorie() {
		return categorie;
	}

	public void setStageBloccati() {
		for (int i = 0; i < categorie.size(); i++) {
			categorie.get(i).setStageBloccati();
		}
	}

	public HashMap<Integer, String> getIntToCat() {
		return IntToCat;
	}

	public HashMap<String, Integer> getCatToInt() {
		return CatToInt;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins += coins;
	}
	
	public DbManager getDbManager() {
		return dbManager;
	}

	public void setDbManager(DbManager dbManager) {
		this.dbManager = dbManager;
	}
	public void azzeraCoins(){
		this.coins=0;
	}
	
}
