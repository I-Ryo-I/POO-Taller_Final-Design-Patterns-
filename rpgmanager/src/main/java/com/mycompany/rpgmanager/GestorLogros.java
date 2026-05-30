package com.mycompany.rpgmanager;
 
public class GestorLogros implements ObservadorPersonaje {
    @Override
    public void actualizar(Personaje personaje, String evento) {
        if (evento.contains("nivel")) {
            System.out.println("  [LOGRO] " + personaje.getNombre()
                + " alcanzó nivel " + personaje.getNivel() + " - Logro desbloqueado!");
        }
    }
}
