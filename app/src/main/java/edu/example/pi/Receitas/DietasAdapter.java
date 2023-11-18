package edu.example.pi.Receitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.example.pi.R;

public class DietasAdapter extends RecyclerView.Adapter<DietasAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Dieta> ListaDietas = new ArrayList<>();
    private LayoutInflater inflater;

    public DietasAdapter(Context context, ArrayList<Dieta> dietas) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.ListaDietas = dietas;
    }


    @NonNull
    @Override
    public DietasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_receitas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dieta d1 = ListaDietas.get(position);
        holder.txtDietaNome.setText(d1.getNome());



    }

    @Override
    public int getItemCount() {
        return ListaDietas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDietaNome;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDietaNome = itemView.findViewById(R.id.txtDietaNome);

        }
    }
}
