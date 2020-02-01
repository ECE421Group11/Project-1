import java.util.function.*;
import java.math.*;
import java.util.stream.*;
import java.util.*;

public class PickShareFunctional{
  /*public static void findHighPricesImperative(){
    ShareInfo highPriced = new ShareInfo("MyStock", new BigDecimal("0.0"));
    final Predicate isPriceLessThan500 = ShareUtil.isPriceLessThan(500);
    for(String symbol : Shares.symbols) {
      ShareInfo shareInfo = ShareUtil.getPrice(symbol);
      if(isPriceLessThan500.test(shareInfo))
        highPriced = ShareUtil.pickHigh(highPriced, shareInfo);
    }
    System.out.println("High priced under $500 is " + highPriced);
  }*/

  // Need error handling to handle when the API is down, when there is no network connection,
  // etc. Need to handle all possible exceptions.
  public static void findHighPricesFunctional(Stream<String> sharesStream, BigDecimal filterValue){
    List<BigDecimal> sortedPrices = sharesStream.map(shareName -> ShareUtil.getPrice(shareName))
      .map(shareInfo -> shareInfo.price)
      .filter(sharePrice -> sharePrice.compareTo(filterValue) < 0)
      // .peek(sharePrice -> System.out.println(sharePrice + " "))
      .sorted(Comparator.reverseOrder())
      .collect(Collectors.toList());
    System.out.println(sortedPrices.get(0));
  }

  public static void main(String[] args){
    // findHighPricesImperative();
    findHighPricesFunctional(Shares.symbols.stream(), BigDecimal.valueOf(500));
  }
}
