package edu.example.pi.Consumption;

import java.sql.Timestamp;

public class ConsumptionsResponse {

    private String _id;
    private int quantidade;
    private String tipoConsumo;

    private Timestamp belongDate;

    private Timestamp createdAt;

    private  Timestamp updateAt;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public Timestamp getBelongDate() {
        return belongDate;
    }

    public void setBelongDate(Timestamp belongDate) {
        this.belongDate = belongDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }
}
