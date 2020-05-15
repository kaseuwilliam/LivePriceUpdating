import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class extends the StockList class and is going to be used to create the
 * framework for individual stock objects. It will ultimately be used to create
 * a hashmap & arraylist of all eligible stocks in the S&P 500 index (CSV file).
 * We will manipulate this data with other classes
 * 
 * @author Ryan & William
 */

public class StockCharacteristics extends StockList {

	private String stockName;
	private double stockPrice2019;
	private double stockPrice2018;
	private double totalRevenueYOY;
	private double netIncomeYOY;
	private double EPS;
	private double marketCap; // in billions
	private double debtToEquity;
	private double freeCashFlow;
	private double beta;
	private double movingAverage;
	private int industry;
	private double stockGrowthRate;

	// First Constructor
	public StockCharacteristics(String stockName, double stockPrice2019, double stockPrice2018, int industry,
			double totalRevenueYOY, double netIncomeYOY, double EPS, double marketCap, double debtToEquity,
			double freeCashFlow, double beta, double movingAverage, double stockGrowthRate) {

		this.stockName = stockName;
		this.stockPrice2019 = stockPrice2019;
		this.stockPrice2018 = stockPrice2018;
		this.industry = industry;
		this.totalRevenueYOY = totalRevenueYOY;
		this.netIncomeYOY = netIncomeYOY;
		this.EPS = EPS;
		this.marketCap = marketCap;
		this.debtToEquity = debtToEquity;
		this.freeCashFlow = freeCashFlow;
		this.beta = beta;
		this.movingAverage = movingAverage;
		this.stockGrowthRate = stockGrowthRate;

	}

	// Second Constructor
	public StockCharacteristics(String stockName, double stockPrice2019) {

		this.stockName = stockName;
		this.stockPrice2019 = stockPrice2019;
	}

	// Third Constructor
	public StockCharacteristics() {

	}

	/**
	 * Reads the StockAnalysis CSV file and creates an ArrayList with all the stock
	 * names in alphabetical order.
	 * 
	 * @return stockList
	 */
	public ArrayList<StockCharacteristics> readData() {

		File stocks = new File("StockAnalysis.csv");

		try {

			Scanner scanner = new Scanner(stocks);

			ArrayList<StockCharacteristics> stockList = new ArrayList<StockCharacteristics>();

			scanner.nextLine();

			while (scanner.hasNextLine()) {

				String s = scanner.nextLine();

				String[] infoSeperation = s.split(",");

				String stockName = infoSeperation[0];
				double stockPrice2019 = Double.parseDouble(infoSeperation[1]);
				double stockPrice2018 = Double.parseDouble(infoSeperation[2]);
				int industry = Integer.parseInt(infoSeperation[3]);
				double totalRevenue = Double.parseDouble(infoSeperation[4]);
				double netIncome = Double.parseDouble(infoSeperation[5]);
				double EPS = Double.parseDouble(infoSeperation[6]);
				double marketCap = Double.parseDouble(infoSeperation[7]);
				double debtToEquity = Double.parseDouble(infoSeperation[8]);
				double freeCashFlow = Double.parseDouble(infoSeperation[9]);
				double beta = Double.parseDouble(infoSeperation[10]);
				double movingAverage = Double.parseDouble(infoSeperation[11]);
				double stockGrowthRate = Double.parseDouble(infoSeperation[12]);

				StockCharacteristics stockInput = new StockCharacteristics(stockName, stockPrice2019, stockPrice2018,
						industry, totalRevenue, netIncome, EPS, marketCap, debtToEquity, freeCashFlow, beta,
						movingAverage, stockGrowthRate);

				// System.out.println(stockName);
				stockList.add(stockInput);
			}

			return stockList;

		} catch (Exception FileNotFoundException) {

			return null;
		}
	}

	/**
	 * Creates a HashMap of stocks where the key is the company's ticker and the
	 * value is a stock in the StockCharacteristics class.
	 * 
	 * @return stocksToList
	 */
	public HashMap<String, StockCharacteristics> stocksListedInHashMap() {

		StockCharacteristics stocks = new StockCharacteristics();

		HashMap<String, StockCharacteristics> stocksToList = new HashMap<String, StockCharacteristics>();

		ArrayList<StockCharacteristics> listedAlphabetically = stocks.readData();

		for (StockCharacteristics stock : listedAlphabetically) {

			stocksToList.put(stock.getStockName(), stock);
		}

		return stocksToList;
	}

	/**
	 * Overrides the toString method so that we can display the company name of a
	 * stock (instead of memory location) when a StockCharacteristics object is
	 * created.
	 * 
	 * @return a String with the stock company's name.
	 */

	@Override
	public String toString() {

		HashMap<String, String> stockCompanyName = read();

		String sentenceToReturn = "";

		StringBuilder words = new StringBuilder("for ");

		try {

			words.append(stockCompanyName.get(getStockName()));
			sentenceToReturn = words.toString();

		} catch (Exception e) {

			words.append(getStockName());
			sentenceToReturn = words.toString();

		}

		return sentenceToReturn;
	}

	/**
	 * Overrides the equals method so that we can establish that if two stocks have
	 * the same ticker name and stock price in 2019, then those two stocks must be
	 * equal/the same.
	 * 
	 * @param StockCharacteristics object
	 * @return a boolean of true or false depending on whether two stocks are equal
	 *         or not.
	 */

	@Override
	public boolean equals(Object obj) {

		StockCharacteristics stock = (StockCharacteristics) obj;

		if (getStockName().equals(stock.getStockName()) && getStockPrice2019() == stock.getStockPrice2019()) {

			return true;

		} else {

			return false;

		}
	}

	/**
	 * Below is all the getters for this class
	 * 
	 * @return getters
	 */

	public double getStockGrowthRate() {
		return stockGrowthRate;
	}

	public double getStockPrice2018() {
		return stockPrice2018;
	}

	public String getStockName() {
		return stockName;
	}

	public double getStockPrice2019() {
		return stockPrice2019;
	}

	public double getTotalRevenueYOY() {
		return totalRevenueYOY;
	}

	public double getNetIncomeYOY() {
		return netIncomeYOY;
	}

	public double getEPS() {
		return EPS;
	}

	public double getMarketCap() {
		return marketCap;
	}

	public double getDebtToEquity() {
		return debtToEquity;
	}

	public double getFreeCashFlow() {
		return freeCashFlow;
	}

	public double getBeta() {
		return beta;
	}

	public double getMovingAverage() {
		return movingAverage;
	}

	public int getIndustry() {
		return industry;
	}

}
