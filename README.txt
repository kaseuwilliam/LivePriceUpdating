In order to run this project, you will need to download and install (in your own Eclipse environment) the Apache Commons Math Library at http://commons.apache.org/proper/commons-math/download_math.cgi

This pre-built package is used to run regressions on the underlying data, read-in from the “List_Of_Stocks”, “Macro_Data”, and “StockAnalysis” CSV files.

Once the package is installed, run the project on the “Runner.java” file.  You will be prompted for a stock input between 1 and 480 (corresponding to your individual stock selection) in the console.

Once you hit enter, you will get a prompt asking whether the correct stock was selected, at which you can say "yes" or "no". If you say "yes", the program will continue to the next stage, but if you say "no", the program will return to the previous step.

The prediction takes approximately 15-20 seconds to compute.

You will then be presented with a predicted stock price for December 2020, December 2021 and December 2022.

You will then be asked if you would like to predict the price of another stock.  

If you say “yes”, the program will run again. If you say “no”, an output CSV file called “StockPredictions” will be created with information of your selected stocks.

Please note that to run all the test files will take approximately 1 minute and 10 seconds. 