package test.berg.cardlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Berg on 24/09/2017.
 */

public class ViewAdapter extends RecyclerView.ViewHolder {
    final TextView nome;
    final TextView minutos;
    final Button edit;
    //inicialização de elementos
    public ViewAdapter(View view)
    {
        super(view);
        nome = (TextView)view.findViewById(R.id.nome);
        minutos = (TextView)view.findViewById(R.id.minutos);
        edit = (Button)view.findViewById(R.id.edit);


    }



}
