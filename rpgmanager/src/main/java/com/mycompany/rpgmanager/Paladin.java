package com.mycompany.rpgmanager;

public class Paladin extends Personaje {
    public Paladin(String nombre) { super(nombre); }

    @Override
    protected int saludBase() { return 130; }

    @Override
    public String atacar() {
        return nombre + " bendice con escudo sagrado 🛡️";
    }
}