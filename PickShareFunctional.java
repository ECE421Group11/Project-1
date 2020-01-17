import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Stream;

public class PickShareFunctional {
	public static void findHighPriced(Stream<String> stream){
		BigDecimal upperBound = BigDecimal.valueOf(500);
		System.out.print("High priced under $" + upperBound + " is ");
		
		System.out.println(
		stream.map(ShareUtil::getPrice)
		.filter(ShareInfo->ShareInfo.price.compareTo(upperBound) < 0)
		.sorted(Comparator.comparing((ShareInfo s)->s.price).reversed())
		.findFirst()
		.get()
		);
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println(LocalDateTime.now());
		findHighPriced(Shares.symbols.stream());
		System.out.println(LocalDateTime.now());
		
		Thread.sleep(60000);
		System.out.println();
		
		System.out.println(LocalDateTime.now());
		findHighPriced(Shares.symbols.parallelStream());
		System.out.println(LocalDateTime.now());
	}
}
