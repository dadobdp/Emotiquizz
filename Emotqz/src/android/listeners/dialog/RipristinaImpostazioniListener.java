package android.listeners.dialog;

import gestori.GestoreInput;
import javaUtils.Livello;
import android.content.DialogInterface;
/**
 * Ripristina effettivamente tutti i livelli ed elimina il file salvataggi
 * @author macbook
 *
 */
public class RipristinaImpostazioniListener implements DialogInterface.OnClickListener {
	

	private GestoreInput gestoreInput = GestoreInput.getGestore();
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		
		//gestoreSalvataggi.eliminaSalvataggi();	
		gestoreInput.getDbManager().deleteAll();
		
		ripristinaLivelli();
		
		gestoreInput.setStageBloccati();

	}
	
	private void ripristinaLivelli() {
		gestoreInput.azzeraCoins();
		for (int i = 0; i < gestoreInput.getCategorie().size(); i++) {
			int stages = gestoreInput.getCategorie().get(i).getStages().size();
			for (int j = 0; j < stages; j++) {
				int livelli = gestoreInput.getCategorie().get(i).getStages().get(j).getLivelli().size();
				for (int k = 0; k < livelli; k++) {
					Livello livello = gestoreInput.getCategorie().get(i).getStages().get(j).getLivello(k);
					livello.setCompletato(false);						
					livello.getHintSbloccati()[0]=0;
					livello.getHintSbloccati()[1]=0;
				}				
			}
		}
	}

}
