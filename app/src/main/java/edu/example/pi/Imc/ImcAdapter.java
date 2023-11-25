package edu.example.pi.Imc;

import java.text.SimpleDateFormat;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.example.pi.R;
import edu.example.pi.Receitas.RecipeResponse;

public class ImcAdapter extends RecyclerView.Adapter<ImcAdapter.ViewHolder>{

    private List<ImcResponse> imcList;

    public ImcAdapter(List<ImcResponse> imcList) {
        this.imcList = imcList;
    }


    //    private List<Imc> lista = new ArrayList<>();
//    private Context context;
//    private LayoutInflater inflater;
//
//    public ImcAdapter(Context context, List<Imc> imcs) {
//        this.context = context;
//        this.inflater = LayoutInflater.from(context);
//        this.lista = imcs;
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_imc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImcResponse imc = imcList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(imc.getCreatedAt());

        holder.txtValorImc.setText(Double.valueOf(imc.getValor()).toString());
        holder.txtDataImc.setText(formattedDate);

    }


    @Override
    public int getItemCount() {
        return imcList.size();
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
