package edu.example.pi.Consumption;

import java.util.Date;

public class ConsumptionsRequest {

    private int quantidade;

    private String tipoConsumo;

    private String belongDate;

    public ConsumptionsRequest(int quantidade, String tipoConsumo, String belongDate) {
        this.quantidade = quantidade;
        this.tipoConsumo = tipoConsumo;
        this.belongDate = belongDate;
    }

    public String getBelongDate() {
        return belongDate;
    }

    public void setBelongDate(String belongDate) {
        this.belongDate = belongDate;
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
