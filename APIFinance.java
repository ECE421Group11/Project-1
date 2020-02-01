import java.math.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class APIFinance {
  private static final String BASE_URL = "https://www.alphavantage.co/query?";
  private final static String apiKey = "BZWV3H8ON54SZAK7";

  public APIFinance(){

  }

  public static BigDecimal getPrice(final String symbol) throws InterruptedException{
    BigDecimal price = new BigDecimal(0);
    try {
      while (price.equals(new BigDecimal(0))){
        URL url = new URL(BASE_URL + "function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey);
        URLConnection connection = url.openConnection();
        InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
          if (line.contains("price")) {
              price = new BigDecimal(line.split("\"")[3].trim());
          }
          else if(line.contains("Invalid API call")) {
        	  throw new IOException("Invalid API call for symbol " + symbol);
          }
        }
        bufferedReader.close();
      }
    }
    catch (IOException e) {
    	System.out.print('\n'+"failure sending request: ");
  		System.out.println(e);
    }
    return price;
  }

  public static void main(String[] args){

  }

}