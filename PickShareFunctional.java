import java.util.function.*;
import java.math.*;
import java.util.stream.*;
import java.util.*;

/**
 * This class contains the method which retreives the highest priced share under a certain threshold
 */
public class PickShareFunctional {

  /**
   * Map each symbol to its ShareInfo equivalent, filter them based on price, the sort them by price
   */
  public static ShareInfo findHighPrices(Stream<String> sharesStream, int filterValue){
    return sharesStream
      .map(ShareUtil::getPrice)
      .filter(ShareUtil.isPriceLessThan(filterValue))
      .sorted(Comparator.comparing((ShareInfo s) -> s.price).reversed())
      .findFirst()
      .get();
  }

  /**
   * Currently tests findHighPrices
   */
  public static void main(String[] args){
    System.out.println(findHighPrices(Shares.symbols.parallelStream(), 500).toString());
  }
}
