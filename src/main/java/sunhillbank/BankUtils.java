package sunhillbank;

import java.math.BigDecimal;

public class BankUtils {
    public static boolean areBigDecimalsSame(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
        if (bigDecimal1.compareTo(bigDecimal2) == 0)
            return true;
        else
            return false;
    }
}
