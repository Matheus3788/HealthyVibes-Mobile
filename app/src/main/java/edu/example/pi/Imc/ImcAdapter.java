package edu.example.pi.Imc;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import edu.example.pi.R;
import edu.example.pi.Receitas.DeleteRecipe;
import edu.example.pi.Receitas.EditarReceitas;
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

        int altura = imc.getAltura();
        double peso = imc.getPeso();


        holder.btneditarimc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.imc_modal_editar);
                dialog.show();

                Button btneditimcmodal = dialog.findViewById(R.id.btneditimc);
                EditText editaltura = dialog.findViewById(R.id.editaltura);
                EditText editpeso = dialog.findViewById(R.id.editpeso);

                ImageButton btncloseedit = dialog.findViewById(R.id.btncloseeditimc);

                editaltura.setText(String.valueOf(imc.getAltura()));
                editpeso.setText(String.valueOf(imc.getPeso()));

                btneditimcmodal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int novaaltura = Integer.parseInt(editaltura.getText().toString());
                        double novopeso = Double.parseDouble(editpeso.getText().toString());

                        UpdateImc updateImc = new UpdateImc(v.getContext());
                        updateImc.onImcUpdate(imc.get_id(), novaaltura, novopeso);

                    }
                    });

                btncloseedit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });




                }


        });

        holder.btnexcluirimc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.imc_modal_excluir);
                Button btnexcluirimc = dialog.findViewById(R.id.btnexcluirimc);

                TextView data = dialog.findViewById(R.id.textView26);
                data.setText("Deseja excluir a informação do dia " + formattedDate);


                btnexcluirimc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DeleteImc deleteImc = new DeleteImc(v.getContext());
                        deleteImc.onImcDelete(imc.get_id());


                    }
                });
                dialog.show();
            }
        });

    }



    @Override
    public int getItemCount() {
        return imcList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtValorImc;
        TextView txtDataImc;
        Button btnexcluirimc;

        Button btneditarimc;

        public ViewHolder(View itemView) {
            super(itemView);
            txtValorImc = itemView.findViewById(R.id.txtImcValor);
            txtDataImc = itemView.findViewById(R.id.txtImcData);
            btnexcluirimc = itemView.findViewById(R.id.btnImcExcluir);
            btneditarimc = itemView.findViewById(R.id.btnImcEditar);

        }
    }
}
