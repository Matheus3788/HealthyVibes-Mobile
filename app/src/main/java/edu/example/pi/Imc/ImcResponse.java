package edu.example.pi.Imc;

import java.sql.Timestamp;


public class ImcResponse {
    private String id;
    private double valor;
    private double peso;
    private double altura;
    private Timestamp timestamp;


    public ImcResponse(double valor, Timestamp timestamp) {
        this.valor = valor;
        this.timestamp = timestamp;
    }

    public ImcResponse(String id, double valor, double peso, double altura, Timestamp timestamp)
 {
        this.id = id;
        this.valor = valor;
        this.peso = peso;
        this.altura = altura;
        this.timestamp = timestamp;
    }



    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}

