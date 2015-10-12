package android.listeners;

import com.emotiquiz.R;

import android.app.AlertDialog;
import android.content.Context;
import android.listeners.dialog.RipristinaImpostazioniListener;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * Listener dell'onClick relativo al bottone Ripristina in Options.
 * questo listener mostra l'alert dialog per il ripristino delle impostazioni
 * In caso positivo viene richiamato RipristinaImpostazioniListener che
 * ripristina effettivamente tutti i livelli.
 * @author dado
 *
 */
public class RipristinaListener implements OnClickListener {
	
	private Context context;

	public RipristinaListener(Context context) {
		super();
		this.context = context;
	}

	@Override
	public void onClick(View v) {
		
		AlertDialog.Builder ripristinaAlert = new AlertDialog.Builder(context);
		ripristinaAlert.setTitle("Ripristina Impostazioni");
		ripristinaAlert.setMessage("L'operazione e' irreversibile.\nVuoi davvero azzerare tutti i tuoi progressi?");
		ripristinaAlert.setPositiveButton("Ripristina", new RipristinaImpostazioniListener());
		ripristinaAlert.setNegativeButton("Annulla", null);
		ripristinaAlert.setIcon(R.drawable.icon);
		AlertDialog alert = ripristinaAlert.create();
		alert.show();
	}
}
