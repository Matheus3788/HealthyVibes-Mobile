package edu.example.pi.Consumption.ConsumoAgua;

import android.app.Dialog;
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

    }



    @Override
    public int getItemCount() {
        return aguaList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtquant;
        TextView txtdata;
        ImageButton btnexcluirimc;
        ImageButton btneditarimc;

        public ViewHolder(View itemView) {
            super(itemView);
            txtquant = itemView.findViewById(R.id.textviewquant);
            txtdata = itemView.findViewById(R.id.textviewhorario);
            btnexcluirimc = itemView.findViewById(R.id.imageButton2);
            btneditarimc = itemView.findViewById(R.id.imageButton);

        }
    }

}
