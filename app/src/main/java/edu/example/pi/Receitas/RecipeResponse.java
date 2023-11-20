package edu.example.pi.Receitas;

public class RecipeResponse {
    private String _id;
    private String message;

    private String titulo;  // Substitua com os campos reais necessários
    private String periodoRef;  // Substitua com os campos reais necessários

    private  String recipe;

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
