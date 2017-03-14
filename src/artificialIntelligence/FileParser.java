package artificialIntelligence;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Ryan on 3/9/2017.
 */
public class FileParser {
    /**
     * Method reads the names from the given file and runs them through the markov model.
     * @param filename
     * @param modelOrder
     * @return
     */
    protected MarkovModel readFile(String filename, int modelOrder) {
        MarkovModel markovModel = new MarkovModel(modelOrder);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            int totalCount = 0;
            while ((line = reader.readLine()) != null) {
                String temp = line;
                temp = temp.toLowerCase();
                markovModel.buildModel(temp);
            }
            reader.close();

            return markovModel;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }
}
