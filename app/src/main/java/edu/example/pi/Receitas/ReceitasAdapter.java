package edu.example.pi.Receitas;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.example.pi.R;

public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ViewHolder>{

    private List<RecipeResponse> recipeList;

    public ReceitasAdapter(List<RecipeResponse> recipeList) {
        this.recipeList = recipeList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receitas_salvas_recycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeResponse recipe = recipeList.get(position);
        Log.i("ID", recipe.get_ID());
        holder.nomeTextView.setText(recipe.getTitulo());  // Substitua com o método real para obter o nome
        holder.periodoTextView.setText(recipe.getPeriodoRef());  // Substitua com o método real para obter o período
    }


    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeTextView;
        TextView periodoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.textViewNome);  // Substitua com o ID real do TextView para o nome
            periodoTextView = itemView.findViewById(R.id.textViewPeriodo);  // Substitua com o ID real do TextView para o período
        }
    }

    public void setRecipeList(List<RecipeResponse> recipeList) {
        this.recipeList = recipeList;
    }

}
