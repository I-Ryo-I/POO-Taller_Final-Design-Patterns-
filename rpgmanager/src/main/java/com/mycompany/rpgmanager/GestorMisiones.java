package com.mycompany.rpgmanager;

import java.util.ArrayList;
import java.util.List;

public class GestorMisiones {
    private static volatile GestorMisiones instancia = null;
    private final List<String> misiones = new ArrayList<>();

    private GestorMisiones() { }

    public static GestorMisiones getInstance() {
        if (instancia == null) {
            synchronized (GestorMisiones.class) {
                if (instancia == null) {
                    instancia = new GestorMisiones();
                }
            }
        }
        return instancia;
    }

    public void agregarMision(String mision) {
        misiones.add(mision);
        System.out.println("[Mision] Agregada: " + mision);
    }

    public void listar() {
        System.out.println("\nMisiones activas:");
        for (int i = 0; i < misiones.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + misiones.get(i));
        }
    }
}