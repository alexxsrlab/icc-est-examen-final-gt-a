package validaciones;

import java.util.*;
import models.Maquina;

public class ValidacionesMaquina {

    public static void validarCampoSubred(Maquina m, int subredEsperado) {
        int real = m.getSubred();
        if (real != subredEsperado) {
            throw new AssertionError("Subred incorrecta: esperada " + subredEsperado + ", real " + real);
        }
    }
     
    public static void validarCampoRiesgo(Maquina m, int riesgoEsperado) {
        int real = m.getRiesgo();
        if (real != riesgoEsperado) {
            throw new AssertionError("Riesgo incorrecto: esperado " + riesgoEsperado + ", real " + real);
        }
    }

    public static void validarResultadoA(Set<Maquina> resultado, int cantidadEsperada, int umbral) {
        if (resultado.size() != cantidadEsperada) {
            throw new AssertionError("Cantidad incorrecta en resultado A");
        }
        for (Maquina m : resultado) {
            if (m.getSubred() >= umbral) {
                throw new AssertionError("Maquina con subred ");
            }
        }
    }

    public static void validarResultadoB(Set<Maquina> ordenado, List<Maquina> originales, int umbral) {
        List<Maquina> filtradas = new ArrayList<>();
        for (Maquina maa : originales) {
            if (maa.getSubred() < umbral) filtradas.add(maa);
        }

        List<Maquina> ordenadasEsperadas = new ArrayList<>(new TreeSet<>(ordenado));
        if (!ordenado.equals(new TreeSet<>(ordenadasEsperadas))) {
        throw new AssertionError("Orden o contenido incorrecto en resultado B");
        }
    }

    public static void validarResultadoC(Map<Integer, Queue<Maquina>> mapa, List<Maquina> maquinas) {
        for (Maquina maaa : maquinas) {
            if (!mapa.containsKey(maaa.getRiesgo())) {
                throw new AssertionError("No se encontro el riesgo en el mapa: " + maaa.getRiesgo());
            }
        }
    }

    public static void validarResultadoD(Stack<Maquina> resultado, List<Maquina> maquinas) {
        if (resultado.isEmpty()) {
            throw new AssertionError("Resultado D vacio");
        }
    }
}

