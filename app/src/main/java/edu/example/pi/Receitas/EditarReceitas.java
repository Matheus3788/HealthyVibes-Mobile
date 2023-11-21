package edu.example.pi.Receitas;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import edu.example.pi.R;

public class EditarReceitas extends AppCompatActivity {
    EditText editnomeedit;
    EditText editdificuldadeedit;
    EditText editporcoesedit;
    EditText editperiodoedit;
    EditText editpriningredienteedit, editsegingredienteedit, editteringredienteedit, editquartingredienteedit;
    EditText editcaloriasedit, editcarboidratosedit, editproteinaedit, editgorduraedit;
    EditText editmododepreparoedit;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receitas_editar);

        editnomeedit = findViewById(R.id.edittextituloedit);
        editdificuldadeedit = findViewById(R.id.edittextdificuldadeedit);
        editporcoesedit = findViewById(R.id.edittextporcoesedit);
        editperiodoedit = findViewById(R.id.edittextperiodoedit);
        editpriningredienteedit = findViewById(R.id.edittextingredpriedit);
        editsegingredienteedit = findViewById(R.id.edittextingredsegedit);
        editteringredienteedit = findViewById(R.id.edittextingredteredit);
        editquartingredienteedit = findViewById(R.id.edittextingredquaredit);
        editcaloriasedit = findViewById(R.id.edittextcaloriasedit);
        editcarboidratosedit = findViewById(R.id.edittextcarboedit);
        editproteinaedit = findViewById(R.id.edittextproteinaedit);
        editgorduraedit = findViewById(R.id.edittextgorduraedit);
        editmododepreparoedit = findViewById(R.id.edittextpreparoedit);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Obtenha os valores dos extras
            String titulo = extras.getString("titulo", "");
            String PeriodoRef = extras.getString("PeriodoRef", "");
            String ingredientesJson = extras.getString("ingredientes", "[]");
            int calorias = extras.getInt("calorias", 0);
            int carboidratos = extras.getInt("carboidratos", 0);
            int proteina = extras.getInt("proteina", 0);
            int gordura = extras.getInt("gordura", 0);
            String mododepreparo = extras.getString("mododepreparo", "");

            editnomeedit.setText(titulo);
            editperiodoedit.setText(PeriodoRef);

            Type listType = new TypeToken<List<Ingredient>>(){}.getType();
            List<Ingredient> ingredientes = new Gson().fromJson(ingredientesJson, listType);

            editpriningredienteedit.setText(ingredientes.get(0).getNome());
            editsegingredienteedit.setText(ingredientes.get(1).getNome());
            editteringredienteedit.setText(ingredientes.get(2).getNome());
            editquartingredienteedit.setText(ingredientes.get(3).getNome());

            editcaloriasedit.setText(String.valueOf(calorias));
            editcarboidratosedit.setText(String.valueOf(carboidratos));
            editproteinaedit.setText(String.valueOf(proteina));
            editgorduraedit.setText(String.valueOf(gordura));

            editmododepreparoedit.setText(mododepreparo);

            // Continue configurando os outros campos conforme necessário
        }


    }
}
