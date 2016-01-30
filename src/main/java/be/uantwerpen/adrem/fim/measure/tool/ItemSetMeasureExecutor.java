package be.uantwerpen.adrem.fim.measure.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import be.uantwerpen.adrem.fim.measure.itemset.ItemSetMeasure;
import be.uantwerpen.adrem.fim.model.Itemset;
import be.uantwerpen.adrem.fim.model.TransactionDB;

public class ItemSetMeasureExecutor {
	public static void execute(ItemSetMeasure measure, String name,
			String datasetFile, String setsFile) {

		TransactionDB db = new TransactionDB(datasetFile);

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					setsFile));
			String outputFile = setsFile.substring(0,
					setsFile.lastIndexOf('.'))
					+ "-" + name + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					outputFile));

			String line;
			while ((line = reader.readLine()) != null) {
				String lineNoExtra;
				if (line.contains("(")) {
					lineNoExtra = line.split("\\(")[0];
				} else if (line.contains(":")) {
					lineNoExtra = line.split(":")[0];
				} else {
					lineNoExtra = line;
				}
				lineNoExtra = lineNoExtra.trim();

				StringTokenizer st = new StringTokenizer(lineNoExtra);
				Itemset itemSet = new Itemset();
				while (st.hasMoreTokens()) {
					itemSet.add(db.getItem(st.nextToken()));
				}
				String toWrite = lineNoExtra + " : "
						+ (int) measure.evaluate(itemSet) + "\n";
				writer.write(toWrite);
			}

			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}