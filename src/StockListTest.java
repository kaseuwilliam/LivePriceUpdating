import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class StockListTest {

	@Test
	void testStockList() {
	
		StockCharacteristics readingTheData = new StockCharacteristics();
		
		ArrayList<String> stockLists = readingTheData.listOfStocks();
		
		String stockName = stockLists.get(10);
		
		assertEquals(stockName, "AFL");
	}

	@Test
	void testRead() {
		
		StockCharacteristics readingTheData = new StockCharacteristics();
		
		HashMap<String, String> stockLists = readingTheData.read();
		
		String stockName = stockLists.get("GOOGL");
		
		assertEquals(stockName, "Alphabet Inc Class A");
	}

}
