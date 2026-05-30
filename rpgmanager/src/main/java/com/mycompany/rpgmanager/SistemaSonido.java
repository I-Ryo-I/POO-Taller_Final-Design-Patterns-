package com.mycompany.rpgmanager;
 
public class SistemaSonido implements ObservadorPersonaje {
    @Override
    public void actualizar(Personaje personaje, String evento) {
        if (evento.contains("daño")) {
            System.out.println("  [SFX] sonido_golpe.mp3");
        } else if (evento.contains("nivel")) {
            System.out.println("  [SFX] level_up.mp3");
        } else {
            System.out.println("  [SFX] evento_generico.mp3");
        }
    }
}