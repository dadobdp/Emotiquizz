package javaUtils;

import java.util.ArrayList;

public class Categoria {

	private String nome;
	private ArrayList<Stage> stages = new ArrayList<Stage>();

	public Categoria(String nome) {
		super();
		this.nome = nome;
	}

	public void aggiungiStage(Stage stage) {
		stages.add(stage);
	}

	public void setStageBloccati() {
		stages.get(0).setBloccato(false);

		for (int i = 1; i < stages.size(); i++) {

			int numLivStagePrecedente = stages.get(i - 1).getLivelli().size();
			int completatiStagePrecedente = stages.get(i - 1)
					.getLivelliCompletati();

			float percentuale = (float) completatiStagePrecedente
					/ (float) numLivStagePrecedente;

			if (percentuale > 0.6) {
				stages.get(i).setBloccato(false);

			} else {
				stages.get(i).setBloccato(true);
			}
		}
	}

	public ArrayList<Stage> getStages() {
		return stages;
	}

	public String getNome() {
		return nome;
	}
}
