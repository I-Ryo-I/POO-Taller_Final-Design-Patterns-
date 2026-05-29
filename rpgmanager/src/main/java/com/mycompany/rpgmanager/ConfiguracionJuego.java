package com.mycompany.rpgmanager;

public class ConfiguracionJuego {
    private static volatile ConfiguracionJuego instancia = null;

    private String dificultad = "normal";
    private String idioma     = "es";
    private int    volumen    = 80;

    private ConfiguracionJuego() { }

    public static ConfiguracionJuego getInstance() {
        if (instancia == null) {
            synchronized (ConfiguracionJuego.class) {
                if (instancia == null) {
                    instancia = new ConfiguracionJuego();
                }
            }
        }
        return instancia;
    }

    public void setDificultad(String d) { this.dificultad = d; }

    public void mostrar() {
        System.out.println("Config → dif:" + dificultad 
                         + " | idioma:" + idioma 
                         + " | vol:" + volumen);
    }
}