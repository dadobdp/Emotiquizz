package gestori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javaUtils.Livello;
import android.database.Cursor;
import android.os.Environment;
import android.util.Log;
import db.utils.DbStrings;

public class GestoreSalvataggi {

	// as singleton
	private static GestoreSalvataggi gestore = new GestoreSalvataggi();

	public static GestoreSalvataggi getGestore() {
		return gestore;
	}

	private GestoreSalvataggi() {
	}

	private String path = Environment.getExternalStorageDirectory()
			+ "/categorie/";
	private GestoreInput gestoreInput = GestoreInput.getGestore();

	
	// nuovo metodo carica salvataggi che setta i livelli completati leggendo il db

	
	
	



}
