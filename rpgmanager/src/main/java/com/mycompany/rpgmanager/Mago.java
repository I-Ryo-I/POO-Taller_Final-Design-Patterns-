package com.mycompany.rpgmanager;

public class Mago extends Personaje {
    public Mago(String nombre) { super(nombre); }

    @Override
    protected int saludBase() { return 80; }

    @Override
    public String atacar() {
        return nombre + " lanza bola de fuego 🔥";
    }
}