import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is an abstract class which is used as guidance to create a list of stock
 * tickers and a hashMap containing info from the CSV files
 * 
 * @author William & Ryan
 */

public abstract class StockList {

	StockCharacteristics stocks;

	// Constructor
	public StockList() {
	}

	/**
	 * Creates an ArrayList with all the stock tickers in alphabetical order. This
	 * method will use polymorphism in the future
	 * 
	 * @return stockList
	 */

	public ArrayList<String> listOfStocks() {

		StockCharacteristics stocks = new StockCharacteristics();

		ArrayList<StockCharacteristics> stocksInCharacteristicsFormat = stocks.readData();

		ArrayList<String> stockList = new ArrayList<String>();

		for (int i = 0; i < stocksInCharacteristicsFormat.size(); i++) {

			stockList.add(stocksInCharacteristicsFormat.get(i).getStockName());
		}

		return stockList;
	}

	/**
	 * Creates a HashMap of stocks where the key is the company's ticker and the
	 * value is an array with the stock's company name and it's industry.
	 * 
	 * @return listOfStocks
	 */

	public HashMap<String, String> read() {

		File stocks = new File("List_Of_Stocks.csv");

		try {

			Scanner scanner = new Scanner(stocks);
			scanner.nextLine();

			HashMap<String, String> listOfStocks = new HashMap<String, String>();

			while (scanner.hasNextLine()) {

				String storage = scanner.nextLine();
				String[] infoSeparation = storage.split(",");

				listOfStocks.put(infoSeparation[0], infoSeparation[1]);
			}

			return listOfStocks;
		}

		catch (Exception e) {

			System.out.println("Something went wrong");

			return null;
		}
	}

	/**
	 * A method that will need to read the StockAnalysis CSV file and create an
	 * ArrayList with all the stock names.
	 * 
	 * @return stockList
	 */

	public abstract ArrayList<StockCharacteristics> readData();

	/**
	 * A method that will create a HashMap of stocks where the key is the company's
	 * ticker and the value is a stock in the StockCharacteristics class.
	 * 
	 * @return stocksToList
	 */

	public abstract HashMap<String, StockCharacteristics> stocksListedInHashMap();

}