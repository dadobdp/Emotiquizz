package utilities_image;

import java.util.List;

import com.emotiquiz.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class ElementsAdapter extends ArrayAdapter<ImageElement>{
	
	private Context context;

	public ElementsAdapter(Context context, int resource,List<ImageElement> imageElement) {
		super(context, resource, imageElement);
		this.context=context;
	}
	
	/*private view holder class*/
    private class ViewHolder {
        TextView testo;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	
    	ViewHolder holder = null;

        ImageElement imageElement = getItem(position);
         
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.category_list, null);
            holder = new ViewHolder();
            holder.testo = (TextView) convertView.findViewById(R.id.listElement);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
                 
        holder.testo.setText(imageElement.getImmagine());
        holder.testo.setBackgroundResource(imageElement.getId());
         
        return convertView;
    }
	


}
