package edu.example.pi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BarraAtalhosFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState){
//        super.onCreateView(inflater, container, savedInstanceState);
//        return inflater.inflate(R.layout.barra_atalhos, container, false);
        View view = inflater.inflate(R.layout.barra_atalhos, container, false);

        ImageButton btnexercicio = view.findViewById(R.id.navBtnExercicios);
        ImageButton btnhome = view.findViewById(R.id.navBtnHome);
        btnexercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Exercicio.class);
                startActivity(intent);
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
