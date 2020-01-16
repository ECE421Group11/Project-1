import java.util.function.*;
import java.math.*;

public class PickShareFunctional{
  public static void findHighPrices(){
    ShareInfo highPriced = new ShareInfo("MyStock", new BigDecimal("0.0"));
    final Predicate isPriceLessThan500 = ShareUtil.isPriceLessThan(500);
    for(String symbol : Shares.symbols) {
      ShareInfo shareInfo = ShareUtil.getPrice(symbol);
      if(isPriceLessThan500.test(shareInfo))
        highPriced = ShareUtil.pickHigh(highPriced, shareInfo);
    }
    System.out.println("High priced under $500 is " + highPriced);
  }

  public static void main(String[] args){
    findHighPrices();
  }
}
