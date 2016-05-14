package features_inversion.classification;
import java.io.File;
import java.io.FileReader;

import weka.core.Instances;

public class FindBinClasses {
	public static void main(String[] args) throws Exception {
		File dataFolder = new File("data/carff");

		int n = 0, m = 0;

		for (File arff : dataFolder.listFiles()) {
			try {
				Instances instances = new Instances(new FileReader(arff));
				instances.setClassIndex(instances.numAttributes() - 1);
				++n;

				if (instances.numClasses() == 2) {
					++m;
				}

			} catch (Exception err) {
				System.out.println(arff + " " + err.getMessage());
			}
		}

		System.out.println(n + " " + m);
	}
}