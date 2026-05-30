package com.mycompany.rpgmanager;
 
public class LogCombate implements ObservadorPersonaje {
    @Override
    public void actualizar(Personaje personaje, String evento) {
        System.out.println("  [LOG] " + personaje.getNombre() + " → " + evento);
    }
}