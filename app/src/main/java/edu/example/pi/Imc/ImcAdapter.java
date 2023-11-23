package edu.example.pi.Imc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.example.pi.R;

public class ImcAdapter extends RecyclerView.Adapter<ImcAdapter.ViewHolder>{

    private List<Imc> lista = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public ImcAdapter(Context context, List<Imc> imcs) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.lista = imcs;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item_imc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Imc imc = lista.get(position);


    }


    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtValorImc;
        TextView txtDataImc;

        public ViewHolder(View itemView) {
            super(itemView);
            txtValorImc = itemView.findViewById(R.id.txtImcValor);
            txtDataImc = itemView.findViewById(R.id.txtImcData);

        }
    }
}
