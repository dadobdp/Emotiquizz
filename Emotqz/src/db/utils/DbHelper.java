package db.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe che dà struttura al database
 * @author macbook
 *
 */
public class DbHelper extends SQLiteOpenHelper {
	
	private static final String DBNAME = "myDb";
	
	public DbHelper(Context context) {
		super(context,DBNAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//crea la tabella per i livelli completati
		String q1 = "CREATE TABLE "+DbStrings.TBL_LIVELLI+
				" ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				DbStrings.CATEGORIA+" TEXT,"+
				DbStrings.STAGE+" INTEGER,"+
				DbStrings.LIVELLO+" INTEGER)";
		
		//crea la tabella per gli hints comprati
		String q2 = "CREATE TABLE "+DbStrings.TBL_HINTS+
				" ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				DbStrings.CATEGORIA+" TEXT,"+
				DbStrings.STAGE+" INTEGER,"+
				DbStrings.LIVELLO+" INTEGER,"+
				DbStrings.HINT+" INTEGER)";
	
		//crea la tabella per i coins
		String q3 = "CREATE TABLE "+DbStrings.TBL_COINS+
				" ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
				DbStrings.COINS+" INTEGER)";
		
		db.execSQL(q1);
		db.execSQL(q2);
		db.execSQL(q3);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	

}
