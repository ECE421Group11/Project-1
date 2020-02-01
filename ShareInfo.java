import java.math.*;
import java.util.Comparator;

/**
 * Stores a share symbol alongside its price
 */
public class ShareInfo{
  public final String symbol;
  public final BigDecimal price;

  /**
   * Initializes the class
   */
  public ShareInfo (final String theSymbol, final BigDecimal thePrice) {
    symbol = theSymbol;
    price = thePrice;
  }

  /**
   * Converts the instance to a string
   */
  public String toString() {
    return "The price for " + symbol + " is " + price + ".";
  }
}
