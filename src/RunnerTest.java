import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

class RunnerTest {

	
	@Test
	void testRunStockPredictor() {
			File tmpDir = new File("StockAnalysis.csv");
			boolean exists = tmpDir.exists();
			
			assertEquals(exists, true);
	}

}
