package db.utils;

import java.util.HashMap;

public class DbStrings {
	
	
	
	public static final String CATEGORIA = "categoria";
	public static final String STAGE = "stage";
	public static final String LIVELLO = "livello";
	public static final String HINT = "hint";
	
	public static final String TBL_LIVELLI = "livelli";
	public static final String TBL_HINTS = "hints";
	public static final String TBL_COINS = "tbl_coins";
	
	public static final String COINS = "coins";
	
	public  final	HashMap<String, String> MAPPACATEGORIE = new HashMap<String, String>() ;
	
	public DbStrings() {
		MAPPACATEGORIE.put("film", "Film");
		MAPPACATEGORIE.put("music", "Musica");
		MAPPACATEGORIE.put("serie", "Serie TV");
	}
	
	public HashMap<String, String> getMappaCategorie() {
		return MAPPACATEGORIE;
	}

}
