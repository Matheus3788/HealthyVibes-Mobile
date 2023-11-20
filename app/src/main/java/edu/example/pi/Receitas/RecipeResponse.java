package edu.example.pi.Receitas;

import java.util.List;

public class RecipeResponse {
    private String _id;
    private String message;

    private List<Ingredient> ingredientes;
    private int calorias;
    private int carboidratos;
    private int gordura;
    private int proteina;
    private String modoDePreparo;

    private String titulo;  // Substitua com os campos reais necessários
    private String periodoRef;  // Substitua com os campos reais necessários

    private  String recipe;


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


    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPeriodoRef() {
        return periodoRef;
    }

    public String get_ID() {
        return _id;
    }

    public String getMessage() {
        return message;
    }



}
