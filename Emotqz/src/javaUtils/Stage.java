package javaUtils;

import java.util.ArrayList;

public class Stage {

	private ArrayList<Livello> livelli = new ArrayList<Livello>();
	private boolean bloccato;
	private boolean completato = false;
	private int livelliCompletati = 0;

	/**
	 * adds a level in the stage
	 * 
	 * @param livello
	 */
	public void aggiungiLivello(Livello livello) {
		livelli.add(livello);
	}

	public Livello getLivello(int n) {
		return livelli.get(n);
	}

	public ArrayList<Livello> getLivelli() {
		return livelli;
	}

	public int getLivelliCompletati() {
		livelliCompletati = 0;
		for (int i = 0; i < livelli.size(); i++) {
			if (livelli.get(i).isCompletato()) {
				livelliCompletati++;
			}
		}
		return livelliCompletati;
	}

	public boolean isBloccato() {
		return bloccato;
	}

	public void setBloccato(boolean bloccato) {
		this.bloccato = bloccato;
	}
	
	public boolean isCompletato() {
		return completato;
	}
	public void setCompletato(boolean completato) {
		this.completato = completato;
	}
	
	
}
