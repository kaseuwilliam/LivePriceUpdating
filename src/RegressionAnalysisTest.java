import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegressionAnalysisTest {

	@Test
	void testCalculateRegression() {

		RegressionAnalysis regression = new RegressionAnalysis();
		
		int betaOneFromExcel = -11;
		
		assertEquals((int) regression.calculateRegression()[0], betaOneFromExcel);
		
	}

	@Test
	void testCalculatedRegressionStockPriceStockCharacteristics() {

			String stockName = "MMM";
			double stockPrice2019 = 176.42;
			double stockPrice2018 = 190.54;
			int industry = 7;
			double totalRevenueYOY = 0.001972031;
			double netIncomeYOY = -0.016086362;
			double EPS = 0.007224694;
			double marketCap = -0.007419718;
			double debtToEquity = 0.337036627;
			double freeCashFlow = 0.009105975;
			double beta = 0.952213309;
			double movingAverage = 171.5566218;
			double stockGrowthRate = 0.037705058;

			StockCharacteristics stock = new StockCharacteristics(stockName, stockPrice2019, stockPrice2018, industry,
					totalRevenueYOY, netIncomeYOY, EPS, marketCap, debtToEquity, freeCashFlow, beta, movingAverage,
					stockGrowthRate);

			RegressionAnalysis analysis = new RegressionAnalysis();

			double price = analysis.calculatedRegressionStockPrice(stock);

			int convertedPrice = (int) price;

			assertEquals(convertedPrice, 220);

	}

	@Test
	void testCalculatedRegressionStockPriceStockCharacteristicsDouble() {
		
		String stockName = "MMM";
		double stockPrice2019 = 176.42;
		double stockPrice2018 = 190.54;
		int industry = 7;
		double totalRevenueYOY = 0.001972031;
		double netIncomeYOY = -0.016086362;
		double EPS = 0.007224694;
		double marketCap = -0.007419718;
		double debtToEquity = 0.337036627;
		double freeCashFlow = 0.009105975;
		double beta = 0.952213309;
		double movingAverage = 171.5566218;
		double stockGrowthRate = 0.037705058;

		StockCharacteristics stock = new StockCharacteristics(stockName, stockPrice2019, stockPrice2018, industry,
				totalRevenueYOY, netIncomeYOY, EPS, marketCap, debtToEquity, freeCashFlow, beta, movingAverage,
				stockGrowthRate);

		RegressionAnalysis analysis = new RegressionAnalysis();

		double [] price = analysis.calculatedRegressionStockPrice(stock,0);

		int convertedPrice = (int) price [0];

		assertEquals(convertedPrice, 19);
	}

	@Test
	void testToString() {
		
		RegressionAnalysis regression = new RegressionAnalysis();
		
		assertEquals(regression.toString().contains("the equation y=-11.326970933410252 intercept + 1.144354773621899 stockPrice2018"), true);
	}

}
