package javaUtils;

import java.util.ArrayList;

public class Livello {

	private ArrayList<String> emoticons = new ArrayList<String>();
	private ArrayList<String> hints = new ArrayList<String>();
	private int[] hintSbloccati=new int[2];
	private String soluzione;
	private boolean completato = false;

	public Livello(ArrayList<String> emoticons, String soluzione, ArrayList<String> hints) {
		super();
		this.emoticons = emoticons;
		this.soluzione = soluzione;
		this.hints = hints;
	}
	

	public int[] getHintSbloccati() {
		return hintSbloccati;
	}


	public void setHintSbloccati(int hintSbloccato) {
		getHintSbloccati()[hintSbloccato]=1;
	}


	public ArrayList<String> getEmoticons() {
		return emoticons;
	}

	public boolean isCompletato() {
		return completato;
	}

	public void setCompletato(boolean completato) {
		this.completato = completato;
	}

	public String getSoluzione() {
		return soluzione;
	}

	public ArrayList<String> getHints() {
		return hints;
	}
	
	
}
