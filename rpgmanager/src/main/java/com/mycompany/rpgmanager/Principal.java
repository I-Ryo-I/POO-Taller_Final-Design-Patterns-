package com.mycompany.rpgmanager;

import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {

        // ── 1. SINGLETON: Configuración global ──────────
        ConfiguracionJuego cfg = ConfiguracionJuego.getInstance();
        cfg.setDificultad("dificil");

        System.out.println("================================================");
        System.out.println("  RPG MANAGER — Proyecto 2 · Design Patterns");
        cfg.mostrar();
        System.out.println("================================================");

        // ── 2. FACTORY: Crear equipo de 4 personajes ────
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

        // ── 3. OBSERVER: Suscribir observadores a Thorin ─
        System.out.println("\n--- Configurando observadores para Thorin ---");
        Personaje thorin = equipo.get(0);

        ObservadorPersonaje log    = new LogCombate();
        ObservadorPersonaje logros = new GestorLogros();
        ObservadorPersonaje sfx    = new SistemaSonido();

        thorin.suscribir(log);
        thorin.suscribir(logros);
        thorin.suscribir(sfx);

        // ── 4. Ronda de combate ──────────────────────────
        System.out.println("\n RONDA DE COMBATE:");
        for (Personaje p : equipo) {
            System.out.println("  -> " + p.atacar());
        }

        // ── 5. Observer en acción ────────────────────────
        System.out.println("\n--- Eventos con Observer activo ---");
        thorin.recibirDanio(35);
        thorin.subirNivel();

        // Desuscribir SistemaSonido y verificar silencio
        System.out.println("\n--- Desuscribiendo SistemaSonido ---");
        thorin.desuscribir(sfx);
        thorin.recibirDanio(10);  // solo Log y Logros reaccionan

        // ── 6. Singleton: verificar misma instancia ──────
        ConfiguracionJuego cfg2 = ConfiguracionJuego.getInstance();
        System.out.println("\n¿Misma config? " + (cfg == cfg2)); // true

        // ── 7. GestorMisiones Singleton ──────────────────
        GestorMisiones gm = GestorMisiones.getInstance();
        gm.agregarMision("Derrotar al Dragon de Fuego");
        gm.agregarMision("Recuperar el Orbe Oscuro");
        gm.listar();
    }
}