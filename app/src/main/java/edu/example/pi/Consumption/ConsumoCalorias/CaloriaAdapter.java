package edu.example.pi.Consumption.ConsumoCalorias;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import edu.example.pi.Consumption.ConsumoAgua.DeleteAgua;
import edu.example.pi.Consumption.ConsumoAgua.UpdateAgua;
import edu.example.pi.Consumption.ConsumptionsResponse;
import edu.example.pi.R;

public class CaloriaAdapter extends RecyclerView.Adapter<CaloriaAdapter.ViewHolder>{
    private List<ConsumptionsResponse> calList;

    public CaloriaAdapter(List<ConsumptionsResponse> calList) {
        this.calList = calList;
    }

    @NonNull
    @Override
    public CaloriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.consumos_salvos_recycle, parent, false);
        return new CaloriaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaloriaAdapter.ViewHolder holder, int position) {
        ConsumptionsResponse cal = calList.get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = sdf.format(cal.getCreatedAt());

        holder.txtquant.setText(String.valueOf(cal.getQuantidade()));
        holder.txtdata.setText(formattedDate);

        int quantidade = cal.getQuantidade();

        holder.btneditarcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.calorias_modal_editar);
                dialog.show();

                Button btneditcalmodal = dialog.findViewById(R.id.btnatualizarcal);
                EditText editquant = dialog.findViewById(R.id.editquant);
                ImageButton btncloseeditcal = dialog.findViewById(R.id.btncloseeditcal);

                editquant.setText(String.valueOf(cal.getQuantidade()));

                btneditcalmodal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String newQuantityText = editquant.getText().toString().trim();

                        try {
                            int newQuantity = Integer.parseInt(newQuantityText);

                            UpdateCaloria updateCaloria = new UpdateCaloria(v.getContext());
                            updateCaloria.onCaloriaUpdate(cal.get_id(), newQuantity);
                            dialog.dismiss(); // Close the dialog after successful update
                        } catch (NumberFormatException e) {
                            Toast.makeText(v.getContext(), "Digite um valor numérico válido para as calorias", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btncloseeditcal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


        holder.btnexcluircal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.calorias_modal_excluir);

                Button btnexcluircal = dialog.findViewById(R.id.btnexcluircal);
                TextView data = dialog.findViewById(R.id.textView26);
                data.setText("Deseja excluir a informação das: " + formattedDate);
                ImageButton btnclosedeletecal = dialog.findViewById(R.id.closeexcluircal);


                btnexcluircal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteCaloria deleteCaloria = new DeleteCaloria(v.getContext());
                        deleteCaloria.onCaloriaDelete(cal.get_id());
                    }
                });
                dialog.show();


                btnclosedeletecal.setOnClickListener(new View.OnClickListener() {
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
        return calList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtquant;
        TextView txtdata;
        ImageButton btnexcluircal;
        ImageButton btneditarcal;

        public ViewHolder(View itemView) {
            super(itemView);
            txtquant = itemView.findViewById(R.id.textviewquant);
            txtdata = itemView.findViewById(R.id.textviewhorario);
            btnexcluircal = itemView.findViewById(R.id.imageButton2);
            btneditarcal = itemView.findViewById(R.id.imageButton);

        }
    }
}
