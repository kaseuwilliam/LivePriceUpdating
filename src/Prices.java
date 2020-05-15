import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Prices {

	StockCharacteristics stocks;
	RegressionAnalysis regressions;

	/**
	 * Creates a HashMap with all the industries in the S&P 500 and uses the Stock
	 * Characteristics class in order to do so.
	 * 
	 * @return HashMap of Industries
	 */

	public static HashMap<Integer, ArrayList<Double>> industries() {

		StockCharacteristics stocks = new StockCharacteristics();

		ArrayList<StockCharacteristics> stocksListed = stocks.readData();

		ArrayList<Double> communicationServices = new ArrayList<Double>(); // 1
		ArrayList<Double> consumerDiscretionary = new ArrayList<Double>(); // 2
		ArrayList<Double> consumerStaples = new ArrayList<Double>(); // 3
		ArrayList<Double> energy = new ArrayList<Double>(); // 4
		ArrayList<Double> financials = new ArrayList<Double>(); // 5
		ArrayList<Double> healthCare = new ArrayList<Double>(); // 6
		ArrayList<Double> industrials = new ArrayList<Double>(); // 7
		ArrayList<Double> informationTechnology = new ArrayList<Double>(); // 8
		ArrayList<Double> materials = new ArrayList<Double>(); // 9
		ArrayList<Double> realEstate = new ArrayList<Double>(); // 10
		ArrayList<Double> utilities = new ArrayList<Double>(); // 11
		ArrayList<Double> Other = new ArrayList<Double>(); // 11

		for (int i = 0; i < stocksListed.size(); i++) {

			int industryNumber = stocksListed.get(i).getIndustry(); // 7

			switch (industryNumber) {

			case 1:
				communicationServices.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 2:
				consumerDiscretionary.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 3:
				consumerStaples.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 4:
				energy.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 5:
				financials.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 6:
				healthCare.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 7:
				industrials.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 8:
				informationTechnology.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 9:
				materials.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 10:
				realEstate.add(stocksListed.get(i).getStockGrowthRate());
				break;
			case 11:
				utilities.add(stocksListed.get(i).getStockGrowthRate());
				break;
			default:
				Other.add(stocksListed.get(i).getStockGrowthRate());
				break;
			}
		}

		HashMap<Integer, ArrayList<Double>> industyStandardDeviations = new HashMap<Integer, ArrayList<Double>>();

		industyStandardDeviations.put(1, communicationServices);
		industyStandardDeviations.put(2, consumerDiscretionary);
		industyStandardDeviations.put(3, consumerStaples);
		industyStandardDeviations.put(4, energy);
		industyStandardDeviations.put(5, financials);
		industyStandardDeviations.put(6, healthCare);
		industyStandardDeviations.put(7, industrials);
		industyStandardDeviations.put(8, informationTechnology);
		industyStandardDeviations.put(9, materials);
		industyStandardDeviations.put(10, realEstate);
		industyStandardDeviations.put(11, utilities);

		return industyStandardDeviations;
	}

	/**
	 * Calculates the standard deviation of the stock growth rates in a given
	 * industry.
	 * 
	 * @return Standard Deviation of Industry
	 */
	public static double standardDeviation(ArrayList<Double> prices) {

		DescriptiveStatistics stats = new DescriptiveStatistics();

		for (int i = 0; i < prices.size(); i++) {

			stats.addValue(prices.get(i));
		}

		double statistic = stats.getStandardDeviation();

		stats.getKurtosis();
		stats.getStandardDeviation();

		return statistic;
	}

	/**
	 * Adjusts a stock price to either 2 standard deviations above/below the mean of
	 * the stock's industry. This method only executes if a stock price has been
	 * predicted to raise/fall by more than 50% in a given year.
	 * 
	 * @return newStockPrice
	 */
	public double adjustedStockPrice(double predictedPrice, StockCharacteristics stock) {

		int industry = stock.getIndustry();

		double newStockPrice = 0;

		if (predictedPrice <= 0.5 * stock.getStockPrice2019()) {

			double movement = 1 - (2 * standardDeviation(industries().get(industry)));

			newStockPrice = stock.getStockPrice2019() * movement;

		} else if (predictedPrice >= 1.5 * stock.getStockPrice2019()) {

			double movement = 1 + (2 * standardDeviation(industries().get(industry)));

			newStockPrice = stock.getStockPrice2019() * movement;

		} else {

			newStockPrice = predictedPrice;
		}

		return newStockPrice;
	}

	/**
	 * Discounts a stock price by a certain factor depending on the stock's
	 * industry. This is done to factor in the effects of the coronavirus on the
	 * general stock market. to either 2 standard deviations above/below the mean of
	 * the stock's industry.
	 * 
	 * @return discounted stock price
	 */
	public double coronavirusDiscount(double stockPrice, StockCharacteristics stock) {

		int industryNumber = stock.getIndustry();

		double premiumOrDiscount = 0;

		switch (industryNumber) {

		case 1:
			premiumOrDiscount = 0.85; // communications
			break;
		case 2:
			premiumOrDiscount = 0.95; // consumerDiscretionary
			break;
		case 3:
			premiumOrDiscount = 1.10;// consumerStaples
			break;
		case 4:
			premiumOrDiscount = 0.65;// energy
			break;
		case 5:
			premiumOrDiscount = 0.70;// financials
			break;
		case 6:
			premiumOrDiscount = 0.90;// healthCare
			break;
		case 7:
			premiumOrDiscount = 0.70;// industrials
			break;
		case 8:
			premiumOrDiscount = 1.05;// informationTechnology
			break;
		case 9:
			premiumOrDiscount = 0.75;// materials
			break;
		case 10:
			premiumOrDiscount = 0.75;// realEstate
			break;
		case 11:
			premiumOrDiscount = 0.80;// utilities
			break;
		default:
			premiumOrDiscount = 0.90;// Other
			break;
		}

		double predictedStockPrice = stockPrice * premiumOrDiscount;

		if (stock.getStockName().equals("AMZN")) {

			predictedStockPrice *= 1.25; // an added premium for Amazon
		}

		return predictedStockPrice;
	}

	/**
	 * Discounts a stock price by a certain factor depending on the stock's
	 * industry. This is done to factor in the effects of the coronavirus on the
	 * general stock market. to either 2 standard deviations above/below the mean of
	 * the stock's industry.
	 * 
	 * @return discounted stock price
	 */
	public double[] predictedPrices(double stockPrice, StockCharacteristics stock) {

		RegressionAnalysis regression = new RegressionAnalysis();

		Prices pricePredictor = new Prices();

		double adjustedPrice = pricePredictor.adjustedStockPrice(stockPrice, stock); // adjusted price

		double coronavirusPrice = pricePredictor.coronavirusDiscount(adjustedPrice, stock); // predicted price for 2020

		double[] yearlyPrices = regression.calculatedRegressionStockPrice(stock, coronavirusPrice);

		double price2021 = yearlyPrices[0];

		double price2022 = yearlyPrices[1];

		if (price2021 < 0) {

			price2021 = coronavirusPrice * 0.7;
		}

		if (price2021 > 2 * coronavirusPrice) {

			price2021 = coronavirusPrice * 1.5;
		}

		if (price2022 < 0) {

			price2022 = price2021 * 0.7;
		}

		if (price2022 > 2 * price2021) {

			price2022 = price2021 * 1.5;
		}

		double[] predictedStockPricesForUpcomingYears = { coronavirusPrice, price2021, price2022 };

		return predictedStockPricesForUpcomingYears;
	}

	
	/**
	 * The method follows overrloading and gets the data for the corresponding
	 * stockSymbol from Yahoo's API to create a long String with all the data for
	 * the stock.
	 * 
	 * @param The symbol of the stock you want to find, and a placeholder integer.
	 * @return A String with all the information for the stock.
	 */

	public String informationFromAPI(String stockSymbol, int number) {

		StringBuilder buildingSymbol = new StringBuilder("https://query1.finance.yahoo.com//v7/finance/quote?symbols=");

		buildingSymbol.append(stockSymbol);

		String symbol = buildingSymbol.toString();
		try {

			URL url = new URL(symbol);

			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

			String str = "";

			StringBuilder words = new StringBuilder();

			while (null != (str = br.readLine())) {

				// System.out.println(str);

				words.append(str);
			}

			String stockInformation = words.toString();

			return stockInformation;

		} catch (MalformedURLException e) {

			return null;
			// e.printStackTrace();

		} catch (IOException e) {

			return null;

			// e.printStackTrace();
		}

	}

	/**
	 * Creates a Stock Object with all the data we got from the informationFromAPI
	 * method.
	 * 
	 * @param The symbol of the stock you want to find.
	 * @return A Stock Object filled with the information of the stock.
	 */

	public double stockInformation(String stockFromAPI) {

		String info = "";
		String symbol = "";
		String shortName = "";
		String regularMarketPrice = "";
		String marketCap = "";
		String fiftyTwoWeekLow = "";
		String fiftyTwoWeekHigh = "";
		String regularMarketOpen = "";
		String regularMarketPreviousClose = "";
		String regularMarketDayLow = "";
		String regularMarketDayHigh = "";
		String regularMarketTime = "";
		String regularMarketVolume = "";
		String fullExchangeName = "";
		String postMarketPrice = ""; // need to still do
		String regularMarketPriceChange = "";// still need to do

		String[] infoSeperation = stockFromAPI.split(",");

		// String stockAverage = infoSeperation[10];

		for (String word : infoSeperation) {

			// System.out.println(word);

			if (word.contains("symbol")) {

				int indexToReturn = word.indexOf(":") + 2;

				symbol = word.substring(indexToReturn, word.length() - 3);

			} else if (word.contains("shortName")) {

				int indexToReturn = word.indexOf(":") + 2;

				shortName = word.substring(indexToReturn, word.length());

			} else if (word.contains("regularMarketPrice")) {

				int indexToReturn = word.indexOf(":") + 1;

				regularMarketPrice = word.substring(indexToReturn, word.length());

			} else if (word.contains("marketCap")) {

				int indexToReturn = word.indexOf(":") + 1;

				marketCap = word.substring(indexToReturn, word.length());

			} else if (word.contains("fiftyTwoWeekLow")) {

				if (word.contains("Change")) {

				} else {
					int indexToReturn = word.indexOf(":") + 1;

					fiftyTwoWeekLow = word.substring(indexToReturn, word.length());
				}
				
			} else if (word.contains("fiftyTwoWeekHigh")) {

				if (word.contains("Change")) {

				} else {
					int indexToReturn = word.indexOf(":") + 1;

					fiftyTwoWeekHigh = word.substring(indexToReturn, word.length());
				}

			} else if (word.contains("regularMarketOpen")) {

				int indexToReturn = word.indexOf(":") + 1;

				regularMarketOpen = word.substring(indexToReturn, word.length());

			} else if (word.contains("regularMarketPreviousClose")) {

				int indexToReturn = word.indexOf(":") + 1;

				regularMarketPreviousClose = word.substring(indexToReturn, word.length());

			} else if (word.contains("regularMarketDayLow")) {

				if (word.contains("Change")) {

				} else {
					int indexToReturn = word.indexOf(":") + 1;
					regularMarketDayLow = word.substring(indexToReturn, word.length());
				}

			} else if (word.contains("regularMarketDayHigh")) {

				if (word.contains("Change")) {

				} else {
					int indexToReturn = word.indexOf(":") + 1;

					regularMarketDayHigh = word.substring(indexToReturn, word.length());
				}
				
			} else if (word.contains("regularMarketTime")) {

				int indexToReturn = word.indexOf(":") + 1;

				regularMarketTime = word.substring(indexToReturn, word.length());

			} else if (word.contains("regularMarketVolume")) {

				int indexToReturn = word.indexOf(":") + 1;

				regularMarketVolume = word.substring(indexToReturn, word.length());

			} else if (word.contains("fullExchangeName")) {

				int indexToReturn = word.indexOf(":") + 2;

				fullExchangeName = word.substring(indexToReturn, word.length() - 1);

			} else if (word.contains("postMarketPrice")) {

				int indexToReturn = word.indexOf(":") + 1;

				postMarketPrice = word.substring(indexToReturn, word.length());

			} else if (word.contains("regularMarketChange") && !word.contains("regularMarketChangePercent")) {

				if (word.contains("Percent")) {

				} 
				
				else {
					int indexToReturn = word.indexOf(":") + 1;

					regularMarketPriceChange = word.substring(indexToReturn, word.length());
				}
				
			} else {
				info = "Didn't find anything";
			}

		}

		try {

			double regularMarketPrice1 = Double.parseDouble(regularMarketPrice);
			double postMarketPrice1 = Double.parseDouble(postMarketPrice);
			double regularMarketPriceChange1 = Double.parseDouble(regularMarketPriceChange);
			double regularMarketOpen1 = Double.parseDouble(regularMarketOpen);
			double regularMarketPreviousClose1 = Double.parseDouble(regularMarketPreviousClose);
			double regularMarketDayLow1 = Double.parseDouble(regularMarketDayLow);
			double regularMarketDayHigh1 = Double.parseDouble(regularMarketDayHigh);
			double marketCap1 = Double.parseDouble(marketCap);
			double fiftyTwoWeekLow1 = Double.parseDouble(fiftyTwoWeekLow);
			double fiftyTwoWeekHigh1 = Double.parseDouble(fiftyTwoWeekHigh);
			double regularMarketVolume1 = Double.parseDouble(regularMarketVolume);


			return regularMarketPrice1;

		} catch (Exception e) {

			return 0;
		}

	}

}
