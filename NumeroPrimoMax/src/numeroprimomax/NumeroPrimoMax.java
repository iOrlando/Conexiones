package numeroprimomax;

import java.math.BigInteger;
import java.util.Objects;

public class NumeroPrimoMax {
    
    private static final Integer PRI_VAL = 2;
    private static final Integer CERO = 0;

    public static void main(String[] args) {
        
        BigInteger numMax =  new BigInteger("600851475143");
        
        
    }
    
    static Boolean validarNumPrimo(Integer numero) {
        Boolean esPrimo = Boolean.FALSE;
        if(!Objects.equals(numero % PRI_VAL, CERO))
            esPrimo = Boolean.TRUE;
        return esPrimo;
    }
    
}
