import java.math.*;

public class ShareInfo {
  public final String symbol;
  public final BigDecimal price;

  public ShareInfo (final String theSymbol, final BigDecimal thePrice) {
    symbol = theSymbol;
    price = thePrice;
  }

  public String toString() {
    return "The price for " + symbol + " is " + price + ".";
  }
}
