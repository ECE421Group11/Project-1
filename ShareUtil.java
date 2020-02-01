import java.math.BigDecimal;
import java.util.function.Predicate;
/**
 * ShareUtil contains various static utility methods that deal with shares. 
 */
public class ShareUtil{

  /**
   * Get the price of a symbol and return a ShareInfo object
   */
  public static ShareInfo getPrice(final String symbol){
    return new ShareInfo (symbol, APIFinance.getPrice(symbol));
  }

  /**
   * Returns a predicate which evaluates if a share has a price less than the specified price
   */
  public static Predicate<ShareInfo> isPriceLessThan(final int price) {
    return shareInfo -> shareInfo.price.compareTo(BigDecimal.valueOf(price)) < 0;
  }

  /**
   * Given two shares returns the share with the highest price
   */
  public static ShareInfo pickHigh(final ShareInfo share1, final ShareInfo share2) {
    return share1.price.compareTo(share2.price) > 0 ? share1: share2;
  }
}

