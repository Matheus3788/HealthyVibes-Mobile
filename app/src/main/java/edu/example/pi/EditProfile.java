package edu.example.pi;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {

    Button btnatualizar;
    Button btnexcliur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_layout);

        btnexcliur = findViewById(R.id.btnexcluir);

        btnexcliur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modalconfirmacao();
            }
        });

    }

    void  modalconfirmacao(){
        final Dialog dialog = new Dialog(EditProfile.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.modal_confirmar_exclusao);
        dialog.show();
    }




}
