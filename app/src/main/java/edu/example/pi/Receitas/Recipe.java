package edu.example.pi.Receitas;

import java.util.List;

public class Recipe {
    private String savedID;
    private String periodoRef;
    private String titulo;
    private List<Ingredient> ingredientes;
    private int calorias;
    private int carboidratos;
    private int gordura;
    private int proteina;
    private String modoDePreparo;

    // Construtor
    public Recipe(String savedID, String periodoRef, String titulo, List<Ingredient> ingredientes,
                  int calorias, int carboidratos, int gordura, int proteina, String modoDePreparo) {
        this.savedID = savedID;
        this.periodoRef = periodoRef;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.calorias = calorias;
        this.carboidratos = carboidratos;
        this.gordura = gordura;
        this.proteina = proteina;
        this.modoDePreparo = modoDePreparo;
    }

    // Getters
    public String getSavedID() {
        return savedID;
    }

    public String getPeriodoRef() {
        return periodoRef;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Ingredient> getIngredientes() {
        return ingredientes;
    }

    public int getCalorias() {
        return calorias;
    }

    public int getCarboidratos() {
        return carboidratos;
    }

    public int getGordura() {
        return gordura;
    }

    public int getProteina() {
        return proteina;
    }

    public String getModoDePreparo() {
        return modoDePreparo;
    }
}
