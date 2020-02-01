import java.math.BigDecimal;
import java.util.function.Predicate;

public class PickShareImperative {
	
	public static void main(String[] args) {
	ShareInfo highPriced = new ShareInfo("ABC", BigDecimal.valueOf(0));
	final Predicate<ShareInfo> isPriceLessThan500 = ShareUtil.isPriceLessThan(500);{
	for(String symbol : Shares.symbols) {
	 ShareInfo shareInfo = ShareUtil.getPrice(symbol);
	 //TEMP
	 System.out.println(shareInfo);
	 try {
		Thread.sleep(12000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//TEMP
	if(isPriceLessThan500.test(shareInfo))
	 highPriced = ShareUtil.pickHigh(highPriced, shareInfo);
	}
	System.out.println("High priced under $500 is " + highPriced);
}
	}
}