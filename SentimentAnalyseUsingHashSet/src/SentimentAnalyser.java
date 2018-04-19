import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

public class SentimentAnalyser {

    private static String sentimentAnalyser(String input) {
        HashSet < String > positives = new HashSet < String > ();
        HashSet < String > negatives = new HashSet < String > ();
        String word = null;


        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/NegativeWords.txt"));
            while ((word = reader.readLine()) != null) {
                negatives.add(word);
            }
            reader = new BufferedReader(new FileReader("resources/PositiveWords.txt"));
            while ((word = reader.readLine()) != null) {
                positives.add(word);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int value = 0;
        String[] inputStr = input.split(" ");
        for (int i = 0; i < inputStr.length; i++) { 
                if (positives.contains(inputStr[i])) {
                    value++;
                    System.out.println("positive word:  " + inputStr[i]);
                } else if (negatives.contains(inputStr[i])) {
                    value--;
                    System.out.println("negative word:  " + inputStr[i]);
                }
        }
        if (value == 0)
            return ("This is a neutral sentiment");
        else if (value > 0)
            return ("This is a positive sentiment");
        else return ("This is a negative sentiment");
    }
        public static void main(String args[]) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the statement or type stop to quit the program: ");
            String input=scanner.nextLine();
            while(!input.equals("stop"))
            {
             System.out.println(sentimentAnalyser(input));
             System.out.print("Enter the statement or type stop to quit the program: ");
             input = scanner.nextLine();
                        
            }
        }
    }