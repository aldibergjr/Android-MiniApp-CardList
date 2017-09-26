package test.berg.cardlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Berg on 24/09/2017.
 */

public class Adaptador extends RecyclerView.Adapter {
    private List<Exercicios> exercicios;
    private Context context;
    //construtor
    public Adaptador(Context context,List<Exercicios> exercicios){
        this.exercicios = exercicios;
        this.context = context;

    }
    //seleção de elemento layout
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_info_exercicio,parent,false);
            ViewAdapter holder = new ViewAdapter(view);
            return holder;
        }
    //preenchimento dos elementos da layout;
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewAdapter holder = (ViewAdapter) viewHolder;

        Exercicios exercicio  = exercicios.get(position) ;

        holder.nome.setText(exercicio.getNomeatividade());
        holder.minutos.setText(exercicio.getMinutos());
    }

    @Override
    public int getItemCount() {
        return exercicios.size();
    }

}
