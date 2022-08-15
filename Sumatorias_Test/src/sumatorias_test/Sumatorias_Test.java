package sumatorias_test;

import java.util.Objects;

public class Sumatorias_Test {

    private static final Integer NUM_MAX = 1000;
    private static final Integer NUM_INI = 1;
    private static final Integer CERO = 0;
    private static final Integer NUM_F1 = 3;
    private static final Integer NUM_F2 = 5;

    public static void main(String[] args) {
        Integer cont = 0;
        for (int i = NUM_INI; i < NUM_MAX; i++) 
            if (validarMultiplo(i, NUM_F1) || validarMultiplo(i, NUM_F2)) 
                cont = sumarNumeros(cont, i);
        System.out.println("Total: " + cont);
    }
    
    static boolean validarMultiplo(Integer numUno, Integer numDos) {
        Boolean esMultiplo = Boolean.FALSE;
        if(Objects.equals(numUno % numDos, CERO))
            esMultiplo = Boolean.TRUE;
        return esMultiplo;
    }
    
    static Integer sumarNumeros(Integer numUno, Integer numDos) {
        return numUno + numDos;
    }
}
