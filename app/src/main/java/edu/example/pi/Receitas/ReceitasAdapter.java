package edu.example.pi.Receitas;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import edu.example.pi.EditProfile;
import edu.example.pi.R;

public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ViewHolder> {

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

//    @Override
//    public void onRecipeDelete(String recipeId) {
//        // Aqui você pode chamar a outra classe passando o ID da receita e o token
//        // Exemplo: OtherClass.deleteRecipe(recipeId, userToken);
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecipeResponse recipe = recipeList.get(position);
        Log.i("ID", recipe.get_ID());
        holder.nomeTextView.setText(recipe.getTitulo());  // Substitua com o método real para obter o nome
        holder.periodoTextView.setText(recipe.getPeriodoRef());  // Substitua com o método real para obter o período

        holder.btnexclui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.receitas_modal_excluir);


                Button btndeletedef = dialog.findViewById(R.id.btnexcluirreceita);

                btndeletedef.setOnClickListener(e -> {

                    DeleteRecipe deleteRecipe = new DeleteRecipe(v.getContext());
                    deleteRecipe.onRecipeDelete(recipe.get_ID());
                });

                dialog.show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomeTextView;
        TextView periodoTextView;
        ImageButton btnexclui;
        ImageButton btnedit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.textViewNome);  // Substitua com o ID real do TextView para o nome
            periodoTextView = itemView.findViewById(R.id.textViewPeriodo);  // Substitua com o ID real do TextView para o período
            btnexclui = itemView.findViewById(R.id.imagebuttondelete);
            btnedit = itemView.findViewById(R.id.imagebuttonedit);

        }
    }

    public void setRecipeList(List<RecipeResponse> recipeList) {
        this.recipeList = recipeList;
    }

}
