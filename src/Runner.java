import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

	StockCharacteristics stocks;
	Prices prices;

	/**
	 * This method will run the entire program
	 * 
	 * @param args
	 * @author William & Ryan
	 */

	public static void main(String[] args) {

		System.out.println("Below is a list of stocks from the S&P 500 Index that you can choose from: ");
		System.out.println();

		runStockPredictor();

	}

	/**
	 * Creates an arraylist with the calculated stock prices that a user selected.
	 * The arraylist has the stock's name at index 0, the 2019 stock price at index
	 * 1, the 2020 predicted stock price at index 2, the 2021 predicted stock price
	 * at index 3, and the 2022 predicted stock price at index 4.
	 * 
	 * @return an arraylist of Strings
	 */
	public static ArrayList<String> stockPredictor() {

		RegressionAnalysis regression = new RegressionAnalysis();

		StockCharacteristics stockToInput = regression.stockInput();

		double price = regression.calculatedRegressionStockPrice(stockToInput);

		Prices adjustedPrice = new Prices();

		double[] pricePredictor = adjustedPrice.predictedPrices(price, stockToInput);

		System.out.println("\n\n\n\n Predicted Stock Prices " + stockToInput);
		System.out.println("****************************************************");

		System.out.printf(" December 2020 Price = $%.2f%n", pricePredictor[0]);

		System.out.printf("\n December 2021 Price = $%.2f%n", pricePredictor[1]);

		System.out.printf("\n December 2022 Price = $%.2f%n", pricePredictor[2]);

		System.out.println("****************************************************");

		ArrayList<String> results = new ArrayList<String>();

		results.add(stockToInput.getStockName());
		results.add(Double.toString(stockToInput.getStockPrice2019()));
		results.add(Double.toString(pricePredictor[0]));
		results.add(Double.toString(pricePredictor[1]));
		results.add(Double.toString(pricePredictor[2]));

		return results;
	}

	/**
	 * Determines whether a user wants to find the predicted stock price of another
	 * stock.
	 * 
	 * @return a boolean with true being yes, the user wants to find another stock
	 *         price, and false being no.
	 */
	public static boolean anotherRun() {

		boolean typeToReturn = false;

		for (int i = 0; i < 1; i++) {

			System.out.println("\n\n\n\n ******************  INPUT NEEDED   ******************");
			System.out
					.println("\n Would you like to predict the price of another stock? \n \n Please type in Yes or No");

			Scanner scanner = new Scanner(System.in);

			String userInput = scanner.next();

			userInput = userInput.toLowerCase();

			if (userInput.equals("yes")) {

				typeToReturn = true;

				// System.out.println("\n Please wait approximately 15 seconds while we
				// calculate the future stock price:");

			} else if (userInput.contentEquals("no")) {

				typeToReturn = false;

			} else {

				System.out.println("\n Please type in Yes or No and make sure there are no spaces");

				i--;
			}
		}

		return typeToReturn;
	}

	/**
	 * Writes a csv. file with the results of the stock prices that were calculated.
	 * 
	 * @param an arraylist with the results of the predicted stock prices
	 * @return void
	 */
	public static void writingFile(ArrayList<ArrayList<String>> results) {

		System.out.println("\n\n\n\n ****************************************************");
		System.out.println("\n A file called Stock_Predictions containing all the results has been created.");

		String userFileName = "Stock_Predictions.csv";

		try {

			FileWriter file = new FileWriter(userFileName);
			
			String heading = "Stock Name, Current Stock Price, 2020 Predicted Stock Price, 2021 Predicted Stock Price, 2022 Predicted Stock Price \n";

			
			file.write(heading);

			for (int i = 0; i < results.size(); i++) {

				StringBuilder newList = new StringBuilder();

				for (int j = 0; j < results.get(i).size(); j++) {
					
					if(j==1) {
						
						Prices price = new Prices();
						String stockName = results.get(i).get(0);
						String information = price.informationFromAPI(stockName, 1);
						double currentPrice = price.stockInformation(information);
						newList.append(currentPrice);
						newList.append(",");
					}
					
					else{
						
						newList.append(results.get(i).get(j));
						newList.append(",");
					}
				}

				newList.append("\n");

				String words = newList.toString();

				file.write(words);

			}

			file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	/**
	 * Runs the whole program and displays the results.
	 */
	public static void runStockPredictor() {

		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();

		results.add(stockPredictor());

		while (anotherRun()) {

			results.add(stockPredictor());
		}

		writingFile(results);
	}
}
