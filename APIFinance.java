import java.math.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import javax.management.InvalidAttributeValueException;

/**
 * This class handles reaching out to the finance API
 */
public class APIFinance {
  private static final String BASE_URL = "https://www.alphavantage.co/query?";
  private final static String apiKey = "8RNI5F8JRRCJ8JXL";

  public APIFinance(){

  }

  /**
   * This method reads in a finance symbol and calls the finance API to get the associated price.
   */
  public static BigDecimal getPrice(final String symbol){
    BigDecimal price = new BigDecimal(0);

    // try block to catch IOExceptions
    try {

      // the API times out if it is sent more than 5 requests per minute. In this case the price is not set
      // and its value is kept at 0. In this case we continually hit the API until it provides us with an 
      // answer
      while (price.equals(new BigDecimal(0))){

        // connect to the API
        URL url = new URL(BASE_URL + "function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey);
        URLConnection connection = url.openConnection();
        InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        String line;
        
        while ((line = bufferedReader.readLine()) != null) {

          // extract the price
          if (line.contains("price")) {
              price = new BigDecimal(line.split("\"")[3].trim());
          }

          // if the symbol provided is invalid, throws an exception
          else if(line.contains("Invalid API call")) {
        	  throw new InvalidAttributeValueException("Invalid API call for symbol " + symbol);
          }
        }
        bufferedReader.close();
      }
    }
    catch (IOException e) {
    	System.out.print('\n'+"failure sending request: ");
  		System.out.println(e);
    }
    catch (InvalidAttributeValueException eI){
      System.out.print('\n'+"invalid symbol in request: ");
  		System.out.println(eI);
    }
    return price;
  }


  public static void main(String[] args){
  }

}