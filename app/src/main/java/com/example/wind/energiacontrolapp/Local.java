package com.example.wind.energiacontrolapp;

public class Local {
    private static int id = 0;
    private int idLocal;
    private String nombreLocal;
    private int horaInicio;
    private int minutoInicio;
    private int horaFinal;
    private int minutoFinal;
    private int potencia;
    private int consumo;

    public Local() {
    }

    public Local(String nombreLocal, int horaInicio, int minutoInicio, int horaFinal, int minutoFinal,int potencia,int consumo) {

        //RECORDAR AÑADIR CONSUMO Y POTENCIA
        this.nombreLocal = nombreLocal;
        this.horaInicio = horaInicio;
        this.minutoInicio = minutoInicio;
        this.horaFinal = horaFinal;
        this.minutoFinal = minutoFinal;
        this.potencia = potencia;
        this.consumo = consumo;
        idLocal = id;
        id++;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinutoInicio() {
        return minutoInicio;
    }

    public void setMinutoInicio(int minutoInicio) {
        this.minutoInicio = minutoInicio;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getMinutoFinal() {
        return minutoFinal;
    }

    public void setMinutoFinal(int minutoFinal) {
        this.minutoFinal = minutoFinal;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }
}
