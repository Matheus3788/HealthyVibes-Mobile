package edu.example.pi.Receitas;

import java.util.List;

public class RecipeRequest {
    private String periodoRef;
    private String titulo;
    private List<Ingredient> ingredientes;
    private int calorias;
    private int carboidratos;
    private int gordura;
    private int proteina;
    private String modoDePreparo;


    public RecipeRequest(String periodoRef, String titulo, List<Ingredient> ingredientes, int calorias, int carboidratos, int gordura, int proteina, String modoDePreparo) {
        this.periodoRef = periodoRef;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.calorias = calorias;
        this.carboidratos = carboidratos;
        this.gordura = gordura;
        this.proteina = proteina;
        this.modoDePreparo = modoDePreparo;
    }
}
