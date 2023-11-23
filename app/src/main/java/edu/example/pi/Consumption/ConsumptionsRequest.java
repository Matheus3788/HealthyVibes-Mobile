package edu.example.pi.Consumption;

public class ConsumptionsRequest {

    private int quantidade;

    private String tipoConsumo;

    public  ConsumptionsRequest(int quantidade, String tipoConsumo){
        this.quantidade = quantidade;
        this.tipoConsumo = tipoConsumo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }
}
