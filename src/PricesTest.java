import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PricesTest {

	 @Test
	void testIndustries() {
		
		 Prices stocks = new Prices();
		
		 StockCharacteristics stock = new StockCharacteristics();
		 
		 ArrayList<Double> growthRates = new ArrayList<Double>();
		 
		 for(StockCharacteristics company: stock.readData()) {
			 
			 if(company.getIndustry() == 1) {
				 
				 growthRates.add(company.getStockGrowthRate());
			 }
		 }
		 
		 assertEquals(stocks.industries().get(1),growthRates);
	}

	@Test
	void testStandardDeviation() {
		
		Prices stocks = new Prices();
		
		ArrayList<Double> standardDeviationsData = new ArrayList<Double>();
		
		for (int i=0; i<10; i++) {
		
			standardDeviationsData.add((double) i);
			
		}
		
		int testInExcel = (int) 3.027650354;
		int testOnProgram= (int) stocks.standardDeviation(standardDeviationsData);
		
		assertEquals(testOnProgram, testInExcel);
	
	}

	@Test
	void testCoronavirusDiscount() {
		
		Prices stocks = new Prices();
		StockCharacteristics stock = new StockCharacteristics();
		
		ArrayList <StockCharacteristics> listOfStocks = stock.readData();
		
		String stockName = "ABC";
		String industry= "7";
		double stockPriceDouble = 100*0.7;
		int stockPrice = (int) stockPriceDouble;
		
		assertEquals(stocks.coronavirusDiscount(100, listOfStocks.get(0)), stockPrice);
	}

	@Test
	void testPredictedPrices() {

		Prices price = new Prices();
		StockCharacteristics stock = new StockCharacteristics();
		
		StockCharacteristics mmm = stock.readData().get(0);
		
		double movement =	1 - (2 * price.standardDeviation(price.industries().get(7)));
		
		double predictedPrice = mmm.getStockPrice2019()*movement;
		
		double expectedAnswer = price.coronavirusDiscount(predictedPrice, mmm);
		
		assertEquals(price.predictedPrices(0, mmm)[0], expectedAnswer);
		
	}

}
