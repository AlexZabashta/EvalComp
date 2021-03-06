package features_inversion.classification;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import weka.core.Instances;

public class ConvertSmallData {

	public static void main(String[] args) throws Exception {
		File dataFolder = new File("data/arff");
		for (File arff : dataFolder.listFiles()) {
			if (arff.length() > 200000) {
				continue;
			}

			try {
				Instances instances = new Instances(new FileReader(arff));
				double[][][] vals = ArffConverter.convert(instances, 20);
				Instances puredataSet = ArffConverter.convert(vals);
				try (PrintWriter writer = new PrintWriter(new File("data/carff/" + arff.getName()))) {
					writer.println(puredataSet);
				}
				
			} catch (Exception err) {
				System.err.println(err.getMessage());
			}

		}
	}

}
