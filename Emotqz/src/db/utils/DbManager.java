package db.utils;

import javaUtils.Livello;
import gestori.GestoreInput;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Classe che si occupa della comunicazione con il database
 * Gestisce lettura e scrittura di:
 * 1) livelli completati
 * 2) hints comprati
 * 3) goins guadagnati
 * 
 * Permette inoltre di azzerare i progressi
 * @author macbook
 *
 */
public class DbManager {

	private DbHelper dbHelper;
	
	private GestoreInput gestoreInput = GestoreInput.getGestore();
	
	public DbManager(Context context) {
		dbHelper = new DbHelper(context);
	}
	
	public void caricaDb(){
		
		caricaSalvataggi();
		caricaHints();
		caricaCoins();
	}
	
	private void caricaSalvataggi(){
		
		Cursor cursor = getTable(DbStrings.TBL_LIVELLI);
		if (cursor .moveToFirst()) {
			
            while (cursor.isAfterLast() == false) {
            	
                String cat = cursor.getString(cursor.getColumnIndex(DbStrings.CATEGORIA));
                int stg = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbStrings.STAGE)));
                int lv = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbStrings.LIVELLO)));
                
                Livello liv = gestoreInput.getCategorie().get(gestoreInput.getCatToInt().get(cat)).getStages().get(stg).getLivelli().get(lv);
				liv.setCompletato(true);

                cursor.moveToNext();
            }
        }
	}
	
	private void caricaHints(){
		
		Cursor cursor = getTable(DbStrings.TBL_HINTS);
		if (cursor .moveToFirst()) {
			
            while (cursor.isAfterLast() == false) {
            	
                String cat = cursor.getString(cursor.getColumnIndex(DbStrings.CATEGORIA));
                int stg = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbStrings.STAGE)));
                int lv = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbStrings.LIVELLO)));
                int hint = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbStrings.HINT)));
                
                Livello livello = gestoreInput.getCategorie()
						.get(gestoreInput.getCatToInt().get(cat))
						.getStages().get(stg).getLivelli().get(lv);
				livello.getHintSbloccati()[hint] = 1;
 
                cursor.moveToNext();
            }
        }
	}
	
	private void caricaCoins(){
		
		int coins = 0;
		
		Cursor cursor = getTable(DbStrings.TBL_COINS);
	
		if(cursor .moveToFirst()){

				//coins = Integer.parseInt(cursor.getString(cursor.getColumnIndex("")));
				coins = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DbStrings.COINS)));
				Log.i("INFO", "Coins: "+coins);
			
		}		
		gestoreInput.setCoins(coins);	
	}

	public void saveLevelCompleted(int categoria, int stage, int lv){
		
		String cat = gestoreInput.getIntToCat().get(categoria);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		
		cv.put(DbStrings.CATEGORIA, cat);
		cv.put(DbStrings.STAGE, stage);
		cv.put(DbStrings.LIVELLO, lv);
		
		try {
			db.insert(DbStrings.TBL_LIVELLI, null, cv);
			Log.i("INFO", "Salvato completamento: "+cat +" "+stage+" "+lv);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveHintBought(int categoria, int stage, int lv, int hint){
		
		String cat = gestoreInput.getIntToCat().get(categoria);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
				
		ContentValues cv = new ContentValues();
		
		cv.put(DbStrings.CATEGORIA, cat);
		cv.put(DbStrings.STAGE, stage);
		cv.put(DbStrings.LIVELLO, lv);
		cv.put(DbStrings.HINT, hint);
		
		try {
			db.insert(DbStrings.TBL_HINTS, null, cv);
			
			Log.i("INFO", "Hint comprato: "+cat +" "+stage+" "+lv+" "+hint);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void saveCoins(int coins){
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues cv = new ContentValues();
		
		cv.put(DbStrings.COINS, coins);
		cv.put("_id", 1);
		
		long ins = db.insert(DbStrings.TBL_COINS,null,cv);

		if(ins == -1){
			int n = db.update(DbStrings.TBL_COINS,cv,null,null);
			Log.i("INFO", "Colonne: "+n);
		}
			
		Log.i("INFO", "Coins totali: "+coins);
	}
	
	public Cursor getTable(String tbl_name){
		
		Cursor cursor = null;
		
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		cursor = db.query(tbl_name, null, null, null, null, null, null);
		
		return cursor;
	}
	
	public void deleteAll(){
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		db.delete(DbStrings.TBL_LIVELLI, null, null);
		db.delete(DbStrings.TBL_HINTS, null, null);
		db.delete(DbStrings.TBL_COINS, null, null);
		
		
		
	}
}
