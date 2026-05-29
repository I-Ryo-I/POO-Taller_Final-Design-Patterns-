package com.mycompany.rpgmanager;

public abstract class Personaje {
    protected String nombre;
    protected int nivel;
    protected int salud;

    public Personaje(String nombre) {
        this.nombre = nombre;
        this.nivel  = 1;
        this.salud  = saludBase();
    }

    protected abstract int saludBase();
    public abstract String atacar();

    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + nombre 
             + " | Niv:" + nivel + " | HP:" + salud;
    }
}
