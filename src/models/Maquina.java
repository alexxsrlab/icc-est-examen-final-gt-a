package models;

import java.util.*;

public class Maquina implements Comparable<Maquina> {
    private String nombre;
    private String ip;
    private List<Integer> codigos;
    private int subred;
    private int riesgo;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;

        this.subred = calcularSubred();
        this.riesgo = calcularRiesgo();
    }

    private int calcularSubred() {
        String[] partes = ip.split("\\.");
        if (partes.length != 4) {
            throw new IllegalArgumentException("IP no valida: " + ip);
        }
        return Integer.parseInt(partes[3]);
    }

    private int calcularRiesgo() {
        int suma = 0;
        for (int c : codigos) {
            if (c % 3 == 0) suma += c;
        }
        long unicos = nombre.replaceAll("\\s+", "").chars().distinct().count();
        return (int) (suma * unicos);
    }

    public String getNombre() {
        return nombre;
    }

    public String getIp() {
        return ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public int getSubred() {
        return subred;
    }

    public int getRiesgo() {
        return riesgo;
    }

    @Override
    public int compareTo(Maquina otra) {
        int cmpSubred = Integer.compare(this.subred, otra.subred);
        if (cmpSubred != 0) return cmpSubred;

        return this.nombre.compareTo(otra.nombre);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maquina)) return false;
        Maquina m = (Maquina) o;
        return subred == m.subred && nombre.equals(m.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subred, nombre);
    }

    @Override
    public String toString() {
        return "Maquina [nombre=" + nombre + ", ip=" + ip + ", codigos=" + codigos + ", subred=" + subred + ", riesgo="
                + riesgo + "]";
    }

    

    

    
}

