package artificialIntelligence;

import java.util.*;

public class Main {

    public static String boyFile = "src/artificialIntelligence/namesBoys.txt";
    public static String girlFile = "src/artificialIntelligence/namesGirls.txt";
    public static void main(String[] args) {
        int modelOrder, minLength, maxLength, numberOfNames, input;
        boolean boy = true;

        Scanner reader = new Scanner(System.in);
        // Model Order Scanner loop
        do {
            System.out.println("Enter Markov Model order (>=2): ");
            while (!reader.hasNextInt()) {
                System.out.println("Input Error!");
                reader.next();
            }
            modelOrder = reader.nextInt();
        } while (modelOrder < 2);

        // Boy or Girl Scanner loop
        do {
            System.out.println("Enter 1 for boys' names. Enter 2 for girls' names: ");
            while (!reader.hasNextInt()) {
                System.out.println("Input Error!");
                reader.next();
            }
            input = reader.nextInt();
        } while (input != 1 && input != 2);
        if (input == 1)
            boy = true;
        else if (input == 2)
            boy = false;


        // Minimum name length loop
        do {
            System.out.println("Enter the minimum length of the name: ");
            while (!reader.hasNextInt()) {
                System.out.println("Input Error!");
                reader.next();
            }
            minLength = reader.nextInt();
        } while (minLength < 0);

        // Minimum name length loop
        do {
            System.out.println("Enter the maximum length of the name: ");
            while (!reader.hasNextInt()) {
                System.out.println("Input Error!");
                reader.next();
            }
            maxLength = reader.nextInt();
        } while (maxLength <= minLength);

        // Number of names loop
        do {
            System.out.println("How many names do you want: ");
            while (!reader.hasNextInt()) {
                System.out.println("Input Error!");
                reader.next();
            }
            numberOfNames = reader.nextInt();
        } while (numberOfNames < 1);



        FileParser file = new FileParser();
        MarkovModel markovModel;
        if (boy)
            markovModel = file.readFile(boyFile, modelOrder);
        else
            markovModel = file.readFile(girlFile, modelOrder);

        NameGenerator gen = new NameGenerator(markovModel, modelOrder);
        for (int i = 0; i < numberOfNames;) {
            String name = gen.getName();
            if (name != null && !markovModel.checkNameNovelty(name) && name.length() <= maxLength && name.length() >= minLength) {
                name = name.substring(0,1).toUpperCase() + name.substring(1);
                System.out.println(name);
                i++;
            }
        }
    }
}
