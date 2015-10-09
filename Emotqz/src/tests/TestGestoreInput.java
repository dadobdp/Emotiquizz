package tests;

import gestori.GestoreInput;
import javaUtils.Categoria;
import javaUtils.Livello;

/**
 * 
 * @author macbookpro Testa il caricamento della struttura dati
 *
 *         ATTENZIONE!! sostituire in GestoreInput la cartella folder (prova)
 *         all' external storage del dispositivo!!!!! chiedere prima a
 *         dadon!!!!!
 */
public class TestGestoreInput {

	public static void main(String[] args) {

		GestoreInput gestoreInput = GestoreInput.getGestore();

		//gestoreInput.caricaStruttura();

		System.out.println("Totale categorie: "
				+ gestoreInput.getCategorie().size());

		for (int i = 0; i < gestoreInput.getCategorie().size(); i++) {

			Categoria categoria = gestoreInput.getCategorie().get(i);
			categoria.setStageBloccati();

			System.out.println("Categoria " + categoria.getNome());

			for (int j = 0; j < categoria.getStages().size(); j++) {

				System.out.println("stage" + (j + 1) + " "
						+ categoria.getStages().get(j).isBloccato());

				for (int j2 = 0; j2 < categoria.getStages().get(j).getLivelli()
						.size(); j2++) {

					Livello livello = categoria.getStages().get(j).getLivelli()
							.get(j2);
					System.out.println(livello.getEmoticons().toString() + " "
							+ livello.getSoluzione() + " "
							+ livello.isCompletato());

				}

			}

		}

	}

}
