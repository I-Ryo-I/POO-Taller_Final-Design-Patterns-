package com.mycompany.rpgmanager;

public class Arquero extends Personaje {
    public Arquero(String nombre) { super(nombre); }

    @Override
    protected int saludBase() { return 110; }

    @Override
    public String atacar() {
        return nombre + " dispara flecha 🏹";
    }
}