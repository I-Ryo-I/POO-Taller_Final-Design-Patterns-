package com.mycompany.rpgmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FabricaPersonajes {
    private static final Map<String, Function<String, Personaje>> CATALOGO = new HashMap<>();

    static {
        CATALOGO.put("guerrero", Guerrero::new);
        CATALOGO.put("mago",     Mago::new);
        CATALOGO.put("arquero",  Arquero::new);
        CATALOGO.put("paladin",  Paladin::new);
    }

    public static Personaje crear(String tipo, String nombre) {
        Function<String, Personaje> constructor = CATALOGO.get(tipo.toLowerCase());
        if (constructor == null) {
            throw new IllegalArgumentException(
                "Tipo desconocido: '" + tipo + "'. Usa: " + CATALOGO.keySet());
        }
        Personaje p = constructor.apply(nombre);
        System.out.println("[Factory] Creado: " + p);
        return p;
    }
}