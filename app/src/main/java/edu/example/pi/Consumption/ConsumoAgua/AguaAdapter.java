package edu.example.pi.Consumption.ConsumoAgua;

import android.app.Dialog;
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

import java.text.SimpleDateFormat;
import java.util.List;

import edu.example.pi.Consumption.ConsumptionsResponse;
import edu.example.pi.Imc.DeleteImc;
import edu.example.pi.Imc.ImcAdapter;
import edu.example.pi.Imc.ImcResponse;
import edu.example.pi.Imc.UpdateImc;
import edu.example.pi.R;


public class AguaAdapter extends RecyclerView.Adapter<AguaAdapter.ViewHolder> {

    private List<ConsumptionsResponse> aguaList;

    public AguaAdapter(List<ConsumptionsResponse> aguaList) {
        this.aguaList = aguaList;
    }

    @NonNull
    @Override
    public AguaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumos_salvos_recycle, parent, false);
        return new AguaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AguaAdapter.ViewHolder holder, int position) {
        ConsumptionsResponse agua = aguaList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = sdf.format(agua.getCreatedAt());

        holder.txtquant.setText(String.valueOf(agua.getQuantidade()));
        holder.txtdata.setText(formattedDate);

        int quantidade = agua.getQuantidade();

        holder.btneditaragua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.agua_modal_editar);
                dialog.show();

                Button btneditaguamodal = dialog.findViewById(R.id.btnatualizaragua);
                EditText editquant = dialog.findViewById(R.id.editquant);

                editquant.setText(String.valueOf(agua.getQuantidade()));

                btneditaguamodal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int novaquant = Integer.parseInt(editquant.getText().toString());

                        UpdateAgua updateAgua = new UpdateAgua(v.getContext());
                        updateAgua.onAguaUpdate(agua.get_id(), novaquant);

                    }
                });
            }
        });

        holder.btnexcluiragua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.agua_modal_excluir);
                Button btnexcluiragua = dialog.findViewById(R.id.btnexcluiragua);
                TextView data = dialog.findViewById(R.id.textView26);
                data.setText("Deseja excluir a informação das: " + formattedDate);

                ImageButton btncloseeditagua = dialog.findViewById(R.id.btncloseeditagua);

                btnexcluiragua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteAgua deleteAgua = new DeleteAgua(v.getContext());
                        deleteAgua.onAguaDelete(agua.get_id());

                    }
                });


                dialog.show();

                btncloseeditagua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }



    @Override
    public int getItemCount() {
        return aguaList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtquant;
        TextView txtdata;
        ImageButton btnexcluiragua;
        ImageButton btneditaragua;

        public ViewHolder(View itemView) {
            super(itemView);
            txtquant = itemView.findViewById(R.id.textviewquant);
            txtdata = itemView.findViewById(R.id.textviewhorario);
            btnexcluiragua = itemView.findViewById(R.id.imageButton2);
            btneditaragua = itemView.findViewById(R.id.imageButton);

        }
    }

}
