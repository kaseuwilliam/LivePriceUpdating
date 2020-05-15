import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class StockCharacteristicsTest {

	@Test
	void testReadData() {
		
		StockCharacteristics readingTheData = new StockCharacteristics();
		
		ArrayList<StockCharacteristics> stockLists = readingTheData.readData();
		
		String stockName = stockLists.get(10).getStockName();
		
		assertEquals(stockName, "AFL");
		
	}
	
	@Test
	void testEquals() {
			
		StockCharacteristics stocks = new StockCharacteristics();
		
		ArrayList <StockCharacteristics> stockList = stocks.readData();
		
		StockCharacteristics stock1 = stockList.get(0);
		
		StockCharacteristics stock2 = stockList.get(1);
		
		StockCharacteristics stock3 = new StockCharacteristics("MMM", 176.42);
		
		assertEquals(stock1.equals(stock3), true);
		
		assertEquals(stock1.equals(stock2), false);
	}

	@Test
	void testToString() {
		
		StockCharacteristics stocks = new StockCharacteristics();
		
		ArrayList <StockCharacteristics> stockList = stocks.readData();
		
		StockCharacteristics stock1 = stockList.get(0);
		
		assertEquals(stock1.toString(), "for 3M Company");
	}
	
	@Test
	void testStocksListedInHashMap() {
		
		StockCharacteristics stocks = new StockCharacteristics();
		
		ArrayList <StockCharacteristics> stockList = stocks.readData();
		
		StockCharacteristics mmmStock = stockList.get(0);
		
		HashMap<String, StockCharacteristics> mmm = new HashMap<String, StockCharacteristics>();
		
		mmm.put("MMM", mmmStock);
		
		assertEquals(mmmStock, stocks.stocksListedInHashMap().get("MMM"));
		
		
		StockCharacteristics amazonStock = stockList.get(27);
		
		assertEquals(stocks.stocksListedInHashMap().get("AMZN"), amazonStock);
	}

	@Test
	void getIndustry() {
		
		StockCharacteristics stocks = new StockCharacteristics();
		
		ArrayList <StockCharacteristics> stockList = stocks.readData();
		
		assertEquals(stockList.get(0).getIndustry(), 7);
	}
	
}
