package controllers;

import java.util.*;
import models.Maquina;

public class MaquinaController {

    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> pila = new Stack<>();
        for (int i = maquinas.size() - 1; i >= 0; i--) {
            Maquina ma = maquinas.get(i);
            if (ma.getSubred() < umbral) {
                pila.push(ma);
            }
        }
        return pila;
    }

    public Set<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        Comparator<Maquina> comp = Comparator.comparingInt(Maquina::getSubred).thenComparing(Maquina::getNombre);
        return new TreeSet<>(pila.stream().sorted(comp).toList());
    }

    public Map<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
        Map<Integer, Queue<Maquina>> mapa = new TreeMap<>();
        for (Maquina mqn : maquinas) {
            mapa.computeIfAbsent(mqn.getRiesgo(), k -> new LinkedList<>()).add(mqn);
        }
        return mapa;
    }

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
        if (mapa.isEmpty()) return new Stack<>();

        Map.Entry<Integer, Queue<Maquina>> mejor = null;
        for (Map.Entry<Integer, Queue<Maquina>> e : mapa.entrySet()) {
            if (mejor == null) {
                mejor = e;
            } else {
                int cmpSize = Integer.compare(e.getValue().size(), mejor.getValue().size());
                int cmpRiesgo = Integer.compare(e.getKey(), mejor.getKey());
                if (cmpSize > 0 || (cmpSize == 0 && cmpRiesgo > 0)) {
                    mejor = e;
                }
            }
        }

        Stack<Maquina> resultado = new Stack<>();
        for (Maquina m : mejor.getValue()) {
            resultado.push(m);
        }
        return resultado;
    }
}

