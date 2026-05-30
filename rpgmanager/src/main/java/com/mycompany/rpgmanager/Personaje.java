package com.mycompany.rpgmanager;
 
import java.util.ArrayList;
import java.util.List;
 
public abstract class Personaje {
 
    protected String nombre;
    protected int    nivel;
    protected int    salud;
 
    // Lista de observadores (patrón Observer)
    private final List<ObservadorPersonaje> observadores = new ArrayList<>();
 
    public Personaje(String nombre) {
        this.nombre = nombre;
        this.nivel  = 1;
        this.salud  = saludBase();
    }
 
    // ── Métodos abstractos ──────────────────────────────
    protected abstract int    saludBase();
    public    abstract String atacar();
 
    // ── Getters ─────────────────────────────────────────
    public String getNombre() { return nombre; }
    public int    getNivel()  { return nivel;  }
    public int    getSalud()  { return salud;  }
 
    // ── Observer: suscribir / desuscribir / notificar ───
    public void suscribir(ObservadorPersonaje obs) {
        observadores.add(obs);
    }
 
    public void desuscribir(ObservadorPersonaje obs) {
        observadores.remove(obs);
    }
 
    private void notificar(String evento) {
        for (ObservadorPersonaje obs : observadores) {
            obs.actualizar(this, evento);
        }
    }
 
    // ── Acciones que notifican a los observadores ───────
    public void subirNivel() {
        nivel++;
        salud = saludBase();
        notificar("sube a nivel " + nivel);
    }
 
    public void recibirDanio(int danio) {
        salud -= danio;
        notificar("recibe " + danio + " de daño (HP=" + salud + ")");
    }
 
    @Override
    public String toString() {
        return "[" + getClass().getSimpleName() + "] "
             + nombre + " | Niv:" + nivel + " | HP:" + salud;
    }
}