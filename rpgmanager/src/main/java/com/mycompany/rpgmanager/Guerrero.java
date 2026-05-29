package com.mycompany.rpgmanager;

public class Guerrero extends Personaje {
    public Guerrero(String nombre) { super(nombre); }

    @Override
    protected int saludBase() { return 150; }

    @Override
    public String atacar() {
        return nombre + " golpea con espada ⚔️";
    }
}
