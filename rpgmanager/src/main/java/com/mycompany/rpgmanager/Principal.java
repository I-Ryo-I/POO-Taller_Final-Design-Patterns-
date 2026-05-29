package com.mycompany.rpgmanager;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public static void main(String[] args) {

        // 1. Singleton de configuración
        ConfiguracionJuego cfg = ConfiguracionJuego.getInstance();
        cfg.setDificultad("dificil");
        System.out.println("================================================");
        System.out.println("  RPG MANAGER — Iniciando partida");
        cfg.mostrar();
        System.out.println("================================================");

        // 2. Equipo de 4 con Factory
        String[][] datos = {
            {"guerrero", "Thorin"},
            {"mago",     "Gandalf"},
            {"arquero",  "Legolas"},
            {"paladin",  "Arthas"},
        };

        List<Personaje> equipo = new ArrayList<>();
        for (String[] d : datos) {
            equipo.add(FabricaPersonajes.crear(d[0], d[1]));
        }

        // 3. Ronda de combate
        System.out.println("\n RONDA DE COMBATE:");
        for (Personaje p : equipo) {
            System.out.println("  → " + p.atacar());
        }

        // 4. Verificar que las dos referencias apuntan a la misma instancia
        ConfiguracionJuego cfg2 = ConfiguracionJuego.getInstance();
        System.out.println("\n¿Misma config? " + (cfg == cfg2)); // → true

        // 5. GestorMisiones Singleton
        GestorMisiones gm = GestorMisiones.getInstance();
        gm.agregarMision("Derrotar al Dragon de Fuego");
        gm.agregarMision("Recuperar el Orbe Oscuro");
        gm.listar();
    }
}